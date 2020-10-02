package com.example.pizzarush.OrderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.CustomerActivity.Profile;
import com.example.pizzarush.CustomerActivity.menu;
import com.example.pizzarush.Entity.Customer;
import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.Entity.Location;
import com.example.pizzarush.Entity.Order;
import com.example.pizzarush.Entity.OrderItem;
import com.example.pizzarush.Entity.Point;
import com.example.pizzarush.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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

                // Add Customer Points
                String cusid = CustomerUtil.getCid();
                System.out.println(cusid);
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Point").child(cusid);
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren())
                        {
                            String mLevel = dataSnapshot.child("mLevel").getValue().toString();
                            int points = Integer.parseInt(dataSnapshot.child("points").getValue().toString());
                            points += 100;
                            if(points>500)
                            {
                                mLevel = "Silver";
                            }else if(points>1000)
                            {
                                mLevel = "Gold";
                            }else if(points>2000)
                            {
                                mLevel = "Platinum";
                            }else if(points>5000)
                            {
                                mLevel = "Diamond";
                            }
                            Point point = new Point(mLevel,points);
                            String cid = CustomerUtil.getCid();
                            dbref = FirebaseDatabase.getInstance().getReference().child("Point").child(cid);
                            dbref.setValue(point);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(getApplicationContext(),"Data  Save Successful",Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(place_order_pizza.this, Profile.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.menu)
        {
            Intent intent = new Intent(place_order_pizza.this, menu.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
