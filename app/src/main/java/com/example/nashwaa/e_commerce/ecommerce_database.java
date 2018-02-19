package com.example.nashwaa.e_commerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nashwaa on 12/1/2017.
 */

public class ecommerce_database extends SQLiteOpenHelper {
    private static String databaseName="onlineShopping";
    SQLiteDatabase Ecommerce;

    public ecommerce_database(Context context) {
        super(context, databaseName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Customers(CustomerID integer primary key autoincrement, CutName text, Username text,"+" Password text, Email text, Gender text, BirthDate text,Job text)");
        db.execSQL("create table Categories(CatID integer primary key autoincrement, "+" CatName text not null)");
        db.execSQL("create table Products(ProID integer primary key autoincrement, ProName text not null, Price text, Quantity integer, CatID integer, FOREIGN KEY(CatID) REFERENCES Categories(CatID))");
        db.execSQL("create table Order_Details(OrdID integer   , ProID integer,ProName text not null, "+" Quantity integer, Primary key(ProID,OrdID), FOREIGN KEY (ProID)REFERENCES Products(ProID) , FOREIGN KEY (OrdID) REFERENCES Orders(OrdID))");
        db.execSQL("create table Orders( OrdID integer primary key autoincrement,OrderDate text, Address text ,"+" CustomerID integer, FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID)) ");

        db.execSQL("insert into Customers(Username,Password,Email,Gender) values('mahmoud','1310','nashwa.elhamamsy@hotmail.com','male')");
        db.execSQL("insert into Customers(Username,Password,Email,Gender) values('Mohamed','123','nashwa.elhamamsy@hotmail.com','male')");
        db.execSQL("insert into Customers(Username,Password,Email,Gender) values('Ahmed','1234','nashwa.elhamamsy@hotmail.com','male')");
        db.execSQL("insert into Categories(CatID,CatName) values (1,'Mobile phones')");
        db.execSQL("insert into Categories(CatID,CatName) values (2,'Laptops')");
        db.execSQL("insert into Categories(CatID,CatName) values (3,'Tablets')");
        db.execSQL("insert into Products(ProID,ProName,Price,Quantity,CatID) values(1,'Inifinix','7300',9,1)");
        db.execSQL("insert into Products(ProID,ProName,Price,Quantity,CatID) values(2,'Apple1','23300',8,1)");
        db.execSQL("insert into Products(ProID,ProName,Price,Quantity,CatID) values(3,'Samsung1','15300',7,1)");
        db.execSQL("insert into Products(ProID,ProName,Price,Quantity,CatID) values(4,'HP','25300',7,2)");
        db.execSQL("insert into Products(ProID,ProName,Price,Quantity,CatID) values(5,'Acer','15300',8,2)");
        db.execSQL("insert into Products(ProID,ProName,Price,Quantity,CatID) values(6,'Lenovo2','35300',9,2)");
        db.execSQL("insert into Products(ProID,ProName,Price,Quantity,CatID) values(7,'Samsung2','15800',8,3)");
        db.execSQL("insert into Products(ProID,ProName,Price,Quantity,CatID) values(8,'Lenovo3','10300',7,3)");
        db.execSQL("insert into Products(ProID,ProName,Price,Quantity,CatID) values(9,'Apple2','32200',8,3)");
       // db.execSQL("insert into Order_Details(ProID,Quantity) values(1,8)");
        //db.execSQL("insert into Order_Details(ProID,Quantity) values(2,7)");
        //db.execSQL("insert into Order_Details(ProID,Quantity) values(3,9)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Customers");
        db.execSQL("drop table if exists Categories");
        db.execSQL("drop table if exists Products");
        onCreate(db);

    }
    public void SignUp(String name, String password, String gender, String job, String date)
    {
        ContentValues cust=new ContentValues();
        cust.put("UserName",name);
        cust.put("Password",password);
        cust.put("Gender",gender);
        cust.put("Job",job);
        cust.put("BirthDate",date);
        Ecommerce=getWritableDatabase();
        Ecommerce.insert("Customers",null,cust);
        Ecommerce.close();
    }
    public Cursor User(String name)
    {
        Ecommerce=getReadableDatabase();
        Cursor cursor= Ecommerce.rawQuery("select UserName from Customers where UserName='"+name+"' ",null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor;
    }
    public Cursor Login(String name, String password)
    {

        Ecommerce=getReadableDatabase();
        Cursor cursor= Ecommerce.rawQuery("select * from Customers where UserName='"+name+"' and Password='"+password+"'",null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
         Ecommerce.close();
        return cursor;
    }
    public int CustID(String name)
    {
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select CustomerID from Customers where UserName='"+name+"'",null);

        if(cursor!=null) {
            cursor.moveToFirst();

        }
        Ecommerce.close();
        return cursor.getInt(0);
    }
    public void AfterLogin(String name)
    {
        int id=CustID(name);
        ContentValues cust=new ContentValues();
        cust.put("CustomerID",id);

        Ecommerce=getWritableDatabase();
        Ecommerce.insert("Orders",null,cust);
        Ecommerce.close();
    }
    public int OrdID(String name)
    {
         int id=CustID(name);
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select OrdID from Orders where CustomerID='"+id+"' ",null);

        if(cursor!=null)
            cursor.moveToFirst();


        Ecommerce.close();
        return cursor.getInt(0);
    }
  /*  public Cursor ForgetPassword(String name, String gender)
    {
        Ecommerce=getReadableDatabase();
        Cursor users=Ecommerce.rawQuery("select * from Customers where UserName='"+name+"' and Gender='"+gender+"'",null);
        if(users!=null)
        {
            users.moveToFirst();
        }
        Ecommerce.close();
        return users;
    }*/
  /*  public Cursor ForgetPassword(String name)
    {
        Ecommerce=getReadableDatabase();
        Cursor users=Ecommerce.rawQuery("select Password from Customers where UserName='"+name+"' ",null);
        if(users!=null)
        {
            users.moveToFirst();
        }
        Ecommerce.close();
        return users;
    }*/
    public void NewPassword(String pw)
    {
        Ecommerce=getWritableDatabase();

        String change="update Customers set Password=?";
        Ecommerce.execSQL(change,new String[]{pw});
    }
public Cursor ProductInfo(String name)
{
  // int id=proid(name);

    Ecommerce=getReadableDatabase();
   // Cursor info=Ecommerce.rawQuery("select ProName,Price from Products where ProName='"+name+"'",null);
    Cursor info=Ecommerce.rawQuery("select ProName,Price from Products where ProName='"+name+"'  ",null);
    if(info!=null) {
        info.moveToFirst();
    }
    Ecommerce.close();
    return info;
}
    public Cursor getAllCategories()
    {
        Ecommerce=getReadableDatabase();
        String[] row={"CatName"};
        Cursor cursor= Ecommerce.query("Categories",row,null,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor;
    }
    public Cursor getAllProducts(String name)
    {
        int ID=getID(name);
        Ecommerce=getReadableDatabase();
        Cursor product=Ecommerce.rawQuery("select ProName from Products where CatID='"+ID+"'",null);
        if(product!=null) {
            product.moveToFirst();
        }
        Ecommerce.close();
        return product;
    }
    public int getID(String name)
    {
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select CatID from Categories where CatName='"+name+"' ",null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor.getInt(0);
    }
    public int get_ID(String name)
    {
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select CatID from Products where ProName='"+name+"' ",null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor.getInt(0);
    }

   public int getProductID(String name)
   {
      // int CatId=getID(name);
       Ecommerce=getReadableDatabase();
       Cursor cursor=Ecommerce.rawQuery("select ProID from Products where ProName='"+name+"' ",null);
       if(cursor!=null)
           cursor.moveToFirst();
       Ecommerce.close();
       return cursor.getInt(0);
   }
 /*  public Cursor getQuantity(String name)
   {
       int ID=getProductID(name);
       Ecommerce=getReadableDatabase();
       Cursor quantity=Ecommerce.rawQuery("select Quantity from Order_Details where ProID='"+ID+"'",null);
       if(quantity!=null) {
           quantity.moveToFirst();
       }
       Ecommerce.close();
       return quantity;
   }
*/
    public Cursor getPrice(String name)
    {
        int ID=getProductID(name);
        Ecommerce=getReadableDatabase();
        Cursor price=Ecommerce.rawQuery("select Price from Products where ProID='"+ID+"'",null);
        if(price!=null) {
            price.moveToFirst();
        }
        Ecommerce.close();
        return price;
    }
    public Cursor getPrice_(String name)
    {

        Ecommerce=getReadableDatabase();
        Cursor price=Ecommerce.rawQuery("select Price from Products where ProName='"+name+"'",null);
        if(price!=null) {
            price.moveToFirst();
        }
        Ecommerce.close();
        return price;
    }
    public int getIDProd(String name,String price)
    {
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select ProID from Products where ProName='"+name+"' And Price='"+price+"'",null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor.getInt(0);
    }
    public void AddtoCart(String name, String price,String username )
    {
        int ID=getIDProd(name,price);
        int OrdID=OrdID(username);
        ContentValues row=new ContentValues();

        row.put("OrdID",OrdID);
        row.put("ProID",ID);
        row.put("ProName",name);
        row.put("Quantity",1);
        //row.put("Price",price);

        Ecommerce=getWritableDatabase();
        Ecommerce.insert("Order_Details",null,row);
        Ecommerce.close();
    }


    public Cursor ShowCart(String username)
    {
      // int proid=getOrderDetailsID();
        int OrdID=OrdID(username);
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select ProName,Quantity from Order_Details where OrdID='"+OrdID+"' ",null);

        if(cursor!=null) {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor;

    }
    public  int getOrderDetailsID()
    {
        Ecommerce=getReadableDatabase();
        //Cursor cursor=Ecommerce.rawQuery("select ProName from Products where ProID='"+ProID+"'",null);
        Cursor cursor=Ecommerce.rawQuery("select ProID from Order_Details  ",null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor.getInt(0);
    }
 public void RemovefromCart(String name)
 {
     Ecommerce=getWritableDatabase();
     Ecommerce.delete("Order_Details","ProName='"+name+"'",null);
     Ecommerce.close();
 }
    public Cursor getProductName(String name)
    {
        // int proid=getOrderDetailsID();
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select ProName from Order_Details where ProName='"+name+"'",null);

        if(cursor!=null) {
            cursor.moveToFirst();
            Ecommerce.close();
            return cursor;
        }

      Ecommerce.close();
        return null;
    }
    public void insertQuantity(String name,int quant)
    {
        Ecommerce=getWritableDatabase();
        String update="Update Order_Details set Quantity=? where ProName like ?";
        Ecommerce.execSQL(update,new String[] {String.valueOf(quant),name});

    }

    public Cursor selectQuantity(String name)
    {
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select Quantity from Order_Details where ProName='"+name+"'",null);
        if (cursor!=null)
        {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor;
    }
    public int selectQuantityOrder(String name)
    {
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select Quantity from Order_Details where ProName='"+name+"'",null);
        if (cursor!=null)
        {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor.getInt(0);
    }
    public int selectQuantPro(String name)
    {
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select Quantity from Products where ProName='"+name+"'",null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor.getInt(0);
    }
    public void decQuantProd(String name, int quant)
    {

       int quants=selectQuantPro(name);
       int newQauntity=quants-quant;

        Ecommerce=getWritableDatabase();
        String update="Update Products set Quantity=? where ProName like ?";
        Ecommerce.execSQL(update,new String[]{String.valueOf(newQauntity),name});

    }
    public void incremenetquantityorder(String name, int quant)
    {
        int quantss=selectQuantityOrder(name);
        int newQauntity_=quantss+quant;

        Ecommerce=getWritableDatabase();
        String update="Update Order_Details set Quantity=? where ProName like ?";
        Ecommerce.execSQL(update,new String[]{String.valueOf(newQauntity_),name});
    }
    public Cursor selectQuantityProd(String name)
    {
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select Quantity from Products where ProName='"+name+"'",null);
        if (cursor!=null)
        {
            cursor.moveToFirst();
        }
        Ecommerce.close();
        return cursor;
    }
   /* public int getMaxid()
    {
        Ecommerce=getReadableDatabase();
        Cursor cursor=Ecommerce.rawQuery("select * from Orders where OrdID=Max(OrdID)",null);

            cursor.moveToLast();
        Ecommerce.close();

        return cursor.getInt(0);

    }
    public void makeorder()
    {
        ContentValues cust=new ContentValues();

        String hello="hello";
        cust.put("OrdID",1);
        cust.put("Address",hello);
        Ecommerce=getWritableDatabase();
        Ecommerce.insert("Orders",null,cust);
        Ecommerce.close();
    }
    */
   public int selectQuantityproduct(String name)
   {
       Ecommerce=getReadableDatabase();
       Cursor cursor=Ecommerce.rawQuery("select Quantity from Products where ProName='"+name+"'",null);
       if(cursor!=null)
       {
           cursor.moveToFirst();
       }
       Ecommerce.close();
       return cursor.getInt(0);
   }
   public void subtract(String name)
   {
       int quantity=selectQuantityproduct(name);
       quantity=quantity-1;
       Ecommerce=getWritableDatabase();
       String update="Update Products set Quantity=? where ProName like ?";
       Ecommerce.execSQL(update,new String[]{String.valueOf(quantity),name});
   }

}

