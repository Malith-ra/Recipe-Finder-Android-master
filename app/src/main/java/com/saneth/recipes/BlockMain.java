package com.saneth.recipes;



        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class BlockMain extends AppCompatActivity {
    Button sendbtn,viewbtn;
    EditText userblockname,userblocknumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_main);


        userblockname = findViewById(R.id.usernameblockz);
        userblocknumber = findViewById(R.id.usernumberblockz);


        sendbtn = findViewById(R.id.addblocklist);
        viewbtn = findViewById(R.id.viewblocks);




        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String userz = userblockname.getText().toString();
                    String blocknumb = userblocknumber.getText().toString();

                    int usernamelength = userz.length();
                    int blocknumnamesleng = blocknumb.length();

                    if(userz.equals("") || blocknumb.equals("")){

                        Toast.makeText(BlockMain.this, "Please Fill All Values To Continue...!", Toast.LENGTH_SHORT).show();
                    }else if(!(blocknumnamesleng == 10)){
                        Toast.makeText(BlockMain.this, "Please Enter Valid Telephone Number...!", Toast.LENGTH_SHORT).show();

                    }else if(usernamelength > 25){
                        Toast.makeText(BlockMain.this, "Please Enter Valid User Name...!", Toast.LENGTH_SHORT).show();

                    }else {
                        CardData helper = new CardData(BlockMain.this);
                        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();

                        helper.insertdata1(userz, blocknumb, sqLiteDatabase);

                        Toast.makeText(BlockMain.this, "Number Added To Blocklist..!", Toast.LENGTH_SHORT).show();
                        Intent ii = new Intent(BlockMain.this, BlockMain.class);
                        startActivity(ii);

                    }


                }catch (Exception ee){
                    Toast.makeText(BlockMain.this, "erorr"+ee, Toast.LENGTH_SHORT).show();

                }


            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(BlockMain.this,viewblocklist.class);
                startActivity(ii);

            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}
