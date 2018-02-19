//package com.example.nashwaa.e_commerce;

/**
 * Created by Nashwaa on 12/4/2017.
 */

/*public class ForgetPassword extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        final ecommerce_database shopping1=new ecommerce_database(getApplicationContext());

       // Button search=(Button)findViewById(R.id.Search_Forget);
        //final EditText username_= (EditText)findViewById(R.id.UserName2);
        //final EditText gender_=(EditText)findViewById(R.id.Gender2);
        //search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username2 = username_.getText().toString();
                //  String gender2=gender_.getText().toString();
               /* if(username2.length()!=0 && gender2.length()!=0)
                {
                  //  Cursor user=shopping1.ForgetPassword(username2,gender2);
                    Cursor user=shopping1.ForgetPassword(username2);
                    if(user.getCount()!=0)
                    {
                        Intent new_1= new Intent(ForgetPassword.this,ChangePassword.class);
                        startActivity(new_1);
                    }
                    else
                    {
                        Toast.makeText(ForgetPassword.this, "Please Enter a valid username or gender", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(ForgetPassword.this, "Please Enter UserName and Gender..", Toast.LENGTH_LONG).show();
                }*/
/*try {
    Intent sendEmail = new Intent(Intent.ACTION_SEND);
    sendEmail.setData(Uri.parse("mailto"));
    sendEmail.setType("message/rfc822");

    sendEmail.putExtra(Intent.EXTRA_EMAIL, username2);
    sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Return Password");
    sendEmail.putExtra(Intent.EXTRA_TEXT, "6666");
    startActivity(sendEmail);
}
catch (Exception e)
{
    Toast.makeText(ForgetPassword.this, "Try Again", Toast.LENGTH_LONG).show();
}
*/

            //}
        //});
    //}

//}
//*/