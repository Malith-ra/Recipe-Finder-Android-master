package com.saneth.recipes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewMessagesSearch extends AppCompatActivity {
    ListView listView;
    ArrayList<LoadMessagesSearch> arrayList;
    ArrayAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_messages_search);


        listView = (ListView) findViewById(R.id.messagelistsearch);
        arrayList = new ArrayList<LoadMessagesSearch>();
        try {

            CardData helper = new CardData(ViewMessagesSearch.this);
            SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
            Intent intent = getIntent();

            String getting= intent.getStringExtra("Searchingtxt");
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT id,USERNUMBER,MESSAGEDETAILS,STATUSZ FROM USERMASSAGE WHERE MESSAGEDETAILS LIKE'%"+getting+"%'",new String[]{});
            if(cursor == null){
                //  Toast.makeText(this, "empty cursor", Toast.LENGTH_SHORT).show();

            }else {
                while (cursor.moveToNext()) {

                    //Toast.makeText(this, "Inside", Toast.LENGTH_SHORT).show();
                    LoadMessagesSearch lm = new LoadMessagesSearch();
                    lm.messageid = cursor.getString(0);
                    lm.messagenumber = cursor.getString(1);
                    lm.messagedetails = cursor.getString(2);
//                Toast.makeText(this, "number--"+lm.messagenumber, Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "message--"+lm.messagedetails, Toast.LENGTH_SHORT).show();
                    arrayList.add(lm);

                }

                LoadMessagesSearchadapter my = new LoadMessagesSearchadapter(this, arrayList);
                listView.setAdapter(my);
            }

        }catch(Exception e){
            Toast.makeText(this, "errrr"+e, Toast.LENGTH_SHORT).show();

        }




    }
}
