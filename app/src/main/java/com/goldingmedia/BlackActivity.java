package com.goldingmedia;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.goldingmedia.jni.LcdPowerSwitch;

public class BlackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(LcdPowerSwitch.lcdGet() == 1){
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
