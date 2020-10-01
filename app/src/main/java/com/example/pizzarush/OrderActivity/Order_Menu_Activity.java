package com.example.pizzarush.OrderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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


}
