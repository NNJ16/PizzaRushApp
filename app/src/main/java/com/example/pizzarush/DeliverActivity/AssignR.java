package com.example.pizzarush.DeliverActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.Entity.Assign;
import com.example.pizzarush.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AssignR extends AppCompatActivity {

    EditText orderId, riderId;
    Button add, delete;

    DatabaseReference dbref;

    Assign assign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_r);

        orderId=findViewById(R.id.txt_orderIDR);
        riderId=findViewById(R.id.txt_riderIDR);
        add=findViewById(R.id.btn_addR);
        delete=findViewById(R.id.btn_deleteR);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final boolean[] isSucees = {false};
                final String riderID = riderId.getText().toString();
                final DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Rider").child(riderID);

                if(TextUtils.isEmpty(orderId.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Enter Order ID",Toast.LENGTH_SHORT);
                }else if(TextUtils.isEmpty(riderId.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Enter Rider ID",Toast.LENGTH_SHORT);
                }else
                    {
                        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChildren())
                                {
                                    dbref= FirebaseDatabase.getInstance().getReference().child("Assign");
                                    final String orderid=orderId.getText().toString();
                                    String rideid=riderId.getText().toString();
                                    assign=new Assign(orderid, rideid);
                                    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Order").child(orderid);
                                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if(dataSnapshot.hasChildren())
                                            {
                                                dbref.child(orderid).setValue(assign);
                                                isSucees[0] =true;
                                                clearControls();
                                                Toast.makeText(getApplicationContext(),"Rider Assigned Successfully",Toast.LENGTH_LONG).show();
                                            }else
                                            {
                                                Toast.makeText(getApplicationContext(),"Order id is not available",Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    if(isSucees[0])
                                    {
                                        int n = Integer.parseInt(dataSnapshot.child("deliveredOR").getValue().toString());
                                        n++;
                                        dbref = FirebaseDatabase.getInstance().getReference().child("Rider");
                                        dbref.child(riderID).child("deliveredOR").setValue(n);
                                    }
                                }else
                                {
                                    Toast.makeText(getApplicationContext(),"Rider id is not available",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef=FirebaseDatabase.getInstance().getReference().child("Assign");

                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String oid=orderId.getText().toString();
                        if (dataSnapshot.hasChild(oid)){
                            dbref=FirebaseDatabase.getInstance().getReference().child("Assign").child(oid);
                            dbref.removeValue();

                            Toast.makeText(getApplicationContext(),"Assigned rider deleted successfully",Toast.LENGTH_LONG).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No assigned orders to Rider",Toast.LENGTH_LONG).show();
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
        orderId.setText("");
        riderId.setText("");
    }


}