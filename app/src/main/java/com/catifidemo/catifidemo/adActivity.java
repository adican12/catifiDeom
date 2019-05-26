package com.catifidemo.catifidemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class adActivity extends AppCompatActivity {
    String url,url2="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ImageView imageView= (ImageView) findViewById(R.id.image);
        url2="https://firebasestorage.googleapis.com/v0/b/firecatwifi.appspot.com/o/images%2FtamaAD.jpg?alt=media&token=9865b31b-ed35-432c-812f-e5172e91ceaf";
//         url="https://firebasestorage.googleapis.com/v0/b/firecatwifi.appspot.com/o/Capture.PNG?alt=media&token=3203c13a-88e5-4f82-97c3-16b1fc66ed15";
//        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
        Picasso.get().load(url2).into(imageView);

    }

    protected void back(View view){
        startActivity(new Intent(this, profileActivity.class));
    }

    protected void adClick(View view){
        Intent openURL = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.tama.co.il/"));
        startActivity(openURL);
    }

}
