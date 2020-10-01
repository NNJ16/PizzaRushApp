package com.example.pizzarush.CustomerActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzarush.Entity.Customer;
import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.Entity.Point;
import com.example.pizzarush.MainActivity;
import com.example.pizzarush.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText txtFname,txtLname,txtMobile,txtEmail,txtPass1,txtPass2;
    Button btnReg;
    DatabaseReference dbRef,dbRefP;

    private static final String CHANNEL_ID ="1001" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtFname = findViewById(R.id.txtFname);
        txtLname = findViewById(R.id.txtLname);
        txtMobile = findViewById(R.id.txtMobile);
        txtEmail = findViewById(R.id.txtlEmail);
        txtPass1 = findViewById(R.id.txtPwd1);
        txtPass2 = findViewById(R.id.txtPwd2);

        btnReg = findViewById(R.id.btnReg);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = getString(R.string.channel_name);
            String description =
                    getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new
                    NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
                dbRefP = FirebaseDatabase.getInstance().getReference().child("Point");

                if(TextUtils.isEmpty(txtFname.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Enter first name",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(txtLname.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Enter last name",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(txtMobile.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Enter mobile no",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(txtEmail.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Enter email address",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(txtPass1.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Enter a password",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(txtPass2.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Confirm your password",Toast.LENGTH_SHORT).show();
                }else
                    {
                        String fname =txtFname.getText().toString();
                        String lname = txtLname.getText().toString();
                        String email = txtEmail.getText().toString();
                        String mobile = txtMobile.getText().toString();
                        String pass = txtPass1.getText().toString();
                        String cpass = txtPass2.getText().toString();



                        if(pass.equals(cpass))
                        {
                            String cid = CustomerUtil.generateCid(email);
                            if(cid != null)
                            {
                                Customer customer = new Customer(fname,lname,mobile,email,pass);
                                Point point = new Point("Bronze",100);
                                dbRef.child(cid).setValue(customer);
                                dbRefP.child(cid).setValue(point);
                                setNotify();
                                Intent intent = new Intent(Register.this, login.class);
                                startActivity(intent);

                            }else
                                {
                                    Toast.makeText(getApplicationContext(),"Enter a valid email address",Toast.LENGTH_SHORT).show();
                                }
                        }else
                            {
                                Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_SHORT).show();
                            }
                    }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setNotify()
    {
        Intent intent = new Intent(this, Notification.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("PizzaRush")
                .setContentText("Welcome to Pizza Rush!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager
                = NotificationManagerCompat.from(this);

        notificationManager.notify(0, builder.build());
    }
}
