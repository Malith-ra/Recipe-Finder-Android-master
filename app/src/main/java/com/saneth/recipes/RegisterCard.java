


package com.saneth.recipes;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import static com.saneth.recipes.ConstantsMalith.AVAILABILITY;
import static com.saneth.recipes.ConstantsMalith.CARD_NAME;
import static com.saneth.recipes.ConstantsMalith.CARD_NUMBER;
import static com.saneth.recipes.ConstantsMalith.CVC;
import static com.saneth.recipes.ConstantsMalith.EXPIRY_DATE;
import static com.saneth.recipes.ConstantsMalith.TABLE_NAME;

//import static com.saneth.recipes.Constants.DESCRIPTION;
//import static com.saneth.recipes.Constants.PRICE;
//import static com.saneth.recipes.Constants.PRODUCT_NAME;
//import static com.saneth.recipes.Constants.WEIGHT;

public class RegisterCard extends AppCompatActivity {

    private CardData products1;
    private EditText name_txt, weight_txt, price_txt, description_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_card);

        //Hide status bar if android version is Kitkat or higher than Kitkat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        name_txt = findViewById(R.id.name_txt);
        weight_txt = findViewById(R.id.weight_txt);
        price_txt = findViewById(R.id.price_txt);
        description_txt = findViewById(R.id.description_txt);

        products1 = new CardData(this);
    }

    //function to add product to the database
    public void registerProduct(View view) {
        String name_text = name_txt.getText().toString().trim();
        if (!name_text.equals("")) {
            SQLiteDatabase db = products1.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CARD_NAME, name_text);
            values.put(CARD_NUMBER, weight_txt.getText().toString().trim());
            values.put(CVC, price_txt.getText().toString().trim());
            values.put(EXPIRY_DATE, description_txt.getText().toString().trim());
            values.put(AVAILABILITY, "");
            db.insertOrThrow(TABLE_NAME, null, values);
            Toast.makeText(this, "Registration Successful  ", Toast.LENGTH_LONG).show();
        } else {
            showAlert();
        }
    }

    //function to show alert if product name is invalid
    public void showAlert() {

        AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(RegisterCard.this);

        myAlertBuilder.setTitle("Invalid Details");         //setting alert title
        myAlertBuilder.setMessage("Name Cannot Be Empty");      //setting alert message

        myAlertBuilder.setPositiveButton("OK", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        myAlertBuilder.show();

    }
}
