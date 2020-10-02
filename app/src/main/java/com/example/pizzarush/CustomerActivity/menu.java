package com.example.pizzarush.CustomerActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.MainActivity;
import com.example.pizzarush.OrderActivity.LocationActivity;
import com.example.pizzarush.OrderActivity.Order_Menu_Activity;
import com.example.pizzarush.R;

public class menu extends AppCompatActivity {
    Button btnLout,btnContact,btnMenu,btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnLout = findViewById(R.id.btnLogOut);
        btnContact = findViewById(R.id.btnContact);
        btnMenu = findViewById(R.id.btnMenu);
        btnHome = findViewById(R.id.btnHome);

        btnLout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                triggerRebirth(getApplicationContext());
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0912222333"));
                startActivity(intent);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, Order_Menu_Activity.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, LocationActivity.class);
                startActivity(intent);
            }
        });
    }
    public static void triggerRebirth(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
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
           Intent intent = new Intent(menu.this, Profile.class);
           startActivity(intent);
       }
       return super.onOptionsItemSelected(item);
    }
}
