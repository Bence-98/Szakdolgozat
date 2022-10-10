package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Button implements GameObject{

    private Rect button;
    private Paint paint;



    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(button, paint);
    }
}
