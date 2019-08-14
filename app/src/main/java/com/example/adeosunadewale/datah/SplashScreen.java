package com.example.adeosunadewale.datah;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SplashScreen extends AppCompatActivity {
    ImageView splashImg, logosc;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        splashImg = findViewById(R.id.splashscreenImg);
        logosc = findViewById(R.id.logoId);
        button = findViewById(R.id.continuebtn);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        Picasso.get().load(R.drawable.happybg).resize(width,height).centerCrop().into(splashImg);
        Picasso.get().load(R.drawable.logo_transparent).resize(width*1/3, height*1/3).centerCrop().
                into(logosc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreen.this,Display_list.class);
                startActivity(intent);
            }
        });
        Logolauncher logolauncher = new Logolauncher();
       // logolauncher.start();
    }

private class Logolauncher extends Thread{
        public void run(){
            try{
                sleep(2000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
//            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
//            startActivity(intent);
//            SplashScreen.this.finish();
        }

}
}
