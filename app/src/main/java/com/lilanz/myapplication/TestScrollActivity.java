package com.lilanz.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class TestScrollActivity extends AppCompatActivity {
    int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final View textView = findViewById(R.id.tv_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.scrollTo(dip2px(a+=10), dip2px(a+=10));

            }
        });

    }

    private int dip2px(int d) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        getResources().getDisplayMetrics();
        return (int) (metrics.density * d + 0.5);
    }
}
