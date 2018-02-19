package com.example.nashwaa.e_commerce;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nashwaa on 12/4/2017.
 */

public class EditCart extends AppCompatActivity {

    EditText quantproduct;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_cart);

        final ecommerce_database shopping=new ecommerce_database(getApplicationContext());
        EditText product_name=(EditText)findViewById(R.id.productname);
        final EditText price=(EditText)findViewById(R.id.price);
         quantproduct=(EditText)findViewById(R.id.Productquantity);

        Button increment=(Button)findViewById(R.id.inc);
        Button decrement=(Button)findViewById(R.id.dec);
        final TextView quantity=(TextView)findViewById(R.id.quantity);
        final TextView total=(TextView)findViewById(R.id.total);
        final TextView quant=(TextView)findViewById(R.id.quant);
        final TextView proquant=(TextView)findViewById(R.id.prodquan);
       final Button done=(Button)findViewById(R.id.done);


       String Proname=getIntent().getExtras().getString("ProName");

        int quantityofproduct=shopping.selectQuantityproduct(Proname);
        product_name.setText(Proname);
        price.setText(getIntent().getExtras().getString("Price"));
        quantproduct.setText(""+quantityofproduct);
        final String pro=product_name.getText().toString();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quanti=  quant.getText().toString();
                int quanti_=Integer.parseInt(quanti);
                shopping.decQuantProd(pro,quanti_);

                Cursor cursor_=shopping.selectQuantityProd(pro);
                if(!cursor_.isAfterLast())
                {
                    proquant.setText(cursor_.getString(0));
                    cursor_.moveToNext();
                }
            }
        });

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldValue=quantity.getText().toString();
                int newValue=Integer.parseInt(oldValue);
                newValue=newValue+1;
                quantity.setText(""+newValue);
                String Price_=price.getText().toString();
                int intPrice=Integer.parseInt(Price_);
                String quants=quantity.getText().toString();
                int quants_=Integer.parseInt(quants);

                int totalprice=intPrice*quants_;
                total.setText(""+totalprice);

                shopping.incremenetquantityorder(pro,quants_);
                Cursor cursor=shopping.selectQuantity(pro);
                if(!cursor.isAfterLast())
                {
                    quant.setText(cursor.getString(0));
                    cursor.moveToNext();
                }
            //    shopping.decQuantProd(pro,quants_);

            //    Cursor cursor_=shopping.selectQuantityProd(pro);
              //  if(!cursor_.isAfterLast())
                //{
                 //   proquant.setText(cursor_.getString(0));
                   // cursor_.moveToNext();
                //}

            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldValue=quantity.getText().toString();
                int newValue=Integer.parseInt((oldValue));
                if(newValue==1)
                {
                    Toast.makeText(EditCart.this, "Error!", Toast.LENGTH_SHORT).show();
                }
                else {
                    newValue = newValue - 1;
                    quantity.setText("" + newValue);
                    String Price_=price.getText().toString();
                    int intPrice=Integer.parseInt(Price_);
                    String quants=quantity.getText().toString();
                    int quants_=Integer.parseInt(quants);

                    int totalprice=intPrice*quants_;
                    total.setText(""+totalprice);

                    shopping.insertQuantity(pro,quants_);
                    Cursor cursor=shopping.selectQuantity(pro);
                    if(!cursor.isAfterLast())
                    {
                        quant.setText(cursor.getString(0));
                        cursor.moveToNext();
                    }
                }

            }
        });


    }

}
