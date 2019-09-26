package com.saneth.recipes;



        import android.support.v7.app.AppCompatActivity;

        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.util.Patterns;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.Toast;

        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class Dinusha extends AppCompatActivity {

    CardData myDataReg;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private EditText pname, pemail, pphone, ppassword, pcpassword, psel;
    private String name, email, phone, password, cpassword, sel;
    Button regbtn;
    RadioButton maleRadioButton, femaleRadioButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinusha);
//        getSupportActionBar().setTitle("Registration");

        pname = (EditText) findViewById(R.id.name);
        pemail = (EditText) findViewById(R.id.email);
        pphone = (EditText) findViewById(R.id.phone);
        ppassword = (EditText) findViewById(R.id.password);
        pcpassword = (EditText) findViewById(R.id.cpassword);
        regbtn = (Button) findViewById(R.id.regbtn);
        maleRadioButton = (RadioButton) findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.femaleRadioButton);





        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register(); //call when the button is clicked to validate input
            }



        });


    }

    public void register(){
        initialize(); //initialize the input to string variables
        if(!validate()) {
            Toast.makeText(this, "Registration failed!!", Toast.LENGTH_SHORT).show();
        }
        else{
            onRegistrationSuccess();
        }
    }

    public void onRegistrationSuccess(){
        myDataReg = new CardData(Dinusha.this);
//        SQLiteDatabase sqLiteDatabase = myDataReg.getReadableDatabase();

        SQLiteDatabase sqLiteDatabase = myDataReg.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * FROM Registration_Table Where Email='"+pemail.getText().toString()+"'", new String[]{});
        if(cursor.moveToNext()){
            Toast.makeText(this, "This Email Is Already Registered...!", Toast.LENGTH_SHORT).show();
        }else{

            Cursor cursor2 = sqLiteDatabase.rawQuery("Select * FROM Registration_Table Where Phone='"+phone+"'", new String[]{});
            if(cursor2.moveToNext()){
                Toast.makeText(this, "This Telephone Number Is Already Registered...!", Toast.LENGTH_SHORT).show();
            }else{
                myDataReg.insertData2(pname.getText().toString(), sel, pemail.getText().toString(), Integer.parseInt(phone), ppassword.getText().toString());

                Toast.makeText(this, "Registration Successful!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Dinusha.this, Login.class);
                startActivity(i);

            }
        }



    }


    public boolean validate(){
        boolean val = true;


        if(name.isEmpty()){
            pname.setError("Name can't be blank!");
            pname.requestFocus();
            val = false;
        }

        if(name.length()>15){
            pname.setError("Name too long!");
            pname.requestFocus();
            val = false;
        }

        if (maleRadioButton.isChecked() || femaleRadioButton.isChecked()) {
            //  Toast.makeText(this, "Gender selected!!", Toast.LENGTH_SHORT).show();

            if(maleRadioButton.isChecked()){
                sel = "Male";
            }
            else {
                sel = "Female";
            }
        }

        else{
            Toast.makeText(this, "Select gender!!", Toast.LENGTH_SHORT).show();
            val = false;
        }



        if(email.isEmpty()){
            pemail.setError("Email can't be blank!");
            pemail.requestFocus();
            val = false;
        }

        if(phone.isEmpty()){
            pphone.setError("Phone can't be blank!");
            pphone.requestFocus();
            val = false;
        }

        if(phone.length()<10){
            pphone.setError("Enter a valid number!");
            pphone.requestFocus();
            val = false;
        }

        if(password.isEmpty()){
            ppassword.setError("Password can't be blank!");
            ppassword.requestFocus();
            val = false;
        }

        if(cpassword.isEmpty()){
            pcpassword.setError("Confirm password can't be blank!");
            pcpassword.requestFocus();
            val = false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            pemail.setError("Enter valid email address!");
            pemail.requestFocus();
            val = false;
        }

        if(!isValidPassword(ppassword.getText().toString())){
            ppassword.setError("Password must contain lower,upper case letters,digits and special characters!");
            ppassword.requestFocus();
            val = false;
        }

        if(!password.equals(cpassword)){
            pcpassword.setError("Passwords doesn't matches!");
            pcpassword.requestFocus();
            val = false;
        }


        return val;

    }

    private boolean isValidPassword(String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }

    public void initialize(){
        name = pname.getText().toString().trim();
        email = pemail.getText().toString().trim();
        phone = pphone.getText().toString().trim();
        password = ppassword.getText().toString().trim();
        cpassword = pcpassword.getText().toString().trim();
//        sel = psel.getText().toString().trim();
    }

}
