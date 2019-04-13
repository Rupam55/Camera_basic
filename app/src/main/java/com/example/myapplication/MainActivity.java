package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView resultimg;
    Button captureBtn;

    private static final int RC_PIC_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultimg=findViewById(R.id.resultimg);
        captureBtn=findViewById(R.id.capture);

        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                capture();
            }
        });
    }

    private void capture(){

        Intent takepicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(takepicture,RC_PIC_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_PIC_CODE)
        {
            if (resultCode==RESULT_OK){

                Bitmap bp = (Bitmap) data.getExtras().get("data");

                resultimg.setScaleType(ImageView.ScaleType.FIT_CENTER);

                resultimg.setImageBitmap((bp));

            }
            else if (resultCode==RESULT_CANCELED){

                Toast.makeText(this,"cancelled",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
