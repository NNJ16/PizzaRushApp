package com.example.pizzarush.OrderActivity;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.Entity.Order;
import com.example.pizzarush.Entity.OrderItem;
import com.example.pizzarush.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Summary_Activity extends AppCompatActivity {

    EditText id,des,amount;
    Button edit,cancle;


    DatabaseReference dbref;
    OrderItem orit;
    Order order;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);

        edit = findViewById(R.id.btnupdate);
        cancle= findViewById(R.id.btndelete);

        des=findViewById(R.id.txtdesS);
        amount=findViewById(R.id.txtamountS);
        id=findViewById(R.id.txtidS);


        String cid =CustomerUtil.getMobile();
        id.setText(cid);
        String sid = id.getText().toString();
        
        DatabaseReference readref1 = FirebaseDatabase.getInstance().getReference().child("Order").child(sid);
        readref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren())
                    des.setText(snapshot.child("sdes").getValue().toString());
                    amount.setText(snapshot.child("stotal").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updateref= FirebaseDatabase.getInstance().getReference().child("Order");
                updateref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String cid = CustomerUtil.getMobile();
                        String sdes = des.getText().toString();
                        String samount = amount.getText().toString();

                        if(dataSnapshot.hasChild(cid))
                        {
                           // dbref = FirebaseDatabase.getInstance().getReference().child("Order").child(cid);
                           // order = new Order(sdes,samount,cid);
                           // dbref.setValue(order);
                           // Toast.makeText(getApplicationContext(),"Update Successfully ",Toast.LENGTH_SHORT).show();

                            Intent intent =new Intent (Summary_Activity.this, Order_Menu_Activity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference deleref = FirebaseDatabase.getInstance().getReference().child("Order");

                deleref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        String sid = id.getText().toString();

                        if(dataSnapshot.hasChild(sid))
                        {
                            dbref = FirebaseDatabase.getInstance().getReference().child("Order").child(sid);
                            dbref.removeValue();
                            Toast.makeText(getApplicationContext(),"Delete Successfully ",Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent (Summary_Activity.this, Order_Menu_Activity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Delete Not Successfully ",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {

                    }
                });
            }
        });
    }
    public void clearController()
    {
        id.setText("");
        amount.setText("");
        des.setText("");
    }
}