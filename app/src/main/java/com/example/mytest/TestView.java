package com.example.mytest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.mytest.Utils;

import androidx.annotation.Nullable;

public class TestView extends View {

    private static String TAG = "TestView";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path = new Path();
    private Path dash = new Path();
    private float RADIUS = Utils.dp2px(100f);
    private PathMeasure pathMeasure;
    private float OPEN_ANGLE = 120f;
    private float PHASE_DISTANCE;
    private float LENGTH = Utils.dp2px(80f);

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Log.e(TAG, "init......");
        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.STROKE);
        dash.addRect(0f, 0f, 2f, 5f, Path.Direction.CW);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged......");
        path.reset();
        path.addArc(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS, getWidth() / 2f + RADIUS, getHeight() / 2f + RADIUS,
                90f + OPEN_ANGLE / 2, 360f - OPEN_ANGLE);
//        path.addCircle(getWidth()/2f, getHeight()/2f, RADIUS, Path.Direction.CW);
//        path.addRect(getWidth()/2f - RADIUS, getHeight()/2f, getWidth()/2f + RADIUS, getHeight()/2f + 2*RADIUS, Path.Direction.CW);
        pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, false);
        PHASE_DISTANCE = (pathMeasure.getLength() - 2f) / 20;
//        path.setFillType(Path.FillType.EVEN_ODD);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw......");
        canvas.drawPath(path, paint);
//        canvas.drawLine(10f, 10f, 70f, 70f, paint);
//        canvas.drawCircle(getWidth()/2f, getHeight()/2f, Utils.dp2px(100f), paint);
//        canvas.drawArc(getWidth()/2f - RADIUS, getHeight()/2f - RADIUS, getWidth()/2f + RADIUS, getHeight()/2f + RADIUS,
//                90f + OPEN_ANGLE/2, 360f - OPEN_ANGLE, false, paint);
        PathDashPathEffect pathDashPathEffect = new PathDashPathEffect(dash, PHASE_DISTANCE, 0f, PathDashPathEffect.Style.ROTATE);
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawArc(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS, getWidth() / 2f + RADIUS, getHeight() / 2f + RADIUS,
                90f + OPEN_ANGLE / 2, 360f - OPEN_ANGLE, false, paint);
        paint.setPathEffect(null);
        canvas.drawLine(getWidth() / 2f, getHeight() / 2f,
                (float) (getWidth() / 2f + LENGTH * Math.cos(Math.toRadians(90f + OPEN_ANGLE / 2f + (360f - OPEN_ANGLE) / 20f * 5))),
                (float) (getHeight() / 2f + LENGTH * Math.sin(Math.toRadians(90f + OPEN_ANGLE / 2f + (360f - OPEN_ANGLE) / 20f * 5))), paint);
    }
}
