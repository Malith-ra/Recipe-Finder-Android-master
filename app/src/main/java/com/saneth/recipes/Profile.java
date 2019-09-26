package com.saneth.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    CardData myDataReg;


    private TextView pname, pemail, pphone, pgender;
    private String nameGet, emailGet, phoneGet, genderGet;
    Button deledit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        pname = (TextView) findViewById(R.id.nameGet);
        pemail = (TextView) findViewById(R.id.emailGet);
        pphone = (TextView) findViewById(R.id.phoneGet);
        pgender = (TextView) findViewById(R.id.genderGet);
        deledit = (Button) findViewById(R.id.deledit);


        pname.setText(CardData.loginuserName);
        pgender.setText(CardData.loginuserGender);
        pemail.setText(CardData.loginuserEmail);
        pphone.setText(CardData.loginuserPhone);

        deledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this,Dmo.class);
                startActivity(i);
            }
        });
    }
}
