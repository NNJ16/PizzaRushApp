package com.example.pizzarush.OrderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.CustomerActivity.Profile;
import com.example.pizzarush.CustomerActivity.menu;
import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.Entity.Order;
import com.example.pizzarush.Entity.OrderItem;
import com.example.pizzarush.Entity.Point;
import com.example.pizzarush.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class place_order_beverages extends AppCompatActivity {

    TextView Total,id,des;
    Button save;

    DatabaseReference dbref;
    Order order;
    OrderItem orderItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_order_beverages);
        orderItem = new OrderItem();
        Button btnsum = (Button) findViewById(R.id.btnsum);
        btnsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent (place_order_beverages.this,Summary_Activity.class);
                startActivity(intent);
            }
        });

        Total=findViewById(R.id.txtTotal);
        des=findViewById(R.id.txtdes);
        save = findViewById(R.id.btnorder);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref= FirebaseDatabase.getInstance().getReference().child("Order");
                String cid = CustomerUtil.getMobile();
                String stotal = Total.getText().toString();
                String sdes = des.getText().toString();
                Order order = new Order(sdes,stotal,cid);
                dbref.child(cid).setValue(order);
                String custId = CustomerUtil.getCid();
                updateUserPoints(custId);
                Toast.makeText(getApplicationContext(),"Data  Save Successful",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean updateUserPoints(final String cid)
    {
        final boolean[] isSuccess = {false};
        DatabaseReference readRefP = FirebaseDatabase.getInstance().getReference().child("Point").child(cid);
        //Add Customer Points
        readRefP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren())
                {
                    String mLevel = dataSnapshot.child("mLevel").getValue().toString();
                    int points = Integer.parseInt(dataSnapshot.child("points").getValue().toString());
                    points += 50;
                    mLevel = getMemberLevel(points);
                    Point point = new Point(mLevel,points);
                    dbref = FirebaseDatabase.getInstance().getReference().child("Point").child(cid);
                    dbref.setValue(point);
                    isSuccess[0] =true;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return isSuccess[0];
    }

    public String getMemberLevel(int points)
    {
        if(points>500)
        {
            return  "Silver";
        }else if(points>1000)
        {
            return  "Gold";
        }else if(points>2000)
        {
            return  "Platinum";
        }else if(points>5000)
        {
            return  "Diamond";
        }else
            {
                return  " ";
            }
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
            Intent intent = new Intent(place_order_beverages.this, Profile.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.menu)
        {
            Intent intent = new Intent(place_order_beverages.this, menu.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb1:
                if (checked)
                    orderItem.setBeverages_size_price(100.0);
                    orderItem.setSdes("400 ml");
                break;
            case R.id.rb2:
                if (checked)
                    orderItem.setBeverages_size_price(160.00);
                    orderItem.setSdes("750 ml ");
                break;
            case R.id.rb3:
                if (checked)
                    orderItem.setBeverages_size_price(220.0);
                    orderItem.setSdes("1500 ml");
                break;
            default:

        }
        Total.setText("  Total Price : Rs"+ orderItem.getBeverages_size_price());
        des.setText(" Beverages Des:"+ orderItem.getSdes());
    }
}
