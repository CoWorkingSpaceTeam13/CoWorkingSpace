package com.example.nashwaa.e_commerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ecommerce_database shopping=new ecommerce_database(getApplicationContext());


        final EditText name= (EditText) findViewById(R.id.UserName);
        final   EditText passw= (EditText) findViewById(R.id.Password);
        final TextView custid=(TextView)findViewById(R.id.custid);


        final CheckBox rememberme=(CheckBox)findViewById(R.id.checkBox);
        SharedPreferences sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
        final SharedPreferences.Editor editorPrefrences=sharedPreferences.edit();
        Boolean info=sharedPreferences.getBoolean("Info",false);
        if(info==true)
        {
            name.setText(sharedPreferences.getString("UserName",""));
            passw.setText(sharedPreferences.getString("Password",""));
            rememberme.setChecked(true);
        }

        Button login=(Button)findViewById(R.id.Login);
        Button signup=(Button)findViewById(R.id.SignUp);

        TextView forgpw=(TextView)findViewById(R.id.Forgetpassword) ;

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
  //              shopping.makeorder();
//                int max=shopping.getMaxid();
        final     String username= name.getText().toString();
        final        String password=passw.getText().toString();

                if(rememberme.isChecked())
                {
                    editorPrefrences.putBoolean("Info",true);
                    editorPrefrences.putString("UserName",username);
                    editorPrefrences.putString("Password",password);
                    editorPrefrences.commit();
                }
               else
                {
                    editorPrefrences.clear();
                    editorPrefrences.commit();
                }
                if (username.length()!=0 && password.length()!=0)
                {
                    Cursor validate= shopping.Login(username,password);
                    if(validate.getCount()!=0)
                    {
                     //   int id=shopping.CustID(name.getText().toString());
                       shopping.AfterLogin(name.getText().toString());
                        //int id=shopping.OrdID();
                        //custid.setText(""+id);
                       Intent new_=new Intent(MainActivity.this,AfterLogin.class);
                       new_.putExtra("UserName",username);
                        startActivity(new_);
                    }
                    else
                        Toast.makeText(MainActivity.this, "Invalid UserName or Password!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Enter UserName and Password..", Toast.LENGTH_SHORT).show();
                }

            }
        });

forgpw.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent new_=new Intent(MainActivity.this,ForgetPassword2.class);
        startActivity(new_);
    }
});

    }
}
