package com.example.pizzarush.DeliverActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.Entity.Rider;
import com.example.pizzarush.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class regRider extends AppCompatActivity {

    EditText rId, rName, rPhone, rEmail, rBikeNo;
    Button registerR;

    DatabaseReference dbref;

    Rider rreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_rider);

        rId=findViewById(R.id.txt_riderIDR);
        rName=findViewById(R.id.txt_nameR);
        rPhone=findViewById(R.id.txt_phoneR);
        rEmail=findViewById(R.id.txt_emailR);
        rBikeNo=findViewById(R.id.txt_bikeR);

        registerR=findViewById(R.id.btn_registerR);

        registerR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                }else if(TextUtils.isEmpty(rBikeNo.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Enter Bike No",Toast.LENGTH_SHORT).show();
                }else
                    {
                        dbref= FirebaseDatabase.getInstance().getReference().child("Rider");
                        String rid=rId.getText().toString();
                        String rname=rName.getText().toString();
                        int rphone=Integer.parseInt(rPhone.getText().toString());
                        String remail=rEmail.getText().toString();
                        String rbikeno=rBikeNo.getText().toString();

                        rreg=new Rider(rid,rname,rphone,remail,rbikeno);
                        dbref.child(rid).setValue(rreg);
                        clearControls();
                        Toast.makeText(getApplicationContext(),"Rider Registered Successfully",Toast.LENGTH_LONG).show();
                    }

            }

            public void clearControls(){
                rId.setText("");
                rName.setText("");
                rPhone.setText("");
                rEmail.setText("");
                rBikeNo.setText("");
            }

        });




    }
}