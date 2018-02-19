package com.example.nashwaa.e_commerce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Nashwaa on 12/4/2017.
 */

public class ChangePassword extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        final ecommerce_database shopping=new ecommerce_database(getApplicationContext());
        final EditText newpass= (EditText)findViewById(R.id.New_password);
        Button done=(Button) findViewById(R.id.change_password);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String newPW= newpass.getText().toString();
                if(newPW.length()!=0)
                {
                    shopping.NewPassword(newPW);
                    Toast.makeText(ChangePassword.this, "Done!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ChangePassword.this, "Please enter new password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
