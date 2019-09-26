package com.saneth.recipes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;

public class Dmo extends AppCompatActivity {

    TextView phoneOrg,name,emailz;
    EditText phonenew,emailnew,namenew;
    Button update,delete;
    CardData mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmo);

        phoneOrg = (TextView) findViewById(R.id.phoneOrg);
        name = (TextView) findViewById(R.id.nameGet);
        emailz = (TextView) findViewById(R.id.emailGet);


        phoneOrg.setText(CardData.loginuserPhone);
        name.setText(CardData.loginuserName);
        emailz.setText(CardData.loginuserEmail);



        phonenew =  findViewById(R.id.edPhoneupdate);
        namenew =  findViewById(R.id.nameupdate);
        emailnew =  findViewById(R.id.emailupdate);


        update = findViewById(R.id.editno);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    mydb = new CardData(Dmo.this);
                    SQLiteDatabase sqLiteDatabase = mydb.getReadableDatabase();

                    mydb.updatedata2(phonenew.getText().toString(),namenew.getText().toString(),emailnew.getText().toString(),sqLiteDatabase);



                    Toast.makeText(Dmo.this, "Data Updated..!", Toast.LENGTH_SHORT).show();
                    Intent ii = new Intent(Dmo.this,Login.class);


                    startActivity(ii);

                }catch (Exception ee){
                    Toast.makeText(Dmo.this, "error"+ee, Toast.LENGTH_SHORT).show();

                }
            }
        });
        delete = findViewById(R.id.delno);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb = new CardData(Dmo.this);
                SQLiteDatabase sqLiteDatabase = mydb.getReadableDatabase();

                mydb.deleteData2(sqLiteDatabase);

                Toast.makeText(Dmo.this, "Data Deleted..!", Toast.LENGTH_SHORT).show();
                Intent ii = new Intent(Dmo.this,Login.class);


                startActivity(ii);
            }
        });







    }
}
