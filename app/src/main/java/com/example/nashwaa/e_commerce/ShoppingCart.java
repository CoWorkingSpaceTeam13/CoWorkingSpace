package com.example.nashwaa.e_commerce;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Nashwaa on 12/9/2017.
 */

public class ShoppingCart extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shopping_cart);

      final  ecommerce_database ecommerce=new ecommerce_database(getApplicationContext());
      final String buy=getIntent().getExtras().getString("ProName");
        final TextView product=(TextView)findViewById(R.id.product1);
        final TextView quantity=(TextView)findViewById(R.id.quantity);
        final TextView total=(TextView)findViewById(R.id.total);
        Button increment=(Button)findViewById(R.id.inc);
        Button decrement=(Button)findViewById(R.id.dec);

        product.setText(buy);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldValue=quantity.getText().toString();
                int newValue=Integer.parseInt((oldValue));
                newValue=newValue+1;
                quantity.setText(""+newValue);
                String newOne=quantity.getText().toString();
                int newOneint=Integer.parseInt(newOne);
            Cursor quants= ecommerce.getPrice(product.getText().toString());
                while (!quants.isAfterLast())
                {
                  String price=  quants.getString(0);
                    int priceint=Integer.parseInt(price);
                    int totalprice=priceint*newOneint;
                    total.setText(""+totalprice);
                    quants.moveToNext();
                }
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldValue=quantity.getText().toString();
                int newValue=Integer.parseInt((oldValue));
                newValue=newValue-1;
                quantity.setText(""+newValue);
            }
        });

    }
}
