package com.example.pizzarush.CustomerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.pizzarush.Admin;
import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.Entity.Point;

import com.example.pizzarush.OrderActivity.LocationActivity;
import com.example.pizzarush.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    Button btnCreate,btnLog;
    EditText txtEmail,txtPass;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnLog = (Button) findViewById(R.id.btnLog);

        txtEmail = findViewById(R.id.txtlEmail);
        txtPass = findViewById(R.id.txtlPwd);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, Register.class);
                startActivity(intent);

            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEmail.getText().toString().trim().equals("admin") && txtPass.getText().toString().trim().equals("1234"))
                {
                    Intent intent = new Intent(login.this, Admin.class);
                    startActivity(intent);
                }else
                    {
                        if(TextUtils.isEmpty(txtEmail.getText().toString()) || txtEmail.getText().toString().lastIndexOf("@") == -1)
                        {
                            Toast.makeText(getApplicationContext(),"Enter your email",Toast.LENGTH_SHORT).show();
                        }else if(TextUtils.isEmpty(txtPass.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(),"Enter your password",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            String email = txtEmail.getText().toString();
                            final String cid = CustomerUtil.generateCid(email);

                            //Customer Login Validate
                            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(cid);

                            readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChildren())
                                    {
                                        String email1 = txtEmail.getText().toString();
                                        String pass1 = txtPass.getText().toString();
                                        String pass2 = dataSnapshot.child("pass").getValue().toString();
                                        String email2 = dataSnapshot.child("email").getValue().toString();
                                        String mobile = dataSnapshot.child("mobile").getValue().toString();

                                        if(pass1.equals(pass2) && email1.toLowerCase().equals(email2.toLowerCase()))
                                        {
                                            CustomerUtil.setCid(cid);
                                            CustomerUtil.setMobile(mobile);
                                            updateUserPoints(cid);
                                            Intent intent = new Intent(login.this, LocationActivity.class);
                                            startActivity(intent);

                                        }else
                                        {
                                            Toast.makeText(getApplicationContext(),"Your account email or password is Incorrect",Toast.LENGTH_SHORT).show();
                                        }
                                    }else
                                    {
                                        Toast.makeText(getApplicationContext(),"Your account email is Incorrect",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                    }

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
                    points += 5;
                    mLevel = getMemberLevel(points);
                    Point point = new Point(mLevel,points);
                    dbRef = FirebaseDatabase.getInstance().getReference().child("Point").child(cid);
                    dbRef.setValue(point);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
