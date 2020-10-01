package com.example.pizzarush.DeliverActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.Entity.Rider;
import com.example.pizzarush.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Riders extends AppCompatActivity {

    EditText srId, rId, rName, rPhone, rEmail, rBike;
     TextView       deliveredO;
    Button updateR,deleteR,searchR;

    DatabaseReference dbref;

    Rider rider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riders);

        srId=findViewById(R.id.txt_riderID);
        rId=findViewById(R.id.txt_riderIDR);
        rName=findViewById(R.id.txt_nameR);
        rPhone=findViewById(R.id.txt_phoneR);
        rEmail=findViewById(R.id.txt_emailR);
        rBike=findViewById(R.id.txt_bikeR2);
        deliveredO=findViewById(R.id.txt_DeliveredOR);

        updateR=findViewById(R.id.btn_updateR);
        deleteR=findViewById(R.id.btn_deleteR);
        searchR=findViewById(R.id.btn_searchR);

        searchR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srid=srId.getText().toString();
                DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("Rider").child(srid);

                readref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            rId.setText(dataSnapshot.child("riderId").getValue().toString());
                            rName.setText(dataSnapshot.child("nameR").getValue().toString());
                            rPhone.setText(dataSnapshot.child("phoneR").getValue().toString());
                            rEmail.setText(dataSnapshot.child("emailR").getValue().toString());
                            deliveredO.setText(dataSnapshot.child("deliveredOR").getValue().toString());
                            rBike.setText(dataSnapshot.child("bikeNo").getValue().toString());
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No riders to Display",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        updateR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef=FirebaseDatabase.getInstance().getReference().child("Rider");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(TextUtils.isEmpty(rId.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(),"Enter Rider Id",Toast.LENGTH_SHORT).show();
                        }else if(TextUtils.isEmpty(rName.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(),"Enter Rider Name",Toast.LENGTH_SHORT).show();
                        }else if(TextUtils.isEmpty(rPhone.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(),"Enter Mobile No",Toast.LENGTH_SHORT).show();
                        }else if(TextUtils.isEmpty(rEmail.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(),"Enter Rider Email",Toast.LENGTH_SHORT).show();
                        }else if(TextUtils.isEmpty(deliveredO.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(),"Enter delivered Orders",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            String rid=rId.getText().toString();
                            String rname=rName.getText().toString();
                            int rphone=Integer.parseInt(rPhone.getText().toString());
                            String remail=rEmail.getText().toString();
                            int rdeliveredO=Integer.parseInt(deliveredO.getText().toString());
                            String bNo = rBike.getText().toString();

                            if(dataSnapshot.hasChild(rid)){
                                rider=new Rider(rid,rname,rphone,remail,bNo,rdeliveredO);
                                dbref=FirebaseDatabase.getInstance().getReference().child("Rider").child(rid);
                                dbref.setValue(rider);
                                clearControls();

                                Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"No Source found",Toast.LENGTH_LONG).show();

                            }

                    }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });

        deleteR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef=FirebaseDatabase.getInstance().getReference().child("Rider");

                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String rid=rId.getText().toString();
                        if (dataSnapshot.hasChild(rid)){
                            dbref=FirebaseDatabase.getInstance().getReference().child("Rider").child(rid);
                            dbref.removeValue();

                            clearControls();
                            srId.setText("");

                            Toast.makeText(getApplicationContext(),"Rider deleted Successfully",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No Rider to Delete",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



    }

    public void clearControls(){
        rId.setText("");
        rName.setText("");
        rPhone.setText("");
        rEmail.setText("");
        rBike.setText("");
        deliveredO.setText("");
    }

}