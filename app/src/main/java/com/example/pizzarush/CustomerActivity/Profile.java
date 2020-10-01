package com.example.pizzarush.CustomerActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.Entity.Customer;
import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity implements PasswordDialog.PasswordDialogListner{
    ImageButton btnFname,btnLname,btnMobile,btnPwd;
    Button btnSave,btnDel;
    EditText txtFname,txtLname,txtMobile,txtPass;
    TextView lblML,lblP;

    DatabaseReference dbref;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnFname = findViewById(R.id.btneFname);
        btnLname = findViewById(R.id.btnElname);
        btnMobile = findViewById(R.id.btnEmobile);
        btnPwd = findViewById(R.id.btnEpwd);

        txtFname = findViewById(R.id.txtEfname);
        txtLname = findViewById(R.id.txtElname);
        txtMobile = findViewById(R.id.txtEmobile);
        txtPass = findViewById(R.id.txtEpwd);

        lblML = findViewById(R.id.lblMlevel);
        lblP = findViewById(R.id.lblMpoints);

        btnSave = findViewById(R.id.btnSave);
        btnDel = findViewById(R.id.btnDelAcc);

        final String cid = CustomerUtil.getCid();
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(cid);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren())
                {
                    txtFname.setText(dataSnapshot.child("fname").getValue().toString());
                    txtLname.setText(dataSnapshot.child("lname").getValue().toString());
                    txtMobile.setText(dataSnapshot.child("mobile").getValue().toString());
                    txtPass.setText(dataSnapshot.child("pass").getValue().toString());
                    email = dataSnapshot.child("email").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference readRefP = FirebaseDatabase.getInstance().getReference().child("Point").child(cid);

        readRefP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren())
                {
                    lblML.setText(dataSnapshot.child("mLevel").getValue().toString());
                    lblP.setText(dataSnapshot.child("points").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnFname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtFname.setEnabled(true);
            }
        });

        btnLname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtLname.setEnabled(true);
            }
        });
        btnMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMobile.setEnabled(true);
            }
        });

        btnPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerUtil.setPwd(txtPass.getText().toString());
                openDialog();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Customer");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String fname = txtFname.getText().toString();
                        String lname = txtLname.getText().toString();
                        String mobile = txtMobile.getText().toString();
                        String pass = txtPass.getText().toString();

                        if(dataSnapshot.hasChild(cid))
                        {
                            Customer customer = new Customer(fname,lname,mobile,email,pass);
                            dbref = FirebaseDatabase.getInstance().getReference().child("Customer").child(cid);
                            dbref.setValue(customer);
                            Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();

                            txtFname.setEnabled(false);
                            txtLname.setEnabled(false);
                            txtMobile.setEnabled(false);
                            txtPass.setEnabled(false);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDelDialog();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void openDialog()
    {
        PasswordDialog passwordDialog = new PasswordDialog();
        passwordDialog.show(getSupportFragmentManager(),"Password Dialog");
    }

    public void openDelDialog(){
        DeleteDialog deleteDialog = new DeleteDialog();
        deleteDialog.show(getSupportFragmentManager(),"Delete Dialog");
    }

    @Override
    public void applyText(String pwd) {
        txtPass.setText(pwd);
    }
}
