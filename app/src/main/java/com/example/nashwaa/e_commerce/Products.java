package com.example.nashwaa.e_commerce;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Nashwaa on 12/1/2017.
 */

public class Products extends AppCompatActivity{


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);

        final ecommerce_database shopping=new ecommerce_database(getApplicationContext());
        TextView all_prod= (TextView)findViewById(R.id.All_Products);

        final String UsernameExtra=getIntent().getExtras().getString("UserName");


        String productss=(getIntent().getExtras().getString("ProductName"));
        all_prod.setText(productss);
        final ListView Product=(ListView)findViewById(R.id.All_Prod);
        ArrayAdapter<String> Product_arr=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);

        Product.setAdapter(Product_arr);

        Cursor categories= shopping.getAllProducts(productss);
        while (!categories.isAfterLast())
        {
            Product_arr.add(categories.getString(0));
            categories.moveToNext();
        }
        Product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected_= (String) Product.getItemAtPosition(position);
                String selected_category= (String) Product.getItemAtPosition(position);

                Intent product= new Intent(Products.this,OrderDetails.class);

                product.putExtra("UserName",UsernameExtra);
                product.putExtra("ProductName",  selected_);
                startActivity(product);
            }
        });

    }
}
