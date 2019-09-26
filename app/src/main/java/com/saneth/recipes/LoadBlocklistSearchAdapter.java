package com.saneth.recipes;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoadBlocklistSearchAdapter extends ArrayAdapter {
    Context c;
    ArrayList<LoadBlockNumbersSearch> list;
    public LoadBlocklistSearchAdapter(@NonNull Context context, ArrayList<LoadBlockNumbersSearch> ar) {

        super(context, R.layout.loadblocknumberscutom,ar);
        c = context;
        list =ar;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.loadblocknumberscutom,null);
        LoadBlockNumbersSearch loadnumberzlists = list.get(position);

        final TextView loadtxtnumber = v.findViewById(R.id.userblockname);
        loadtxtnumber.setText(loadnumberzlists.blockname);


        final TextView loadmessagez = v.findViewById(R.id.userblocknumber);
        loadmessagez.setText(loadnumberzlists.blocknumber);

        final TextView loadmessageidz = v.findViewById(R.id.hiddenidblock);
        loadmessageidz.setText(loadnumberzlists.blockidid);

        Button btn = v.findViewById(R.id.btndeleteblockz);
        Button btn2 = v.findViewById(R.id.btnupdate);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent uu = new Intent(c,Updateblocks.class);
                uu.putExtra("usernumber",loadtxtnumber.getText().toString());
                uu.putExtra("username",loadmessagez.getText().toString());
                uu.putExtra("updateid",loadmessageidz.getText().toString());
               c.startActivity(uu);

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CardData helper = new CardData(c);
                    SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();

                    helper.deletedata1(loadmessageidz.getText().toString(),sqLiteDatabase);
                    Toast.makeText(c, "Data Deleted", Toast.LENGTH_SHORT).show();
                    Intent ii = new Intent(c,MainActivity.class);
                    c.startActivity(ii);
                }catch (Exception e){

                    Toast.makeText(c, "erroooo"+e, Toast.LENGTH_SHORT).show();

                }

            }
        });



        return v;
    }
}
