package com.example.pizzarush.OrderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.CustomerActivity.Profile;
import com.example.pizzarush.CustomerActivity.menu;
import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.Entity.Location;
import com.example.pizzarush.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LocationActivity extends AppCompatActivity {
    EditText cid,address;
    Button save;

    DatabaseReference dbref;
    Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        Button btnnext = (Button) findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iid = address.getText().toString();
                if (iid.equals("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter your address", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent =new Intent (LocationActivity.this,Order_Menu_Activity.class);
                startActivity(intent);
            }
        });




        cid =findViewById(R.id.txtname);
        cid.setText(CustomerUtil.getMobile());
        address =findViewById(R.id.txtresult);


        save = findViewById(R.id.btnadd);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String iid = address.getText().toString();
                if (iid.equals("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter your address", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbref= FirebaseDatabase.getInstance().getReference().child("Location");

                String sid = cid.getText().toString();
                String saddress = address.getText().toString();
                dbref.child(sid).setValue(saddress);
                Toast.makeText(getApplicationContext(),"Data  Save Successful",Toast.LENGTH_SHORT).show();
            }
        });
    }
        public void clearController()
        {
            cid.setText("");
            address.setText("");

        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.profile)
        {
            Intent intent = new Intent(LocationActivity.this, Profile.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.menu)
        {
            Intent intent = new Intent(LocationActivity.this, menu.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
