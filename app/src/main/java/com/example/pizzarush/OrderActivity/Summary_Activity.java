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
import com.example.pizzarush.Entity.Point;
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
                        String cid = CustomerUtil.getMobile();
                        if(dataSnapshot.hasChild(sid))
                        {
                            dbref = FirebaseDatabase.getInstance().getReference().child("Order").child(sid);
                            dbref.removeValue();
                            String custId = CustomerUtil.getCid();
                            updateUserPoints(custId);
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
                    points -= 50;
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
    public void clearController()
    {
        id.setText("");
        amount.setText("");
        des.setText("");
    }
}