package com.saneth.recipes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
public class forwardmessage extends AppCompatActivity {
    Button btn1,btn2;
    EditText sendernumber,sendmessage;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forwardmessage);


        sendernumber = findViewById(R.id.forwardsendnumberz);
        sendmessage = findViewById(R.id.forwardsendmessage);

        btn1 = findViewById(R.id.forwardsendbtn);
        btn2 = findViewById(R.id.forwardviewbtn);

        textView1 = findViewById(R.id.forwardtxtcount);

        Intent intent = getIntent();

        String getting= intent.getStringExtra("messageeka");
        sendmessage.setText(getting);



        sendmessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                int messagelenght = sendmessage.getText().length();
                textView1.setText("Letter Count is "+messagelenght);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //    Toast.makeText(forwardmessage.this, "Clicked Forwrd", Toast.LENGTH_SHORT).show();
                String messagenumberz[] =sendernumber.getText().toString().split(",");
                String messagedetailz = sendmessage.getText().toString();


                int messagelength =messagedetailz.length();
                if(messagedetailz.equals("")){

                    Toast.makeText(forwardmessage.this, "Please Fill All Feilds To Continue..!", Toast.LENGTH_SHORT).show();

                }else if(messagenumberz.length == 0){
                    Toast.makeText(forwardmessage.this, "Please Fill All Feilds To Continue..!", Toast.LENGTH_SHORT).show();

                }else if(messagenumberz.length > 3){
                    Toast.makeText(forwardmessage.this, "Please Fill All Feilds To Continue..!", Toast.LENGTH_SHORT).show();

                }else if(messagelength >65){
                    Toast.makeText(forwardmessage.this, "You Have Exceed the text count.!", Toast.LENGTH_SHORT).show();
                }else{
                    for(int i = 0;i<messagenumberz.length;i++){

                        String nomessa = messagenumberz[i];
                        CardData helper = new CardData(forwardmessage.this);
                        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();

                        helper.insertdata(nomessa,messagedetailz,sqLiteDatabase);



                    }
                    Toast.makeText(forwardmessage.this, "Message Forwarded..!", Toast.LENGTH_SHORT).show();
                    Intent ii = new Intent(forwardmessage.this,Viewmessages.class);
                    startActivity(ii);
                }

            }
        });


    }
}
