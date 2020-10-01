package com.example.pizzarush.OrderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.Entity.Location;
import com.example.pizzarush.Entity.Order;
import com.example.pizzarush.Entity.OrderItem;
import com.example.pizzarush.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class place_order_pizza extends AppCompatActivity {

    Location loc;
    Order order;
    TextView total, des,id;
    double total_price;
    Button save;

    OrderItem orderitem;

    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_order_pizza);
        orderitem= new OrderItem();

        Button btnsum = (Button)findViewById(R.id.btnsum);
        btnsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(place_order_pizza.this, Summary_Activity.class);
                   startActivity(intent);
            }
        });




        total = findViewById(R.id.txtTotal);
        des = findViewById(R.id.txtDes);

        save = findViewById(R.id.btnorder);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref= FirebaseDatabase.getInstance().getReference().child("Order");
                String cid = CustomerUtil.getMobile();
                String stotal = total.getText().toString();
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
                    orderitem.setPizza_size_price(500.0);
                    orderitem.setSdes("Small");
                break;
            case R.id.rb2:
                if (checked)
                    orderitem.setPizza_size_price(950.0);
                    orderitem.setSdes("Medium");
                break;
            case R.id.rb3:
                if (checked)
                    orderitem.setPizza_size_price(1750.0);
                    orderitem.setSdes("Large");
                break;
            default:

        }
        total.setText("  Total Price :  "+ orderitem.getPizza_size_price());
        des.setText(" Pizza Dee:"+ orderitem.getSdes());

    }
    public void onCheckBoxClicked(View view){

        Boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()) {

        case R.id.cb1:
            if (checked) {
                orderitem.setMeat_price(100.0);
                orderitem.setSdes(orderitem.getSdes() + " Extra Meat");
            }else
                {
                    orderitem.setSdes(orderitem.getSdes().substring(0,orderitem.getSdes().length()-10));
                }
            break;
        case R.id.cb2:
            if (checked) {
                orderitem.setCheese_price(200.0);
                orderitem.setSdes(orderitem.getSdes() + " Extra cheese");
            }else
        {
            orderitem.setSdes(orderitem.getSdes().substring(0,orderitem.getSdes().length()-12));
        }
            break;
        case R.id.cb3:
            if (checked) {
                orderitem.setBBQ_price(250.0);
                orderitem.setSdes(orderitem.getSdes() + " BBQ Chicken");
            }
            else
            {
                orderitem.setSdes(orderitem.getSdes().substring(0,orderitem.getSdes().length()-11));
            }
            break;

        }
        total.setText("  Total Price : Rs." + calculate_total());
        des.setText(" Pizza Des:"+ orderitem.getSdes());
    }

    private double calculate_total() {
         total_price = orderitem.getPizza_size_price()+ orderitem.getCheese_price()+orderitem.getMeat_price()+ orderitem.getBBQ_price();

         return total_price;
    }
}
