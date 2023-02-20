package com.example.mytest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MultiTextView extends View {

    private TextPaint paint = new TextPaint();
    private String text = "Builder for static layouts. The builder is the preferred pattern for " + "constructing StaticLayout objects and should be preferred over the constructors, particularly to access newer " + "features. To build a static layout, first call obtain(CharSequence, int, int, " + "TextPaint, int) with the required arguments (text, paint, and width), then call " + "setters for optional parameters, and finally build() to build the StaticLayout object. " + "Parameters not explicitly set will get default values.";
    StaticLayout staticLayout;

    public MultiTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        StaticLayout staticLayout = StaticLayout.Builder.obtain(text, 0, text.length(), paint, getWidth()).build();
//        staticLayout.draw(canvas);
//        paint.breakText();
    }
}
