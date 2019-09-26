package com.saneth.recipes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Hide status bar if android version is Kitkat or higher than Kitkat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        LinearLayout register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(this);

//        LinearLayout display_btn = findViewById(R.id.display_btn);
//        display_btn.setOnClickListener(this);

//        LinearLayout availability_btn = findViewById(R.id.availability_btn);
//        availability_btn.setOnClickListener(this);

        LinearLayout edit_btn = findViewById(R.id.edit_btn);
        edit_btn.setOnClickListener(this);

        LinearLayout search_btn = findViewById(R.id.search_btn);
        search_btn.setOnClickListener(this);

//        LinearLayout recipes_btn = findViewById(R.id.recipes_btn);
//        recipes_btn.setOnClickListener(this);
//
//
//        LinearLayout search_btn1 = findViewById(R.id.recipes_btn1);
//        search_btn1.setOnClickListener(this);
//
//        LinearLayout search_btn2 = findViewById(R.id.recipes_btn2);
//        search_btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.register_btn:
                Intent intent1 = new Intent(Home.this,RegisterProduct.class);
                startActivity(intent1);
                break;
//            case  R.id.display_btn:
//                Intent intent2 = new Intent(MainActivity.this, DisplayCard.class);
//                startActivity(intent2);
//                break;
//            case  R.id.availability_btn:
//                Intent intent3 = new Intent(MainActivity.this,Availability.class);
//                startActivity(intent3);
//                break;
            case  R.id.edit_btn:
                Intent intent4 = new Intent(Home.this,EditProduct.class);
                startActivity(intent4);
                break;
            case  R.id.search_btn:
                Intent intent5 = new Intent(Home.this,SearchProduct.class);
                startActivity(intent5);
                break;
//            case  R.id.recipes_btn:
//                Intent intent6 = new Intent(MainActivity.this,Recipes.class);
//                startActivity(intent6);
//                break;
//
//
//            case  R.id.recipes_btn1:
//                Intent intent7 = new Intent(MainActivity.this,PasinduMain.class);
//                startActivity(intent7);
//                break;
//
//            case  R.id.recipes_btn2:
//                Intent intent8 = new Intent(MainActivity.this,BlockMain.class);
//                startActivity(intent8);
//                break;
        }
    }


}
