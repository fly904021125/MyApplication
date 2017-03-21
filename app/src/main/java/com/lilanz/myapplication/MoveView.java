package com.lilanz.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TA on 2017/3/10.
 */

public class MoveView extends View {

    private boolean isDrawCircle;
    private Paint paint;
    private float radius;
    private static float DEFAULT_RADIUS = 100;
    private List<PointF> list;

    public MoveView(Context context) {
        super(context);
        init(context);
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isDrawCircle && list != null) {
            for (int i = 0; i < list.size(); i++) {
                PointF pointF = list.get(i);
                canvas.drawCircle(pointF.x, pointF.y, radius, paint);
            }
        }
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        radius = displayMetrics.density * DEFAULT_RADIUS + 0.5F;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int actionIndex = event.getActionIndex();
        float x = event.getX(actionIndex);
        float y = event.getY(actionIndex);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isDrawCircle = true;
                list = new ArrayList<>();
                PointF point = new PointF(x, y);
                list.add(point);
                break;

            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    x=event.getX(i);
                    y=event.getY(i);
                    list.get(i).set(x, y);
                }

                break;
            case MotionEvent.ACTION_UP:
                list.clear();
                isDrawCircle = false;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                point = new PointF(x, y);
                list.add(point);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                list.remove(actionIndex);
                break;
        }
        invalidate();


        return true;
    }
}
