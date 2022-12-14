package edu.sjsu.android.project2jiahongzhen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class MyView extends View {
    /*
    1 int: a constant number that defines the size of the ball
    2 Bitmap objects: one for the field, one for the ball
    4 floats: originX, originY, horizontalBound, verticalBound
    1 Particle object that defines the position of the ball
     */
    private final int BALL_SIZE = 175;
    private Bitmap field, ball;
    private float originX, originY, horizontalBound, verticalBound;
    private Particle particle;
    public MyView(Context context)
    {
        super(context);
        field = BitmapFactory.decodeResource(context.getResources(),R.drawable.field);
        ball = BitmapFactory.decodeResource(context.getResources(),R.drawable.ball);
        ball = Bitmap.createScaledBitmap(ball, BALL_SIZE,BALL_SIZE,false);
        particle = new Particle();
    }
    @Override
    protected void onSizeChanged(int w, int h , int oldw, int oldh)
    {
        super.onSizeChanged(w,h,oldw,oldh);
        originX = w / 2f;
        originY = h / 2f;

        // TODO: set horizontalBounds, verticalBound
        horizontalBound = originX-ball.getWidth()/2;
        verticalBound = originY-ball.getHeight()/2;
        field = Bitmap.createScaledBitmap(field, w, h, false);
    }
    @Override
    protected  void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(field,0,0, null);
        // TODO: draw the ball so that it will be on the center at the beginning
        // And moves based on particle object's data
        // base on particle.mPosX and particle.mPosY, BALL_SIZE, originX, originY
        //canvas.drawBitmap(ball, particle.mPosY ,particle.mPosX, null);
        canvas.drawBitmap(ball, originX+particle.mPosX-ball.getWidth()/2 ,originY-particle.mPosY-ball.getHeight()/2, null);

        // TODO: draw your name
        Paint myName = new Paint();
        myName.setColor(Color.BLACK);
        myName.setAlpha(120);
        myName.setTextSize(80);
        canvas.drawText("Jiahong Zhen",originX-250,originY-50, myName);


        particle.updatePosition(MainActivity.x, MainActivity.y, MainActivity.timestamp);
        particle.resolveCollisionWithBounds(horizontalBound,verticalBound);
        invalidate();
    }


}
