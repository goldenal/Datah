package com.example.adeosunadewale.datah;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.adeosunadewale.datah.data.VicroseContract;

/**
 * Created by ADEOSUN ADEWALE on 1/15/2018.
 */

public class Vadapter extends BaseAdapter{
    String[] projection = {VicroseContract.VicEntry.COLUMN_NAME, VicroseContract.VicEntry._ID};
    private Context mcontext;
    private Cursor mcursor;
    private static final int s_state = 1;
    private static final int u_state = 2;
    private int mrow[];
    public int idno;

    public Vadapter(Context context, Cursor cursor){
        mcontext = context;
        mcursor  = cursor;
        mrow = new int[getCount()];


    }
    @Override
    public int getCount() {
        return mcursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return mcursor.getInt(mcursor.getColumnIndex(projection[1]));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Boolean showseparator = false;
        mcursor.moveToPosition(position);
        if(convertView == null){
            LayoutInflater layoutInflater =(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view = layoutInflater.inflate(R.layout.list_item,null);
        }
        else {
            view = convertView;
        }

        TextView textname = (TextView) view.findViewById(R.id.list_name);
         idno = mcursor.getInt(mcursor.getColumnIndex(projection[1]));
        String namev = mcursor.getString(mcursor.getColumnIndex(projection[0]));

        textname.setText(namev + idno);
        switch (mrow[position]){
            case s_state:
                showseparator = true;
                break;
            case u_state:
                showseparator = false;
                break;
            default:
                if (position == 0){
                    showseparator = true;
                }
                else {
                    mcursor.moveToPosition(position - 1);
                    String p_name = mcursor.getString(mcursor.getColumnIndex(projection[0]));
                    char[] p_nameArray = p_name.toCharArray();
                    char[] nameArray = namev.toCharArray();

                    if (p_nameArray[0] != nameArray[0]){
                        showseparator = true;
                    }
                    mcursor.moveToPosition(position);
                }
                mrow[position] = showseparator ? s_state : u_state;
                break;
        }
     TextView septextView = (TextView) view.findViewById(R.id.seperator);

        if (showseparator){
            septextView.setText(namev.toCharArray(),0 ,1);
            septextView.setVisibility(View.VISIBLE);
        }
        else {
            view.findViewById(R.id.seperator).setVisibility(View.GONE);
        }
        return view;
    }
    public void updateAdapter(Cursor mcursor){
        this.mcursor = mcursor;
        this.notifyDataSetChanged();

    }
    public int getidd(){
        return idno;
    }



}
