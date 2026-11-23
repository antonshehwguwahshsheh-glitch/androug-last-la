package com.example.mainvirus;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.graphics.Color;

public class BugScreamer extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        TextView tv = new TextView(this);
        tv.setText("ЖУКИ ВЕЗДЕ! ТЕЛЕФОН ЗАБЛОКИРОВАН!");
        tv.setTextColor(Color.RED);
        tv.setTextSize(40);
        tv.setBackgroundColor(Color.BLACK);
        setContentView(tv);
    }
}
