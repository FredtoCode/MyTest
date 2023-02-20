package com.example.mytest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowInsetsAnimation;

import androidx.annotation.Nullable;

public class XferModeView extends View {

    private static String TAG = "XferModeView";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF bounds = new RectF(20, 20, 520, 520);
    private Bitmap TestBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
    private Rect Text_bounds = new Rect();
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public XferModeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        Canvas canvas = new Canvas(TestBitmap);
        paint.setColor(Color.parseColor("#2196F3"));
        canvas.drawRect(0f, 0f, 500f, 500f, paint);
        paint.setColor(Color.BLACK);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = canvas.saveLayer(bounds, null);
        canvas.drawOval(20f, 20f, 520f, 520f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        paint.setColor(Color.parseColor("#D81B60"));
//        canvas.drawRect(20f, 20f, 520f, 270f, paint);
        canvas.drawBitmap(TestBitmap, 20f, 20f, paint);
//        canvas.drawBitmap(getMyBitmap(500), 20f, 20f, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(count);
        paint.setStyle(Paint.Style.FILL);
//        paint.getTextBounds("abab", 0, "abab".length(), Text_bounds);
        paint.getFontMetrics(fontMetrics);
        paint.setTextSize(100f);
        paint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText("abab", 270f, 270f + (Text_bounds.bottom - Text_bounds.top)/2f, paint);
        Log.e(TAG, "changeWidth: " + fontMetrics.ascent + "changeHeight: " + fontMetrics.descent );
        canvas.drawText("abab", 270f, 270f + (fontMetrics.descent - fontMetrics.ascent)/2f, paint);
    }

    private Bitmap getMyBitmap(int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
//        BitmapFactory.decodeResource(getResources(), R.drawable.background, options);
//        Log.e(TAG, "changeWidth: " + options.outWidth + "changeHeight: " + options.outHeight );
        return BitmapFactory.decodeResource(getResources(), R.drawable.background, options);
    }
}
