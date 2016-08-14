package com.android.mystic.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mystic.R;
import com.android.mystic.ui.listener.IScreenSwipe;
import com.android.mystic.ui.listener.OnSwipeTouchListener;

public class QuotesActivity extends AppCompatActivity implements IScreenSwipe {

    TextView tvQuotesContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_fragment);
        tvQuotesContent = (TextView)findViewById(R.id.tvQuoteContent);
        tvQuotesContent.setOnTouchListener(new OnSwipeTouchListener(this, this));
    }

    @Override
    public void onSwipeRight() {
        Toast.makeText(this,"You are Swiping Right ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeLeft() {
        Toast.makeText(this,"You are Swiping Left",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeBottom() {
        Toast.makeText(this,"You are Swiping Bottom",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeTop() {
        Toast.makeText(this,"You are Swiping Top ",Toast.LENGTH_SHORT).show();
    }
}
