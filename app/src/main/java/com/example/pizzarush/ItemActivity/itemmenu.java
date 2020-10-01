package com.example.pizzarush.ItemActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.R;

public class itemmenu extends AppCompatActivity {

    Button btnadd, btnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemmenu);

        btnadd = (Button) findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemmenu.this, add_item.class);
                startActivity(intent);
            }
        });

        btnview = (Button) findViewById(R.id.btnview);

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemmenu.this, view_item.class);
                startActivity(intent);
            }
        });

    }
}