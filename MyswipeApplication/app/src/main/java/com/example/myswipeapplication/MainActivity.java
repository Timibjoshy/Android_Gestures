package com.example.myswipeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mv = findViewById(R.id.myview);
        final TextView grtv = findViewById(R.id.gestureResultTextView);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.fabrands400);
        grtv.setTypeface(typeface);
        // Get screen size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        // Initialize SimpleFingerGestures
        SimpleFingerGestures sfg = new SimpleFingerGestures();
        sfg.setDebug(true);
        sfg.setConsumeTouchEvents(true);

        // Set up gesture listener
        sfg.setOnFingerGestureListener(new SimpleFingerGestures.OnFingerGestureListener() {
            @Override
            public boolean onSwipeUp(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You swiped up ");
                showToast("You swiped up");
                return false;
            }

            @Override
            public boolean onSwipeDown(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You swiped down ");
                showToast("You swiped down");
                return false;
            }

            @Override
            public boolean onSwipeLeft(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You swiped left ");
                showToast("You swiped left");
                return false;
            }

            @Override
            public boolean onSwipeRight(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You swiped right ");
                showToast("You swiped right");
                return false;
            }

            @Override
            public boolean onPinch(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You pinched  fingers ");
                showToast("You pinched fingers");
                return false;
            }

            @Override
            public boolean onUnpinch(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You unpinched fingers" );
                showToast("You unpinched fingers");
                return false;
            }

            @Override
            public boolean onDoubleTap(int fingers) {
                grtv.setText("You double tapped");
                showToast("You double tapped");
                return false;
            }


        });


        GestureDetector.SimpleOnGestureListener singleTapListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                grtv.setText("You single clicked");
                showToast("You single clicked");
                return true;
            }
        };

        final GestureDetector singleTapDetector = new GestureDetector(this, singleTapListener);

        // Set onTouchListener for ImageView
        mv.setOnTouchListener((v, event) -> {
            sfg.onTouch(v, event);
            singleTapDetector.onTouchEvent(event);
            return true;
        });


    }
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}