package com.example.nashwaa.e_commerce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by Nashwaa on 12/1/2017.
 */

public class Submit extends AppCompatActivity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit);
        EditText address=(EditText)findViewById(R.id.address);
        EditText date=(EditText)findViewById(R.id.date);







}}
