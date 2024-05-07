package com.example.report;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PhoneStat extends AppCompatActivity {

    private WifiManager wifiManager;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 액티비티를 종료하고 이전 액티비티로 돌아감
            }
        });

        TextView androidVersionTextView = findViewById(R.id.androidVersionTextView);
        int androidVersion = android.os.Build.VERSION.SDK_INT;
        androidVersionTextView.setText("안드로이드 버전: " + androidVersion);

        TextView wifiTextView = findViewById(R.id.wifiTextView);

        // WifiManager 초기화
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // Wi-Fi 상태 가져오기
        int wifiState = wifiManager.getWifiState();

        // Wi-Fi 상태에 따라 TextView에 표시
        if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
            wifiTextView.setText("Wi-Fi 상태: 켜짐");
        } else if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
            wifiTextView.setText("Wi-Fi 상태: 꺼짐");
        } else {
            wifiTextView.setText("Wi-Fi 상태: 알 수 없음");
        }

        // ConnectivityManager 초기화
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // 데이터 서비스 상태 확인 및 표시
        TextView dataTextView = findViewById(R.id.lteTextView);
        if (isMobileDataEnabled()) {
            dataTextView.setText("데이터 서비스 상태: 켜짐");
        } else {
            dataTextView.setText("데이터 서비스 상태: 꺼짐");
        }

        // 내부 저장소의 전체 용량과 남은 용량 확인 및 표시
        TextView storageTextView = findViewById(R.id.sdcardTextView);
        long totalInternalStorageSize = getTotalInternalMemorySize();
        totalInternalStorageSize = totalInternalStorageSize / (1024*1024*1024);
        long availableInternalStorageSize = getAvailableInternalMemorySize();
        availableInternalStorageSize = availableInternalStorageSize / (1024*1024*1024);
        storageTextView.setText("내부 저장소:  전체 용량: " + totalInternalStorageSize + "GB" +
                "/ 남은 용량: " + availableInternalStorageSize + "GB");

        // 설치된 앱 수 표시
        TextView installedAppsTextView = findViewById(R.id.apptextView);
        int installedAppsCount = getInstalledAppsCount();
        int runningAppsCount = getRunningAppsCount();
        installedAppsTextView.setText("설치된 앱 수: " + installedAppsCount +" / 실행 중인 앱 수: " + runningAppsCount);

    }

    // 데이터 서비스(모바일 데이터) 상태 확인 메서드
    private boolean isMobileDataEnabled() {
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return networkInfo != null && networkInfo.isConnected();
    }

    // 내부 저장소의 전체 용량 가져오는 메서드
    private long getTotalInternalMemorySize() {
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        return totalBlocks * blockSize;
    }

    // 내부 저장소의 사용 가능한 용량 가져오는 메서드
    private long getAvailableInternalMemorySize() {
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return availableBlocks * blockSize;
    }

    // 설치된 앱 수 가져오는 메서드
    private int getInstalledAppsCount() {
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        return installedApps.size();
    }

    // 실행 중인 앱 수 가져오는 메서드
    private int getRunningAppsCount() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = activityManager.getRunningAppProcesses();
        return runningApps.size();
    }
}
