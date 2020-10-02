package com.example.pizzarush.ItemActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.Entity.Item;
import com.example.pizzarush.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class update_item extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText id, name, ingre, description, type, price;
    Button update, cancel;
    TextView imgid;

    private Spinner spinnerItemType;

    DatabaseReference dbref;
    Item itm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        spinnerItemType = findViewById(R.id.spinnerItemType);

        Intent intent = getIntent();

        String tid = intent.getStringExtra("key");

        TextView textView = findViewById(R.id.txt_uid);

        textView.setText(tid);

        id = findViewById(R.id.txt_uid);
        name = findViewById(R.id.txt_uname);
        ingre = findViewById(R.id.txt_uingre);
        description = findViewById(R.id.txt_udescription);

//        type = findViewById(R.id.txt_utype);
        price = findViewById(R.id.txt_uprice);
        imgid = findViewById(R.id.txt_uimgid);

        update = findViewById(R.id.btn_uadd);
        cancel = findViewById(R.id.btn_ucancel);

        id.setText(tid);

        String iid = id.getText().toString();
        DatabaseReference showdref = FirebaseDatabase.getInstance().getReference().child("Item").child(iid);

        showdref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren())
                {
                    id.setText(dataSnapshot.child("id").getValue().toString());
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    ingre.setText(dataSnapshot.child("ingre").getValue().toString());
                    description.setText(dataSnapshot.child("description").getValue().toString());
                    String[] textSizes = getResources().getStringArray(R.array.font_sizes);
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_spinner_item, textSizes);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerItemType.setAdapter(adapter);
                   spinnerItemType.setSelection(adapter.getPosition("type"));
//                  type.setText(dataSnapshot.child("type").getValue().toString());
                   price.setText(dataSnapshot.child("price").getValue().toString());
//                  imgid.setText(dataSnapshot.child("imgid").getValue().toString());

                }else
                {
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Item");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String iid = id.getText().toString();
                        String iname = name.getText().toString();
                        String iingre = ingre.getText().toString();
                        String idescription = description.getText().toString();
//                        String itype = type.getText().toString();
                        String itype = spinnerItemType.getSelectedItem().toString();
                        int iprice = Integer.parseInt(price.getText().toString());
//                      String iimgid = imgid.getText().toString();


                        if (dataSnapshot.hasChild(iid)) {
                            itm = new Item(iid, iname, iingre, idescription, itype, iprice);
                            dbref = FirebaseDatabase.getInstance().getReference().child("Item").child(iid);
                            dbref.setValue(itm);
                            clearControlls();

                            Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        }


    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getId() == R.id.spinnerItemType)
        {
            String valuefromSpinner = adapterView.getItemAtPosition(position).toString();
            type.getText().toString();
        }
    }


    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void clearControlls()
    {
        id.setText("");
        name.setText("");
        ingre.setText("");
        description.setText("");
//        type.setText("");
        price.setText("");
        imgid.setText("");
    }
    }







