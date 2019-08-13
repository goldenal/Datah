package com.example.adeosunadewale.datah;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adeosunadewale.datah.data.VicroseContract;
import com.squareup.picasso.Picasso;

public class DisplayInDetails extends AppCompatActivity {
    Uri currenturi;
    Toast mtoast;
    private String name;
    ImageView imageView;
    int del;
    public static final String LOG_TAG = DisplayInDetails.class.getSimpleName();
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Drawable drawable = getResources().getDrawable(R.drawable.bggrad);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(drawable);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(drawable);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayindetails);


        imageView = findViewById(R.id.imageView);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;

       // Picasso.get().load(R.drawable.newback).resize(width,width*1/2).centerCrop().into(imageView);
         Intent intent = getIntent();
        currenturi = intent.getData();
        setedit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.display_menu, menu);
        return true;
    }
    @Override
    protected void onStart(){
        super.onStart();
        setedit();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()){
            case R.id.menu_delete:
               deleteWarning(deleteOpt);
               return true;

            case R.id.menu_edit:
                edit();
                return true;
            case R.id.menu_showdel:
                deleteWarning(deleteOpt);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    DialogInterface.OnClickListener deleteOpt = new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialogInterface, int i ){
            del = getContentResolver().delete(currenturi,null,null);
            deleted();
            finish();
        }
    };
    private void deleteWarning(DialogInterface.OnClickListener deleteOpt){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete " + name);
        builder.setPositiveButton("yes", deleteOpt);
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void edit(){
        Intent intent = new Intent(DisplayInDetails.this,New_data.class);
       intent.setData(currenturi);
        startActivity(intent);
    }
    public void deleted(){
        if(del==1){
           mtoast = Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT);
            mtoast.show();

        }else{
           mtoast= Toast.makeText(this,"Unable to delete",Toast.LENGTH_SHORT);
            mtoast.show();
        }

    }
    private void setedit(){
        String[] projection = {
                VicroseContract.VicEntry._ID,VicroseContract.VicEntry.COLUMN_NAME,VicroseContract.VicEntry.COLUMN_SI,
                VicroseContract.VicEntry.COLUMN_GL, VicroseContract.VicEntry.COLUMN_BK,VicroseContract.VicEntry.COLUMN_Wst,
                VicroseContract.VicEntry.COLUMN_Bst,VicroseContract.VicEntry.COLUMN_UndBst,VicroseContract.VicEntry.COLUMN_lb,
                VicroseContract.VicEntry.COLUMN_Sw,VicroseContract.VicEntry.COLUMN_Slv,VicroseContract.VicEntry.COLUMN_Knel,
                VicroseContract.VicEntry.COLUMN_FB
        };
        if (currenturi != null) {
            cursor = getContentResolver().query(currenturi, projection, null, null, null);
            cursor.moveToFirst();


            try {
                name = cursor.getString(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_NAME));
                float si = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_SI));
                float gl = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_GL));
                float bk = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_BK));
                float wst = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Wst));
                float bst = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Bst));
                float undbst = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_UndBst));
                float lb = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_lb));
                float sw = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Sw));
                float slv = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Slv));
                float knel = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Knel));
                float fb = cursor.getFloat(cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_FB));


                  TextView nview =  findViewById(R.id.textView3);


                TextView sivals =  findViewById(R.id.siVALUE);
                TextView glvals =  findViewById(R.id.glVALUE);
                TextView bkvals = findViewById(R.id.bkVALUE);
                TextView wstvals = findViewById(R.id.wstVALUE);
                TextView bstvals = findViewById(R.id.bstVALUE);
                TextView undbstvals =  findViewById(R.id.underbstVAL);
                TextView lbvals =  findViewById(R.id.lbVALUE);
                TextView swvals =  findViewById(R.id.swVALUE);
                TextView slvvals =  findViewById(R.id.slvValue);
                TextView knelvals =  findViewById(R.id.knelVALUE);
                TextView fbvals =  findViewById(R.id.fbVALUE);



                sivals.setText(""+si);
                glvals.setText(""+gl);
                bkvals.setText(""+bk);
                wstvals.setText(""+wst);
                bstvals.setText(""+bst);
                undbstvals.setText(""+undbst);
                lbvals.setText(""+lb);
                swvals.setText(""+sw);
                slvvals.setText(""+slv);
                knelvals.setText(""+knel);
                fbvals.setText(""+fb);

                nview.setText(name);



            } finally {
                cursor.close();
            }
        }

    }

}
