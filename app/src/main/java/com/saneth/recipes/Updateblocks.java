package com.saneth.recipes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Updateblocks extends AppCompatActivity {
    EditText editText1,editText2;
    Button updatez;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateblocks);


        editText1 = findViewById(R.id.usernameblockzupdatez);
        editText2 = findViewById(R.id.usernumberblockzupdatez);

        Intent intent = getIntent();

        final String getid= intent.getStringExtra("updateid");
        String getusername= intent.getStringExtra("username");
        String getusernumber= intent.getStringExtra("usernumber");

        editText2.setText(getusername);
        editText1.setText(getusernumber);


        updatez = findViewById(R.id.updatezblocking);



        updatez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedname = editText1.getText().toString();
                String editednumber = editText2.getText().toString();

                int usernamelength = editedname.length();
                int blocknumnamesleng = editednumber.length();
                try {
                    if(editedname.equals("") || editednumber.equals("")){

                        Toast.makeText(Updateblocks.this, "Please Fill All Values To Continue...!", Toast.LENGTH_SHORT).show();
                    }else if(!(blocknumnamesleng == 10)){
                        Toast.makeText(Updateblocks.this, "Please Enter Valid Telephone Number...!", Toast.LENGTH_SHORT).show();

                    }else if(usernamelength > 25){
                        Toast.makeText(Updateblocks.this, "Please Enter Valid User Name...!", Toast.LENGTH_SHORT).show();

                    }else {
                        CardData helper = new CardData(Updateblocks.this);
                        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();

                        helper.updatedata1(editedname, editednumber,getid,sqLiteDatabase);

                        Toast.makeText(Updateblocks.this, "Data Updated..!", Toast.LENGTH_SHORT).show();
                        Intent ii = new Intent(Updateblocks.this, MainActivity.class);
                        startActivity(ii);

                    }
                }catch (Exception ee){
                    Toast.makeText(Updateblocks.this, "Error"+ee, Toast.LENGTH_SHORT).show();

                }
            }
        });







    }
}
