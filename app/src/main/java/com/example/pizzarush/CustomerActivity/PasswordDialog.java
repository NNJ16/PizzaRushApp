package com.example.pizzarush.CustomerActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.pizzarush.Entity.CustomerUtil;
import com.example.pizzarush.R;

public class PasswordDialog extends AppCompatDialogFragment {
    private EditText txtOpwd,txtNpwd,txtCpwd;
    private PasswordDialogListner listner;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        txtOpwd = view.findViewById(R.id.txtOpwd);
        txtNpwd = view.findViewById(R.id.txtNpwd);
        txtCpwd = view.findViewById(R.id.txtCpwd);

        builder.setView(view)
                .setTitle("Reset Password")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String Opwd = txtOpwd.getText().toString();
                        String Npwd = txtNpwd.getText().toString();
                        String Cpwd = txtCpwd.getText().toString();
                        String pass = CustomerUtil.getPwd();

                        if(Opwd.equals(pass))
                        {
                            if(Npwd.equals(Cpwd))
                            {
                                listner.applyText(Npwd);
                            }else
                            {
                                Toast.makeText(getContext(),"Password not matched",Toast.LENGTH_SHORT).show();
                            }
                        }else
                        {
                            Toast.makeText(getContext(),"Old password is invalid",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return builder.create();
    }
    public interface PasswordDialogListner
    {
        void applyText(String pwd);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listner = (PasswordDialogListner) context;
    }
}
