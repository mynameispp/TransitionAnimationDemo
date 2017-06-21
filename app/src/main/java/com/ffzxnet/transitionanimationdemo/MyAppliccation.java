package com.ffzxnet.transitionanimationdemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 创建者： feifan.pi 在 2017/1/13.
 */

public class MyAppliccation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
