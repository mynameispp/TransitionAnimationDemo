package com.ffzxnet.transitionanimationdemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> imgUrl=new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        imgUrl.add("http://www.feizl.com/upload2007/2012_01/120115153325811.jpg");
        imgUrl.add("http://img.jsqq.net/uploads/allimg/150228/1_150228191103_3.jpg");
        imgUrl.add("http://diy.qqjay.com/u2/2014/0416/4c384c9baea329b86b7478f646f873f9.jpg");
        imgUrl.add("http://imgsrc.baidu.com/forum/w=580/sign=51b80af1f003738dde4a0c2a831ab073/61d3c643ad4bd1139b49c2635cafa40f4afb05a5.jpg");

        RelativeLayout relativeLayout= (RelativeLayout) findViewById(R.id.base);
        int size=relativeLayout.getChildCount();
        for (int i=0;i<size;i++){
            SimpleDraweeView draweeView= (SimpleDraweeView) relativeLayout.getChildAt(i);
            draweeView.setTag(imgUrl.get(i));
            draweeView.setImageURI(Uri.parse(imgUrl.get(i)));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void changeScene(View v) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("imgUrl", v.getTag().toString());

        Pair<View, String> imagePair = Pair.create(v, "shareName");
        Pair<View, String> textPair = Pair.create(v, "shareName1");
        ActivityOptionsCompat compat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, imagePair, textPair);
        startActivity(intent,compat.toBundle());

//        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this,
//                v, "shareName").toBundle());
    }
}
