package com.android.mystic.ui;

import android.annotation.SuppressLint;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.android.mystic.R;
import com.android.mystic.log.MysticLog;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private String textContent = null;
    TextView tvContent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        tvContent = (TextView)findViewById(R.id.tvFullContent);
        tvContent.setMovementMethod(new ScrollingMovementMethod());
        textContent = this.getResources().getText(R.string.dummy_content).toString();
        MysticLog.e(textContent);
        tvContent.setText(textContent);
    }
}
