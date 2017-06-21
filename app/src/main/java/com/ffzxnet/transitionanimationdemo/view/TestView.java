package com.ffzxnet.transitionanimationdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.ffzxnet.transitionanimationdemo.R;


/**
 * 创建者： feifan.pi 在 2017/2/12.
 */

public class TestView extends View {
    Paint paint;
    Rect mBounds;
    String txt;
    int colorInt;
    int txt_size;
    boolean txt_bold;
    boolean txt_italc;
    boolean txt_strikeThru;

    private int[] styles = {STYLE_BOLD, STYLE_ITALC, STYLE_STRIKETHRU};
    private static final int STYLE_BOLD = 1;
    private static final int STYLE_ITALC = 2;
    private static final int STYLE_STRIKETHRU = 4;


    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Test);
            int size = typedArray.getIndexCount();
            for (int i = 0; i < size; i++) {
                int index = typedArray.getIndex(i);
                switch (index) {
                    case R.styleable.Test_txt:
                        txt = typedArray.getString(index);
                        break;
                    case R.styleable.Test_txt_color:
                        colorInt = typedArray.getColor(index, Color.BLACK);
                        break;
                    case R.styleable.Test_txt_size:
                        txt_size = typedArray.getInt(index, 40);
                        Log.e("ssssssss1", txt_size + "");
                        txt_size = (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_SP, txt_size, getResources().getDisplayMetrics());
                        break;
                    case R.styleable.Test_txt_style:
                        int txt_style = typedArray.getInt(index, -1);
                        //获取多个属性，判断使用了那些属性 flag
                        switch (txt_style) {
                            case STYLE_BOLD:
                                txt_bold = true;
                                break;
                            case STYLE_ITALC:
                                txt_italc = true;
                                break;
                            case STYLE_STRIKETHRU:
                                txt_strikeThru = true;
                                break;
                            case STYLE_BOLD + STYLE_ITALC:
                                txt_bold = true;
                                txt_italc = true;
                                break;
                            case STYLE_BOLD + STYLE_STRIKETHRU:
                                txt_bold = true;
                                txt_strikeThru = true;
                                break;
                            case STYLE_ITALC + STYLE_STRIKETHRU:
                                txt_italc = true;
                                txt_strikeThru = true;
                                break;
                            case STYLE_BOLD + STYLE_ITALC + STYLE_STRIKETHRU:
                                txt_bold = true;
                                txt_italc = true;
                                txt_strikeThru = true;
                                break;
                        }
                        break;
                }
            }
            typedArray.recycle();
        }

        paint = new Paint();
        mBounds = new Rect();
        //设置文字属性
        paint.setFakeBoldText(txt_bold);
        paint.setStrikeThruText(txt_strikeThru);
        if (txt_italc) {
            paint.setTextSkewX(-0.5f);
        }
        paint.setTextSize(txt_size);
        paint.getTextBounds(txt, 0, txt.length(), mBounds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width, height;
        int specMode, specSize;
        //重写宽度
        specMode = MeasureSpec.getMode(widthMeasureSpec);
        specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            //写死的固定值
            width = getPaddingLeft() + getPaddingRight() + specSize;
        } else {
            //自适应wrap
            width = getPaddingLeft() + getPaddingRight() + mBounds.width();
        }
        //重写高度
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            //写死的固定值
            height = getPaddingLeft() + getPaddingRight() + specSize;
        } else {
            //自适应wrap
            height = getPaddingLeft() + getPaddingRight() + mBounds.height();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画背景
        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
        //画文字
        paint.setColor(colorInt);
        //文字基准线
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(txt, getWidth() / 2 - mBounds.width() / 2, baseline, paint);
    }
}
