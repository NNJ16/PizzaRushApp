package com.example.pizzarush.DeliverActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.R;

public class Delivery extends AppCompatActivity {

    Button btn_assignR, btn_manageR, btn_addR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        btn_assignR=(Button)findViewById(R.id.btn_assignR);

        btn_assignR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery.this, AssignR.class);
                startActivity(intent);
            }
        });

        btn_manageR=(Button)findViewById(R.id.btn_ManageR);

        btn_manageR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery.this, Riders.class);
                startActivity(intent);
            }
        });

        btn_addR=(Button)findViewById(R.id.btn_AddR);

        btn_addR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery.this, regRider.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}