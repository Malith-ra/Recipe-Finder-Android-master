package com.saneth.recipes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Viewmessages extends AppCompatActivity {


    ListView listView;
    ArrayList<LoadMessages> arrayList;
    ArrayAdapter adp;
    EditText searchname;
    Button searchbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmessages);

        searchname = findViewById(R.id.txttypetxt);
        searchbtn = findViewById(R.id.searchbtnz);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uu = new Intent(Viewmessages.this,ViewMessagesSearch.class);
                uu.putExtra("Searchingtxt",searchname.getText().toString());
                startActivity(uu);
            }
        });


        listView = (ListView) findViewById(R.id.messagelist);
        arrayList = new ArrayList<LoadMessages>();
        try {

            CardData helper = new CardData(Viewmessages.this);
            SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT id,USERNUMBER,MESSAGEDETAILS,STATUSZ FROM USERMASSAGE",new String[]{});
        if(cursor == null){
          //  Toast.makeText(this, "empty cursor", Toast.LENGTH_SHORT).show();

        }else {
            while (cursor.moveToNext()) {

                //Toast.makeText(this, "Inside", Toast.LENGTH_SHORT).show();
                LoadMessages lm = new LoadMessages();
                lm.messageid = cursor.getString(0);
                lm.messagenumber = cursor.getString(1);
                lm.messagedetails = cursor.getString(2);
//                Toast.makeText(this, "number--"+lm.messagenumber, Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "message--"+lm.messagedetails, Toast.LENGTH_SHORT).show();
                arrayList.add(lm);

            }

            Loadmessagesadapter my = new Loadmessagesadapter(this, arrayList);
            listView.setAdapter(my);
        }

        }catch(Exception e){
            Toast.makeText(this, "errrr"+e, Toast.LENGTH_SHORT).show();

        }


    }
}
