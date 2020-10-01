package com.example.pizzarush.ItemActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzarush.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class add_item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText id, name, ingre, description, type, price;
    Button add, cancel, up;
    ImageView img;
    public Uri imguri;
//    TextView imgid;

    private Spinner spinnerItemType;


    DatabaseReference dbref;

    Item itm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
//        mStorageRef = FirebaseStorage.getInstance().getReference("Images");

        spinnerItemType = findViewById(R.id.spinnerItemType);

        id = findViewById(R.id.txt_uid);
        name = findViewById(R.id.txt_uname);
        ingre = findViewById(R.id.txt_uingre);
        description = findViewById(R.id.txt_udescription);
        //type = findViewById(R.id.spinnerItemType);
        //type = findViewById(R.id.txt_utype);
        price = findViewById(R.id.txt_uprice);

//        imgid = findViewById(R.id.txt_imageid);

        add = findViewById(R.id.btn_uadd);
        cancel = findViewById(R.id.btn_ucancel);
        up = findViewById(R.id.btn_upload);

//        spinnerItemType.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.font_sizes);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemType.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       dbref = FirebaseDatabase.getInstance().getReference().child("Item");

                                       //String iid = id.getText().toString();

                                       //EditText iid = (EditText) findViewById(R.id.txt_uid);
                                       String iid = id.getText().toString();
                                       if (iid.matches("")) {
                                           Toast.makeText(getApplicationContext(), "You did not enter a ID", Toast.LENGTH_SHORT).show();
                                           return;
                                       }

                                       String iname = name.getText().toString();
                                       if (iname.matches("")) {
                                           Toast.makeText(getApplicationContext(), "You did not enter a Name", Toast.LENGTH_SHORT).show();
                                           return;
                                       }

                                       String iingre = ingre.getText().toString();
                                       String idescription = description.getText().toString();
//                                       String itype = spinnerItemType.getText().toString();
                                       String itype = spinnerItemType.getSelectedItem().toString();
                                       int iprice = Integer.parseInt(price.getText().toString());


//                                       String iimgid = imgid.getText().toString();

                                       itm = new Item(iid, iname, iingre, idescription, itype, iprice);

                                       dbref.child(iid).setValue(itm);

                                       Toast.makeText(getApplicationContext(), "Data Added Successfully.", Toast.LENGTH_SHORT).show();
                                       clearControlls();



                                   }
                                   });

//        up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Filechooser();
//            }
//        });
//        up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fileuploader();
//            }
//        });

        }

//        private String getExtension(Uri uri)
//        {
//            ContentResolver cr = getContentResolver();
//            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//            return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
//        }
//
//        private void Fileuploader()
//        {
//            String imgid = "";
//            imgid = System.currentTimeMillis()+"."+getExtension(imguri);
//            itm.setImgid(imgid);
//            dbref.push().setValue(itm);
//
//            StorageReference Ref = mStorageRef.child(imgid);
//
//            Ref.putFile(imguri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            // Get a URL to the uploaded content
//                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                            Toast.makeText(add_item.this,"Image Uploaded Successfully",Toast.LENGTH_LONG).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception exception) {
//                            // Handle unsuccessful uploads
//                            // ...
//                        }
//                    });
//
//
//
//        }
//
//        private void Filechooser()
//        {
//            Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(intent,1);
//        }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!= null)
//        {
//            imguri=data.getData();
//            img.setImageURI(imguri);
//        }
//    }

    public void clearControlls()
    {
        id.setText("");
        name.setText("");
        ingre.setText("");
        description.setText("");
//        type.setText("");
        price.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getId() == R.id.spinnerItemType)
        {
            String valuefromSpinner = adapterView.getItemAtPosition(position).toString();
            type.getText().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


//    private boolean validate() {
//        boolean valid = true;
//
//        String vid = id.getText().toString();
//        String vname = name.getText().toString();
//        String vingre = ingre.getText().toString();
//        String vdescription = description.getText().toString();
//        String vtype = type.getText().toString();
//        int vprice = Integer.parseInt(price.getText().toString());
//
//        if (id.isEmpty() || name.length() < 3) {
//            id.setError("at least 3 characters");
//            valid = false;
//        } else {
//            et_username.setError(null);
//        }
//
//        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            et_email.setError("enter a valid email address");
//            valid = false;
//        } else {
//            et_email.setError(null);
//        }
//        if (phone.isEmpty() || !isPhoneNumberValid(phone)) {
//            et_phone.setError("enter a valid phone number with your country code");
//            valid = false;
//        } else {
//            et_phone.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//            et_password.setError("between 4 and 10 alphanumeric characters");
//            valid = false;
//        } else {
//            et_password.setError(null);
//        }
//
//        return valid;
//    }
//
//    public static boolean isPhoneNumberValid(String phoneNumber) {
//
//        boolean valid = true;
//        String regex = "^(?:0091|\\+91|0)[7-9][0-9]{9}";
//
//        if (!phoneNumber.matches(regex)) {
//            valid = false;
//        }
//        return valid;
//    }
//
//    private boolean isEmpty(EditText etText) {
//        if (etText.getText().toString().trim().length() > 0)
//            return false;
//
//        return true;
//    }


}

