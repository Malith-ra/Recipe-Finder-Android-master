package com.saneth.recipes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewblocklistsearch extends AppCompatActivity {


    ListView listView;
    ArrayList<LoadBlockNumbersSearch> arrayList;
    ArrayAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewblocklistsearch);


        listView = (ListView) findViewById(R.id.messagelistsearchblock);
        arrayList = new ArrayList<LoadBlockNumbersSearch>();

        try {

            CardData helper = new CardData(viewblocklistsearch.this);
            SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
            Intent intent = getIntent();

            String getting= intent.getStringExtra("Searchingtxtblock");
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT id,USERNAME,USERNUMBER,STATUSZ FROM USERBLOCK WHERE USERNAME LIKE'%"+getting+"%'",new String[]{});
            if(cursor == null){
                //  Toast.makeText(this, "empty cursor", Toast.LENGTH_SHORT).show();

            }else {
                while (cursor.moveToNext()) {

                    //Toast.makeText(this, "Inside", Toast.LENGTH_SHORT).show();
                    LoadBlockNumbersSearch lm = new LoadBlockNumbersSearch();
                    lm.blockidid = cursor.getString(0);
                    lm.blockname = cursor.getString(1);
                    lm.blocknumber = cursor.getString(2);
//                Toast.makeText(this, "number--"+lm.messagenumber, Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "message--"+lm.messagedetails, Toast.LENGTH_SHORT).show();
                    arrayList.add(lm);

                }

                LoadBlocklistSearchAdapter my = new LoadBlocklistSearchAdapter(this, arrayList);
                listView.setAdapter(my);
            }

        }catch(Exception e){
            Toast.makeText(this, "errrr"+e, Toast.LENGTH_SHORT).show();

        }





    }
}
