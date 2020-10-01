package com.example.pizzarush.OrderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.Entity.Order;
import com.example.pizzarush.Entity.OrderItem;
import com.example.pizzarush.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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
                Toast.makeText(getApplicationContext(),"Data  Save Successful",Toast.LENGTH_SHORT).show();
            }
        });



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
