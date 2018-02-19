package com.example.nashwaa.e_commerce;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nashwaa on 12/1/2017.
 */

public class OrderDetails extends AppCompatActivity{
    Cursor Matched;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);

        final ecommerce_database shopping=new ecommerce_database(getApplicationContext());
        final cart_database cart=new cart_database(getApplicationContext());


        final String UsernameExtra=getIntent().getExtras().getString("UserName");


        final TextView productname= (TextView)findViewById(R.id.productname);
        final TextView price= (TextView)findViewById(R.id.price);
        Button addtocart=(Button)findViewById(R.id.buy);
        final String products=(getIntent().getExtras().getString("ProductName"));

        int id=shopping.get_ID(products);
        Cursor cursor= shopping.ProductInfo(products);
        while (!cursor.isAfterLast())
        {
            productname.setText(cursor.getString(0));
            price.setText(cursor.getString(1));

            cursor.moveToNext();
        }
         addtocart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                         shopping.AddtoCart(productname.getText().toString(), price.getText().toString(),UsernameExtra.toString());

                         Toast.makeText(OrderDetails.this, "Added to Cart!", Toast.LENGTH_SHORT).show();
                         shopping.subtract(productname.getText().toString());
                 Intent newintent=new Intent(OrderDetails.this,AfterLogin.class);
                 newintent.putExtra("UserName",UsernameExtra);
                 startActivity(newintent);

                 //Intent intent=new Intent(OrderDetails.this,ShoppingCart.class);
                 //intent.putExtra("ProName",products);
                 //startActivity(intent);
             }
         });

    }
}
