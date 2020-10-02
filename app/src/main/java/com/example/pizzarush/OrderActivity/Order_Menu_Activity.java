package com.example.pizzarush.OrderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.CustomerActivity.Profile;
import com.example.pizzarush.CustomerActivity.menu;
import com.example.pizzarush.R;

public class Order_Menu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_menu);

        Button btnB = (Button) findViewById(R.id.btnbev);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent (Order_Menu_Activity.this,place_order_beverages.class);
                startActivity(intent);
            }
        });

        Button btnP = (Button) findViewById(R.id.btnpiz);
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent (Order_Menu_Activity.this,place_order_pizza.class);
                startActivity(intent);
            }
        });

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
            Intent intent = new Intent(Order_Menu_Activity.this, Profile.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.menu)
        {
            Intent intent = new Intent(Order_Menu_Activity.this, menu.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
