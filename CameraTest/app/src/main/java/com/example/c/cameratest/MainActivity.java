package com.example.c.cameratest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    private final int CAMERA_TEST = 0;
    String path = Environment.getExternalStorageDirectory().toString()+"/temp/imageTest.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button captureButton = (Button) findViewById(R.id.capture);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(intent, CAMERA_TEST);
            }
        });

        Button processButton = (Button) findViewById(R.id.process);
        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_TEST) {
            //Log.e("requestCode = ", ""+requestCode);
            if (resultCode == RESULT_OK) {
                //Log.e("resultCode = ", ""+resultCode);
                File file = new File(path);
                Bitmap captureBmp = null;

                try {
                    captureBmp = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Log.e("captureBmp = ", ""+captureBmp);
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(captureBmp);
            }
        }
    }
}
