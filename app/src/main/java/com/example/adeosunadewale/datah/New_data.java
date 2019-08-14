package com.example.adeosunadewale.datah;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.adeosunadewale.datah.data.VicroseContract;

public class New_data extends AppCompatActivity {
    EditText  mName,mSI, mBK,mBST,mFB,mGL,mKNEL,mLB,mSLV,mSw,mUNDBST,mWST,phon_no;
    Uri currenturi;
    int decider;
    String reformed =null, struct="";
    public static final String LOG_TAG = New_data.class.getSimpleName();
    boolean haschanged = false;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.bggrad);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(drawable);
        }
        actionBar.setBackgroundDrawable(drawable);

        setContentView(R.layout.activity_new_data);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        mName =  findViewById(R.id.name);
        mSI =  findViewById(R.id.textfieldSI);
        mBK =  findViewById(R.id.textfieldBK);
        mFB = findViewById(R.id.textfieldFB);
        mGL =  findViewById(R.id.textfieldGL);
        mKNEL =  findViewById(R.id.textfieldKNEL);
        mLB =  findViewById(R.id.textfieldLB);
        mSLV =  findViewById(R.id.textfieldSLV);
        mSw =  findViewById(R.id.textfieldSW);
        mUNDBST =  findViewById(R.id.textfieldUNDBST);
        mWST =  findViewById(R.id.textfieldWST);
        mBST = findViewById(R.id.textfieldBST);
        phon_no = findViewById(R.id.phone_no);

        mName.setOnTouchListener(mTouch);
        mSI.setOnTouchListener(mTouch);
        mBK.setOnTouchListener(mTouch);
        mFB.setOnTouchListener(mTouch);
        mGL.setOnTouchListener(mTouch);
        mKNEL.setOnTouchListener(mTouch);
        mLB.setOnTouchListener(mTouch);
        mSLV.setOnTouchListener(mTouch);
        mSw.setOnTouchListener(mTouch);
        mUNDBST.setOnTouchListener(mTouch);
        mWST.setOnTouchListener(mTouch);
        mBST.setOnTouchListener(mTouch);
        phon_no.setOnTouchListener(mTouch);

        Intent intent = getIntent();
        currenturi = intent.getData();
        if(currenturi == null){
            setTitle("New Data");
            decider = 1;
        }
        else{
            setTitle("Edit Data");
            setedit();
            decider = 2;
        }


    }

    private View.OnTouchListener mTouch = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            haschanged = true;
            return false;
        }
    };


    private void insertData(){
        ContentValues val = new ContentValues();

        setContentView(R.layout.activity_new_data);


        String nameString = mName.getText().toString().trim();
        nameString = nameString.substring(0,1).toUpperCase()+nameString.substring(1).toLowerCase();

        val.put(VicroseContract.VicEntry.COLUMN_NAME, nameString);




        try{
           float SI = Float.valueOf(mSI.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_SI, SI);

        }
        catch(NumberFormatException e){
        }
        try{
            float BK = Float.valueOf(mBK.getText().toString());
            Log.e(LOG_TAG, "insertData: "+ BK );
            val.put(VicroseContract.VicEntry.COLUMN_BK ,BK );
        }
        catch(NumberFormatException e){
        }
        try{
            float BST = Float.valueOf(mBST.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_Bst, BST);
        }
        catch(NumberFormatException e){
        }
        try{
            float FB = Float.valueOf(mFB.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_FB, FB);
        }
        catch(NumberFormatException e){
        }
        try{
            float GL = Float.valueOf(mGL.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_GL, GL);
        }
        catch(NumberFormatException e){
        }
        try{
            float KNEL = Float.valueOf(mKNEL.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_Knel, KNEL);
        }
        catch(NumberFormatException e){
        }
        try{
            float LB = Float.valueOf(mLB.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_lb, LB);
        }
        catch(NumberFormatException e){
        }
        try{
            float SLV = Float.valueOf(mSLV.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_Slv, SLV);
        }
        catch(NumberFormatException e){
        }
        try{
            float Sw = Float.valueOf(mSw.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_Sw, Sw);
        }
        catch(NumberFormatException e){
        }
        try{

            float UNDBST = Float.valueOf(mUNDBST.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_UndBst, UNDBST);
        }
        catch(NumberFormatException e){
        }
        try{

            float WST = Float.valueOf(mWST.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_Wst, WST);
        }
        catch(NumberFormatException e){

        }
        try{

            int phone = Integer.valueOf(phon_no.getText().toString());
            val.put(VicroseContract.VicEntry.COLUMN_Phone, phone);
        }
        catch(NumberFormatException e){

        }

        if (decider == 1){
            Uri newuri = getContentResolver().insert(VicroseContract.VicEntry.CONTENT_URI, val);
            if (newuri == null){
                Toast.makeText(this, "Error, Unable to save",Toast.LENGTH_SHORT).show();
            }
            else {

                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            }
            finish();

        }
        else {
            int updated = getContentResolver().update(currenturi,val,null,null);

            if (updated != 1){
                Toast.makeText(this, "Error, Unable to update",Toast.LENGTH_SHORT).show();
            }
            else {

                Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }

            finish();
        }






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_newdata, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_newdata:
                validator();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setedit(){
        String[] projection = {
                  VicroseContract.VicEntry._ID,VicroseContract.VicEntry.COLUMN_NAME,VicroseContract.VicEntry.COLUMN_SI,
                 VicroseContract.VicEntry.COLUMN_GL, VicroseContract.VicEntry.COLUMN_BK,VicroseContract.VicEntry.COLUMN_Wst,
                 VicroseContract.VicEntry.COLUMN_Bst,VicroseContract.VicEntry.COLUMN_UndBst,VicroseContract.VicEntry.COLUMN_lb,
                 VicroseContract.VicEntry.COLUMN_Sw,VicroseContract.VicEntry.COLUMN_Slv,VicroseContract.VicEntry.COLUMN_Knel,
                VicroseContract.VicEntry.COLUMN_FB, VicroseContract.VicEntry.COLUMN_Phone
        };
        Cursor cursor = getContentResolver().query(currenturi,projection,null,null,null);
        cursor.moveToFirst();
        try{
            int custName = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_NAME);
            int c_si = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_SI);
            int c_gl = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_GL);
            int c_bk = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_BK);
            int c_wst = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Wst);
            int c_bst = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Bst);
            int c_undbst = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_UndBst);
            int c_lb = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_lb);
            int c_sw = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Sw);
            int c_slv = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Slv);
            int c_knel = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Knel);
            int c_fb = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_FB);
            int c_phone = cursor.getColumnIndex(VicroseContract.VicEntry.COLUMN_Phone);

            String name = cursor.getString(custName);
            float si = cursor.getFloat(c_si);
            float gl = cursor.getFloat(c_gl);
            float bk = cursor.getFloat(c_bk);
            float wst = cursor.getFloat(c_wst);
            float bst = cursor.getFloat(c_bst);
            float undbst = cursor.getFloat(c_undbst);
            float lb = cursor.getFloat(c_lb);
            float sw = cursor.getFloat(c_sw);
            float slv  = cursor.getFloat(c_slv);
            float knel = cursor.getFloat(c_knel);
            float fb = cursor.getFloat(c_fb);
            int phoneNumber = cursor.getInt(c_phone);

           mName.setText(name);
           mSI.setText(Float.toString(si));
           mGL.setText(Float.toString(gl));
           mBK.setText(Float.toString(bk));
           mWST.setText(Float.toString(wst));
           mBST.setText(Float.toString(bst));
           mUNDBST.setText(Float.toString(undbst));
           mLB.setText(Float.toString(lb));
           mSw.setText(Float.toString(sw));
           mSLV.setText(Float.toString(slv));
           mKNEL.setText(Float.toString(knel));
            mFB.setText(Float.toString(fb));
            phon_no.setText(Integer.toString(phoneNumber));



        }
        finally {
            cursor.close();
        }

        }


    private void showUnsavedDialog(DialogInterface.OnClickListener discardButton){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Discard your changes and quit editing");
        builder.setPositiveButton("discard", discardButton);
        builder.setNegativeButton("keep editing", new DialogInterface.OnClickListener() {
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
    @Override
    public void onBackPressed(){
        if (!haschanged){
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButton = new DialogInterface.OnClickListener(){
           @Override
            public void onClick(DialogInterface dialogInterface, int i ){
               finish();
           }
        };
        showUnsavedDialog(discardButton);
    }
    private void validator(){
        if (awesomeValidation.validate()) {
            insertData();
        }
    }

}
