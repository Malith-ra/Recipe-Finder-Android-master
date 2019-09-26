package com.saneth.recipes;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import static com.saneth.recipes.ConstantsMalith.AVAILABILITY;
import static com.saneth.recipes.ConstantsMalith.CARD_NAME;
import static com.saneth.recipes.ConstantsMalith.CARD_NUMBER;
import static com.saneth.recipes.ConstantsMalith.CVC;
import static com.saneth.recipes.ConstantsMalith.EXPIRY_DATE;
import static com.saneth.recipes.ConstantsMalith.TABLE_NAME;


public class CardDetails extends AppCompatActivity {

    private EditText name_txt, weight_txt, price_txt, description_txt;
    private CardData products;
    private String productName, description1, availability1;
    private double weight1, price1;
    private Spinner spinner;
    private ImageButton delete_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        //Hide status bar if android version is Kitkat or higher than Kitkat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        name_txt = findViewById(R.id.name_txt);
        weight_txt = findViewById(R.id.weight_txt);
        price_txt = findViewById(R.id.price_txt);
        description_txt = findViewById(R.id.description_txt);
        spinner = findViewById(R.id.spinner);
        delete_btn = findViewById(R.id.delete_btn);

        //setting options for the spinner
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinnerOptions,
                R.layout.spinner_item);

        spinner.setAdapter(adapter);

        //show confirmation dialog when delete button clicked
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });


        products = new CardData(this);

        productName = getIntent().getStringExtra("card.name");
        getProductDetails(productName);

        name_txt.setText(productName);
        weight_txt.setText("" + weight1);
        price_txt.setText(price1 + "");
        description_txt.setText(description1);
        if (availability1.equals("Available")) {
            spinner.setSelection(0);
        } else {
            spinner.setSelection(1);
        }
    }


    //function to update records
    public void updateProduct(View view) {
        SQLiteDatabase db = products.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CARD_NAME, name_txt.getText().toString().trim());
        values.put(CARD_NUMBER, weight_txt.getText().toString().trim());
        values.put(CVC, price_txt.getText().toString().trim());
        values.put(EXPIRY_DATE, description_txt.getText().toString().trim());
        values.put(AVAILABILITY, spinner.getSelectedItem().toString().trim());

        db.update(TABLE_NAME, values, "name1= '" + productName + "'", null);
        Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show();
    }

    //function to get details of a selected product
    private void getProductDetails(String productName) {

        SQLiteDatabase db = products.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "
                + CARD_NAME + ", "
                + CARD_NUMBER + ", "
                + CVC + ", "
                + EXPIRY_DATE + ", "
                + AVAILABILITY
                + " FROM " + TABLE_NAME
                + " WHERE " + CARD_NAME + "='" + productName + "'", null);

        while (cursor.moveToNext()) {
            weight1 = cursor.getDouble(1);
            price1 = cursor.getDouble(2);
            description1 = cursor.getString(3);
            availability1 = cursor.getString(4);
        }
        cursor.close();
    }

    //function to delete records
    private void deleteProduct() {
        SQLiteDatabase db = products.getReadableDatabase();
        db.delete(TABLE_NAME, CARD_NAME + "='" + productName + "'", null);
    }

    //function to show confirmation alert when deleting a product
    public void showAlert() {

        AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(CardDetails.this);

        myAlertBuilder.setTitle("Confirm Delete");
        myAlertBuilder.setMessage("Are You Sure You Want to Delete this Product");

        myAlertBuilder.setPositiveButton("OK", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteProduct();
                        Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }
                });
        myAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        myAlertBuilder.show();

    }
}
