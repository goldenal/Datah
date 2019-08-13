package com.example.adeosunadewale.datah;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adeosunadewale.datah.data.VicroseContract;



public class Display_list extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

        Uri currenturi;
        private static final int V_LOADER = 0;
        Vcursoradapter vcursoradapter;
        Vadapter vadapter;
        Cursor cursor;
    public static final String LOG_TAG = Display_list.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list);
        Log.e(LOG_TAG,"the log of wood" + currenturi);
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.bggrad);
        actionBar.setBackgroundDrawable(drawable);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(drawable);
        }


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), New_data.class);
                startActivity(intent);
          }
        });
        ListView myView = (ListView) findViewById(R.id.list);
        View emptyview = findViewById(R.id.emptyv);
        myView.setEmptyView(emptyview);
        myView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(LOG_TAG,"the log "+ id );
                Intent intent = new Intent(Display_list.this,DisplayInDetails.class);
                currenturi = ContentUris.withAppendedId(VicroseContract.VicEntry.CONTENT_URI,id);
                intent.setData(currenturi);

                startActivity(intent);

            }
        });
        myView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {


                return false;
            }

        });

        String[] projection = {VicroseContract.VicEntry.COLUMN_NAME, VicroseContract.VicEntry._ID};
         cursor = getContentResolver().query(VicroseContract.VicEntry.CONTENT_URI,projection,null,null,
                VicroseContract.VicEntry.COLUMN_NAME + " ASC");

        vcursoradapter = new Vcursoradapter(this, cursor);
        vadapter = new Vadapter(this,cursor);
        vadapter.notifyDataSetChanged();
        myView.setEmptyView(emptyview);
        myView.setAdapter(vcursoradapter);
        vadapter.updateAdapter(cursor);
       getLoaderManager().initLoader(V_LOADER,null,this);



    }
    @Override
    public void onStart() {

        super.onStart();
        vadapter.updateAdapter(cursor);
        vadapter.notifyDataSetChanged();
    }



   @Override
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
      String[] projection = {
             VicroseContract.VicEntry.COLUMN_NAME, VicroseContract.VicEntry._ID
      };
        return new CursorLoader(this, VicroseContract.VicEntry.CONTENT_URI,projection,null,null,VicroseContract.VicEntry.COLUMN_NAME + " ASC");


    }



    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        vcursoradapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        vcursoradapter.swapCursor(null);

    }
}
