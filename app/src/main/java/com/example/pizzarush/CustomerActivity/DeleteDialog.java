package com.example.pizzarush.CustomerActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteDialog extends AppCompatDialogFragment {
    DatabaseReference dbRef;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_delete,null);

        builder.setView(view)
                .setTitle("Remove Account")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Customer");
                        DatabaseReference delRefP = FirebaseDatabase.getInstance().getReference().child("Point");
                        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String cid = CustomerUtil.getCid();
                                if(dataSnapshot.hasChild(cid))
                                {
                                    dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(cid);
                                    dbRef.removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        delRefP.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String cid = CustomerUtil.getCid();
                                if(dataSnapshot.hasChild(cid))
                                {
                                    dbRef = FirebaseDatabase.getInstance().getReference().child("Point").child(cid);
                                    dbRef.removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        Intent intent = new Intent(getContext(),login.class);
                        getContext().startActivity(intent);
                    }

                });

        return builder.create();
    }
}


