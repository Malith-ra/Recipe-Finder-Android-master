package com.saneth.recipes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewblocklist extends AppCompatActivity {
    EditText usernametype;
    Button searchbtn;
    ListView listView;
    ArrayList<LoadBlockNumbers> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewblocklist);


        usernametype =findViewById(R.id.typenameuser);
        searchbtn =findViewById(R.id.searchblockuser);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uu = new Intent(viewblocklist.this,viewblocklistsearch.class);
                uu.putExtra("Searchingtxtblock",usernametype.getText().toString());
                startActivity(uu);

            }
        });

        listView = (ListView) findViewById(R.id.blocklistvi);
        arrayList = new ArrayList<LoadBlockNumbers>();

        try {
            CardData helper = new CardData(viewblocklist.this);
            SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT id,USERNAME,USERNUMBER,STATUSZ FROM USERBLOCK",new String[]{});
            if(cursor == null){
                //  Toast.makeText(this, "empty cursor", Toast.LENGTH_SHORT).show();

            }else {
                while (cursor.moveToNext()) {

                    //Toast.makeText(this, "Inside", Toast.LENGTH_SHORT).show();
                    LoadBlockNumbers lm = new LoadBlockNumbers();
                    lm.blockidid = cursor.getString(0);
                    lm.blockname = cursor.getString(1);
                    lm.blocknumber = cursor.getString(2);
//                Toast.makeText(this, "number--"+lm.messagenumber, Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "message--"+lm.messagedetails, Toast.LENGTH_SHORT).show();
                    arrayList.add(lm);

                }

                LoadBlocklistAdapter my = new LoadBlocklistAdapter(this, arrayList);
                listView.setAdapter(my);

        }
        }catch (Exception e){
            Toast.makeText(this, "error--"+e, Toast.LENGTH_SHORT).show();
        }


    }
}
