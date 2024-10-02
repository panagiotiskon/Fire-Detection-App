package com.example.firedetectionapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawEscapeRoute extends View {

    private Paint paint;
    private Path path;
    private float arrowX, arrowY;  // Arrow position
    private boolean drawArrow;

    public DrawEscapeRoute(Context context) {
        super(context);
        init();
    }

    public DrawEscapeRoute(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(12);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
        drawArrow = false;
    }

    public void setPath(float startX, float startY, float endX, float endY) {
        path.moveTo(startX, startY);
        path.lineTo(endX, endY);
        invalidate();
    }

    public void drawLeftArrow(Canvas canvas, float x, float y) {

        float arrowSize = 50;

        canvas.drawLine(x, y, x - arrowSize, y, paint);

        canvas.drawLine(x - arrowSize, y, x - arrowSize + 20, y - 20, paint);

        canvas.drawLine(x - arrowSize, y, x - arrowSize + 20, y + 20, paint);

    }

    public void drawArrow(float x, float y) {
        arrowX = x;
        arrowY = y;
        drawArrow = true;
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (path != null) {
            canvas.drawPath(path, paint);
        }
        if (drawArrow) {
            drawLeftArrow(canvas, arrowX, arrowY);
        }
    }
}
