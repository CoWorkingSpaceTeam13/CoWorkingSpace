package com.example.nashwaa.e_commerce;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Nashwaa on 12/9/2017.
 */

public class SignUp extends AppCompatActivity {

Calendar ca;
    int day,year,month;
     Button calendar;
       EditText birthdate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);

        final ecommerce_database shopping=new ecommerce_database(getApplicationContext());
        final EditText username=(EditText)findViewById(R.id.New_UserName);
        final   EditText password=(EditText)findViewById(R.id.New_Password_);
        final   EditText gender=(EditText)findViewById(R.id.New_Gender);
        final   EditText job=(EditText)findViewById(R.id.Job);
         birthdate=(EditText)findViewById(R.id.Date);
         calendar=(Button)findViewById(R.id.Calendar);

        Button done=(Button)findViewById(R.id.SignUp_Done);
birthcalendar();
username.requestFocus();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_= username.getText().toString();
                String password_= password.getText().toString();
                String gender_= gender.getText().toString();
                String job_= job.getText().toString();
                String birthdate_= birthdate.getText().toString();

                if(username_.length()!=0 &&password_.length()!=0&&gender_.length()!=0&&job_.length()!=0&&birthdate_.length()!=0) {
                   //
                    Cursor validate= shopping.User(username_);
                    if(validate.getCount()!=0)
                    {
                        Toast.makeText(SignUp.this, "This Username already exists!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        shopping.SignUp(username_, password_, gender_, job_, birthdate_);
                    Toast.makeText(SignUp.this, "Done!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignUp.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
            }

        });


    }

    private void birthcalendar() {
        ca= Calendar.getInstance();
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;

                        birthdate.setText(dayOfMonth+ "/" + month +"/"+ year);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }
}