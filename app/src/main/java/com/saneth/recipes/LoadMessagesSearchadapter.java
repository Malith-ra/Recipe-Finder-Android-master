package com.saneth.recipes;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class LoadMessagesSearchadapter extends ArrayAdapter {

    Context c;
    ArrayList<LoadMessagesSearch> list;
    public LoadMessagesSearchadapter( Context context, ArrayList<LoadMessagesSearch> ar) {

        super(context, R.layout.loadmessagesz,ar);
        c = context;
        list =ar;

    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.loadmessagesz,null);
        LoadMessagesSearch loadMessages = list.get(position);

        TextView loadtxtnumber = v.findViewById(R.id.messageloadnumber);
        loadtxtnumber.setText(loadMessages.messagenumber);


        final TextView loadmessagez = v.findViewById(R.id.messageloaadingz);
        loadmessagez.setText(loadMessages.messagedetails);

        final TextView loadmessageidz = v.findViewById(R.id.hiddenidz);
        loadmessageidz.setText(loadMessages.messageid);

        Button btn = v.findViewById(R.id.btndelete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  Toast.makeText(c, "Message ID--"+loadmessageidz.getText(), Toast.LENGTH_SHORT).show();
                try {
                    CardData helper = new CardData(c);
                    SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();

                    helper.deletedata(loadmessageidz.getText().toString(),sqLiteDatabase);
                    Toast.makeText(c, "Data Deleted", Toast.LENGTH_SHORT).show();
                    Intent ii = new Intent(c,MainActivity.class);
                    c.startActivity(ii);
                }catch (Exception e){

                    Toast.makeText(c, "erroooo"+e, Toast.LENGTH_SHORT).show();

                }


            }
        });

        Button btnforward = v.findViewById(R.id.frwardbtn);
        btnforward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yy = new Intent(c,forwardmessage.class);
                yy.putExtra("messageeka",loadmessagez.getText().toString());
                c.startActivity(yy);

            }
        });


        return v;
    }
}
