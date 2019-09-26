package com.saneth.recipes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.saneth.recipes.Constants.DESCRIPTION;
import static com.saneth.recipes.Constants.PRICE;
import static com.saneth.recipes.Constants.PRODUCT_NAME;
import static com.saneth.recipes.Constants.WEIGHT;
import static com.saneth.recipes.ConstantsMalith.AVAILABILITY;
import static com.saneth.recipes.ConstantsMalith.CARD_NAME;
import static com.saneth.recipes.ConstantsMalith.CARD_NUMBER;
import static com.saneth.recipes.ConstantsMalith.CVC;
import static com.saneth.recipes.ConstantsMalith.EXPIRY_DATE;
import static com.saneth.recipes.ConstantsMalith.TABLE_NAME;
//import static com.saneth.recipes.Constants.WEIGHT;


public class CardData extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "products1.db";
    private static final int DATABASE_VERSION = 1;

//    public static final String TABLE_NAME ="Registration_Table";
//    public static final String COL_1 ="ID";
//    public static final String COL_2 ="Name";
//    public static final String COL_3 ="Gender";
//    public static final String COL_4 ="Email";
//    public static final String COL_5 ="Phone";
//    public static final String COL_6 ="Password";

    static String loginuserID;
    static String loginuserName;
    static String loginuserGender;
    static String loginuserEmail;
    static String loginuserPhone;
    static String loginuserPassword;


    public CardData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( " CREATE TABLE " + TABLE_NAME + " ( "
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + CARD_NAME + " TEXT , "
                + CARD_NUMBER + " DOUBLE ,"
                + CVC +" DOUBLE ,"
                + EXPIRY_DATE +" TEXT ,"
                + AVAILABILITY + " TEXT ) ; " );


        db.execSQL( " CREATE TABLE " + Constants.TABLE_NAME + " ( "
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + PRODUCT_NAME + " TEXT , "
                + WEIGHT + " DOUBLE ,"
                + PRICE +" DOUBLE ,"
                + DESCRIPTION +" TEXT ,"
                + Constants.AVAILABILITY + " TEXT ) ; " );

        String sqlque ="create table Registration_Table(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,GENDER TEXT, EMAIL TEXT, PHONE INTEGER, PASSWORD TEXT)";
        db.execSQL(sqlque);


        String sql ="CREATE TABLE USERMASSAGE(id INTEGER PRIMARY KEY AUTOINCREMENT,USERNUMBER TEXT ,MESSAGEDETAILS TEXT,STATUSZ TEXT)";
        db.execSQL(sql);


        String sql1 ="CREATE TABLE USERBLOCK(id INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT ,USERNUMBER TEXT,STATUSZ TEXT)";
        db.execSQL(sql1);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }






    public void insertdata(String messagenumber, String messagedetails, SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put("USERNUMBER",messagenumber);
        values.put("MESSAGEDETAILS",messagedetails);
        values.put("STATUSZ","Sent");
        database.insert("USERMASSAGE",null,values);

    }

    public void deletedata(String messageid, SQLiteDatabase database){

        String deleteque ="DELETE FROM USERMASSAGE WHERE id='"+messageid+"'";
        database.execSQL(deleteque);

    }


    public void insertdata1(String username,String usernumber,SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put("USERNAME",username);
        values.put("USERNUMBER",usernumber);
        values.put("STATUSZ","Sent");
        database.insert("USERBLOCK",null,values);

    }
    public void updatedata1(String username,String usernumber,String blockidz,SQLiteDatabase database){

        String updateque ="UPDATE  USERBLOCK SET USERNAME='"+username+"',USERNUMBER='"+usernumber+"'  WHERE id='"+blockidz+"'";
        database.execSQL(updateque);

    }

    public void deletedata1(String messageid,SQLiteDatabase database){

        String deleteque ="DELETE FROM USERBLOCK WHERE id='"+messageid+"'";
        database.execSQL(deleteque);

    }









    public void insertData2(String name, String gender, String email, int phone, String pw){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("NAME", name);
        val.put("GENDER", gender);
        val.put("EMAIL", email);
        val.put("PHONE", phone);
        val.put("PASSWORD", pw);
        sqLiteDatabase.insert("Registration_Table", null, val);
    }

    public Cursor ViewLoginData(String email, String pw){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor1 = sqLiteDatabase.rawQuery("Select * FROM Registration_Table Where Email='"+email+"' and Password='"+pw+"'", new String[]{});
        return cursor1;

    }

    public void updatedata2(String teleno,String usernamez,String emailz,SQLiteDatabase database){

        String updateque ="UPDATE  Registration_Table SET Phone='"+teleno+"',Name='"+usernamez+"',Email='"+emailz+"' WHERE ID='"+ CardData.loginuserID +"'";

        database.execSQL(updateque);

    }
    public void deleteData2(SQLiteDatabase database){

        String updateque ="DELETE FROM  Registration_Table WHERE ID='"+ CardData.loginuserID +"'";

        database.execSQL(updateque);

    }

}
