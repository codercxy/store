package com.dd.android.views.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.dd.android.R;
import com.google.zxing.encoding.EncodingHandler;

public class CodeActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        imageView = (ImageView) findViewById(R.id.code_img);
        try {
//          Bitmap mBitmap = EncodingHandler.createQRCode("www.baidu.com", 300);
//          qrcodeImg.setImageBitmap(mBitmap);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.head);
            Bitmap www = EncodingHandler.createQRCode("www", 600, 600, bitmap);

            imageView.setImageBitmap(www);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
