package com.example.john.testdome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Main2Activity extends AppCompatActivity {
    private Image3DSwitchView imageSwitchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageSwitchView = (Image3DSwitchView) findViewById(R.id.image_switch_view);
        imageSwitchView.setOnImageSwitchListener(new Image3DSwitchView.OnImageSwitchListener() {
            @Override
            public void onImageSwitch(int currentImage) {
                // Log.d("TAG", "current image is " + currentImage);
            }
        });
        imageSwitchView.setCurrentImage(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageSwitchView.clear();
    }
}
