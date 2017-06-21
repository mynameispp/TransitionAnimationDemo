package com.ffzxnet.transitionanimationdemo;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    TextView textview2;
    float finalRadius;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置一个exit transition
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setSharedElementExitTransition(new Explode());
//        getWindow().setSharedElementEnterTransition(new Explode());
        setContentView(R.layout.activity_main2);
        SimpleDraweeView imageButton = (SimpleDraweeView) findViewById(R.id.s1);
        SimpleDraweeView imageButton2 = (SimpleDraweeView) findViewById(R.id.s2);
        String url = getIntent().getStringExtra("imgUrl");
        imageButton.setImageURI(Uri.parse(url));
        imageButton2.setImageURI(Uri.parse(url));

        finalRadius = (float) Math.hypot(720, 1280);

        textview2 = (TextView) findViewById(R.id.test);

        imageButton.setOnTouchListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void changeScene(View v) {
        supportFinishAfterTransition();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(textview2.getContext(), Main3Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            //5.0及以上才有这个效果
            if (v.getId() == R.id.s1) {
                //展开波纹
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //参数（需要加载的动画控件，从那开始的坐标x，从那开始的坐标y,多大开始，到多大结束
                    Animator anim_open = ViewAnimationUtils.createCircularReveal(textview2, (int) event.getRawX(), (int) event.getRawY(), 0, finalRadius);
                    anim_open.setDuration(800);
                    anim_open.setInterpolator(new AccelerateDecelerateInterpolator());
                    anim_open.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                            //波纹打开控件一定要先显示，否则无效
                            textview2.setVisibility(View.VISIBLE);
                            textview2.setOnClickListener(null);//获取点击焦点，以免被低下的控件抢走点击焦点
                            textview2.setOnTouchListener(Main2Activity.this);
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {
                        }
                    });
                    anim_open.start();
                }
            } else if ((v.getId() == R.id.test)) {
                //波纹收拢
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    float finalRadius = (float) Math.hypot(720, 1280);
                    Animator anim_close = ViewAnimationUtils.createCircularReveal(textview2, (int) event.getRawX(), (int) event.getRawY(), finalRadius, 0);
                    anim_close.setDuration(800);
                    anim_close.setInterpolator(new AccelerateDecelerateInterpolator());
                    anim_close.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            //波纹收拢控件不能先隐藏
                            textview2.setVisibility(View.GONE);
                            textview2.setOnTouchListener(null);
                            Intent intent = new Intent(textview2.getContext(), Main3Activity.class);
                            startActivity(intent);
                            intent = null;
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {
                        }
                    });
                    anim_close.start();
                }
            }
        } else {
            textview2.setVisibility(View.VISIBLE);
            textview2.setOnClickListener(Main2Activity.this);
        }
        return false;
    }
}
