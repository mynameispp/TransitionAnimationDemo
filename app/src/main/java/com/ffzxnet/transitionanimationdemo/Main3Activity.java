package com.ffzxnet.transitionanimationdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;


import com.ffzxnet.transitionanimationdemo.view.loading.AnimationUtils;
import com.ffzxnet.transitionanimationdemo.view.loading.LeafLoadingView;

import java.util.Random;

public class Main3Activity extends AppCompatActivity {

    LeafLoadingView mLeafLoadingView;
    private View mFanView;
    int mProgress;

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (mProgress < 40) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(0,
                                new Random().nextInt(1000));
                        mLeafLoadingView.setProgress(mProgress);
                    } else if (mProgress < 100) {
                        mProgress += 1;
                        // 随机1200ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(0,
                                new Random().nextInt(1200));
                        mLeafLoadingView.setProgress(mProgress);
                    }else{
                        Animation animation=AnimationUtils.initAlphaAnimtion(getApplicationContext(),1,0,1000);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                mFanView.setVisibility(View.GONE);
                                findViewById(R.id.pros).setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        mFanView.startAnimation(animation);
                    }
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mFanView = findViewById(R.id.fan_pic);
        RotateAnimation rotateAnimation = AnimationUtils.initRotateAnimation(false, 1500, true,
                Animation.INFINITE);
        mFanView.startAnimation(rotateAnimation);
        mLeafLoadingView = (LeafLoadingView) findViewById(R.id.leafLoadingView);
        mHandler.sendEmptyMessageDelayed(0, 2000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
        mProgress = 0;
    }
}
