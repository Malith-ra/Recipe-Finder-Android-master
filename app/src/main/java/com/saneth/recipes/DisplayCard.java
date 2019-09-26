package com.saneth.recipes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.saneth.recipes.ConstantsMalith.AVAILABILITY;
import static com.saneth.recipes.ConstantsMalith.CARD_NAME;
import static com.saneth.recipes.ConstantsMalith.TABLE_NAME;

public class DisplayCard extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private CardData products1;
    private ArrayList<String> product_names, availability_status;
    private static String[] SELECT = {CARD_NAME};
    private ProductsListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_card);


        //Hide status bar if android version is Kitkat or higher than Kitkat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        listView = (ListView) findViewById(R.id.listview);
        listView.setFadingEdgeLength(50);


        products1 = new CardData(this);
        product_names = getProductNames();

        availability_status = new ArrayList<>();

        //initiating the availability states of products
        for (int i = 0; i < product_names.size(); i++) {
            availability_status.add(getAvailability(i));
        }

        //creating list adapter
        listAdapter = new ProductsListAdapter(this, product_names);
        //set all checkboxes unchecked
        listAdapter.setAllChecked(false);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);



    }




    //function to get product names from db
    private ArrayList<String> getProductNames() {

        SQLiteDatabase db = products1.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, SELECT, null, null, null, null, CARD_NAME);

        ArrayList<String> product_names = new ArrayList<>();

        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            product_names.add(name);
        }
        return product_names;
    }

    //function to set availability state of product
    public void setAvailability(View view) {

        SQLiteDatabase db = products1.getWritableDatabase();

        for (int i = 0; i < availability_status.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(AVAILABILITY, availability_status.get(i));
            db.update(TABLE_NAME, values, "name1='" + product_names.get(i) + "'", null);
            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(DisplayCard.this, DisplayProducts.class);

        startActivity(intent);
    }

    //function to get availability state of a given item
    public String getAvailability(int position) {

        String availability = "";
        SQLiteDatabase db = products1.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + AVAILABILITY + " FROM " + TABLE_NAME + " WHERE " + CARD_NAME + "='" + product_names.get(position) + "'", null);

        while (c.moveToNext()) {
            availability = c.getString(0);
        }
        return availability;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CheckBox checkBox = view.findViewById(R.id.check_box);
        checkBox.setChecked(!checkBox.isChecked());     //changing the state of checkbox when item clicked
        listAdapter.setNotifyOnChange(true);

        //updating availability state by considering the checkbox state
        String status;
        if (checkBox.isChecked()) {
            status = "Available";
        } else {
            status = "Not Available";
        }
        availability_status.set(position, status);
    }
}
