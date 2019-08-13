package com.example.adeosunadewale.datah.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by ADEOSUN ADEWALE on 11/15/2017.
 */

public class Vprovider extends ContentProvider{



    public static final String LOG_TAG = Vprovider.class.getSimpleName();
    private static final int custom = 100;
    private static final int custom_id = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    //public static final String CONTENT_AUTHORITY = "com.example.adeosunadewale.vicrosedata.data";
  //  public static final Uri custom_uri = Uri.parse("content://" + CONTENT_AUTHORITY + "/" + VicroseContract.VicEntry.TABLE_NAME);
   // public static final Uri custom_id_uri = Uri.parse("content://"+ CONTENT_AUTHORITY + "/" + VicroseContract.VicEntry.TABLE_NAME +
   //         "/" + custom_id );



    static {


        sUriMatcher.addURI(VicroseContract.CONTENT_AUTHORITY,VicroseContract.PATH_VICROSE,custom);




        sUriMatcher.addURI(VicroseContract.CONTENT_AUTHORITY, VicroseContract.PATH_VICROSE + "/#",custom_id);
    }
    private VicroseDbHelper mdbhelper;
    @Override
    public boolean onCreate() {
        mdbhelper = new VicroseDbHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase data = mdbhelper.getReadableDatabase();
        Cursor cursor ;

        int match = sUriMatcher.match(uri);

        switch (match){

            case custom:
                cursor = data.query(VicroseContract.VicEntry.TABLE_NAME, projection,selection,selectionArgs, null,null,
                        sortOrder);
                break;

            case custom_id:

                selection = VicroseContract.VicEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = data.query(VicroseContract.VicEntry.TABLE_NAME, projection,selection,selectionArgs, null,null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("cannot query unknown uri " + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;

    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case custom:
                return VicroseContract.VicEntry.CONTENT_LIST_TYPE;
            case custom_id:
                return VicroseContract.VicEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown uri" + uri);

        }

    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match =  sUriMatcher.match(uri);
        switch (match){
            case custom:
                return insertVdata(uri,values);
            default:
                throw new IllegalArgumentException("Insertion not suitable");
        }
    }
    private Uri insertVdata(Uri uri, ContentValues values){
        SQLiteDatabase database = mdbhelper.getWritableDatabase();

        long id = database.insert(VicroseContract.VicEntry.TABLE_NAME,null,values);
        if(id == -1){
            Log.e(LOG_TAG, "Failed to insert" +uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);


    }



    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {
        SQLiteDatabase database = mdbhelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowDeleted;
        switch (match){

            case custom:
                rowDeleted = database.delete(VicroseContract.VicEntry.TABLE_NAME,selection,selectionArgs);

                break;
            case custom_id:
                selection = VicroseContract.VicEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowDeleted = database.delete(VicroseContract.VicEntry.TABLE_NAME,selection,selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("DELETE NOT SUPPORTED" + uri);
        }
        if(rowDeleted != 0 ){
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return  rowDeleted;

    }

    @Override
    public int update( Uri uri, ContentValues values,  String selection,  String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case custom:
                return updateV(uri,values, selection,selectionArgs);
            case custom_id:
                selection = VicroseContract.VicEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateV(uri,values, selection,selectionArgs);
            default:
                throw new IllegalArgumentException("update not supported");
        }
    }
    private int updateV(Uri uri,ContentValues values, String selection,String[] selectionArgs){
        if (values.containsKey(VicroseContract.VicEntry.COLUMN_NAME)){
            String name = values.getAsString(VicroseContract.VicEntry.COLUMN_NAME);
            if(name == null){
                throw new IllegalArgumentException("A name is required");
            }
        }
       SQLiteDatabase database = mdbhelper.getWritableDatabase();
        int rowupdate = database.update(VicroseContract.VicEntry.TABLE_NAME,values,selection,selectionArgs);
        if(rowupdate != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowupdate;
    }
}
