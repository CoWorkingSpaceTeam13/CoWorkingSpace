package com.example.nashwaa.e_commerce;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nashwaa on 12/1/2017.
 */

public class Cart extends AppCompatActivity{
    ArrayAdapter<String> Cart_arr;
    ecommerce_database shopping;

    TextView totalprice;
    String UserNameExtra;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

      shopping   = new ecommerce_database(getApplicationContext());
       // final cart_database cart = new cart_database(getApplicationContext());

        final TextView prods = (TextView) findViewById(R.id.textView);
        totalprice  = (TextView) findViewById(R.id.totalPrice);
        final ListView Cart = (ListView) findViewById(R.id.Cart);
        Button submit=(Button)findViewById(R.id.Submit);

        UserNameExtra=getIntent().getExtras().getString("UserName");

       final ArrayList<String> thelist=new ArrayList<>();



        Cursor cursor = shopping.ShowCart(UserNameExtra.toString());

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Cart.this,Submit.class);
        startActivity(intent);
    }
});
       /* Cart.setAdapter(Cart_arr);
        Cursor cursor = shopping.ShowCart();
        while (!cursor.isAfterLast()) {
            Cart_arr.add(cursor.getString(0));
           // Cart_arr.notifyDataSetChanged();
            cursor.moveToNext();*/
       int total=0;
       while(!cursor.isAfterLast())
       {
           String first=cursor.getString(0);
           String second=cursor.getString(1);
           Cursor cur=shopping.getPrice_(first);
           String third=cur.getString(0);
         //  String third=cursor.getString(2);
           //thelist.add(cursor.getString(0));
           thelist.add("Product Name:"+first + "," +"Quantity:"+ second + "," +"Price:"+ third );
           Cart_arr = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,thelist);
           Cart.setAdapter(Cart_arr);
            total+=Integer.parseInt(third)*Integer.parseInt(second);
           totalprice.setText(""+total);
           cursor.moveToNext();
       }
       registerForContextMenu(Cart);

        }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.context,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String selectedItem=((TextView)info.targetView).getText().toString();



        String[] prod=selectedItem.split(":");
        String[] prod_=prod[1].split(",");
        String ProName=prod_[0];


       Cursor proinfo= shopping.ProductInfo(ProName);
        int id=item.getItemId();
        if(id==R.id.remove)
        {



            shopping.RemovefromCart(ProName);
            Cart_arr.remove(selectedItem);
            return true;
        }
        if(id==R.id.edit)
        {

            Intent intent=new Intent(Cart.this,EditCart.class);
            intent.putExtra("ProName",proinfo.getString(0));
            intent.putExtra("Price",proinfo.getString(1));

            startActivity(intent);
            return true;
        }



        return super.onContextItemSelected(item);

    }

}
