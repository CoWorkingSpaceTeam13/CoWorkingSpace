package com.example.nashwaa.e_commerce;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Nashwaa on 12/1/2017.
 */

public class AfterLogin extends AppCompatActivity{
    ArrayAdapter<String> Catg_arr;
  //  private String[] Catg;
    private final int REQ_CODE_SEARCH_OUTPUT=143;
      EditText search;
    String UsernameExtra;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_shopping);

        final ecommerce_database shopping=new ecommerce_database(getApplicationContext());
        final TextView prods=(TextView)findViewById(R.id.textView);
        final ListView Catg=(ListView)findViewById(R.id.All_Catg);
       search=(EditText) findViewById(R.id.searchView);
        Button mic=(Button) findViewById(R.id.micro);

         UsernameExtra=getIntent().getExtras().getString("UserName");



        Catg_arr=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);


        Catg.setAdapter(Catg_arr);

        Cursor categories= shopping.getAllCategories();
        while (!categories.isAfterLast())
        {
            Catg_arr.add(categories.getString(0));
            categories.moveToNext();
        }

      search.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {

              AfterLogin.this.Catg_arr.getFilter().filter(s);
          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });
        Catg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               String selected_= (String) Catg.getItemAtPosition(position);

               Intent product= new Intent(AfterLogin.this,Products.class);
               product.putExtra("UserName",UsernameExtra);


                product.putExtra("ProductName",  selected_);
                startActivity(product);

            }
        });
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Now!");
                try
                {
                       startActivityForResult(intent,REQ_CODE_SEARCH_OUTPUT);
                }
                catch (ActivityNotFoundException tim)
                {
                    Toast.makeText(AfterLogin.this, "No Matched Results", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case REQ_CODE_SEARCH_OUTPUT:
                if(resultCode==RESULT_OK&&null!=data)
                {
                    ArrayList<String> text=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    search.setText(text.get(0));
                }
                  break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(AfterLogin.this,Cart.class);
        intent.putExtra("UserName",UsernameExtra);
                startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
