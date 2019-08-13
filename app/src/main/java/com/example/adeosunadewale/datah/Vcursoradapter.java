package com.example.adeosunadewale.datah;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.adeosunadewale.datah.data.VicroseContract;

/**
 * Created by ADEOSUN ADEWALE on 12/4/2017.
 */

public class Vcursoradapter extends CursorAdapter {
    private Context mcontext;
    private Cursor mcursor;
    private int mrow[];
    private static final int s_state = 1;
    private static final int u_state = 2;
    String[] projection = {VicroseContract.VicEntry.COLUMN_NAME, VicroseContract.VicEntry._ID};

    public Vcursoradapter(Context context, Cursor cursor) {

        super(context, cursor, 0);
        mcontext = context;
        mcursor  = cursor;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tt = (TextView) view.findViewById(R.id.textid);
              TextView textname = (TextView) view.findViewById(R.id.list_name);
        String namev = cursor.getString(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_NAME));
        String egg =  namev.substring(0,1);
        tt.setText(egg);
        textname.setText(namev);
    }

}
