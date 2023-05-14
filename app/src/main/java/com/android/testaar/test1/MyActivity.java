package com.android.testaar.test1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.testaar.R;
import com.android.testaar.databinding.Test1LayoutBinding;

public class MyActivity extends AppCompatActivity {
    Test1LayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Test1LayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.test1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1，获取通知管理器
                SelfNofition(null);
            }
        });


    }

    //自定义布局的通知
    public void SelfNofition(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel notifici = new NotificationChannel("self", "自定义", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(notifici);
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.layout_widget);//自定义的布局视图
        views.setTextViewText(R.id.appwidget_text, "警告：当前阀值：200/120");

        Notification.Builder builder = new Notification.Builder(this, "self");
        builder.setSmallIcon(R.drawable.push_small)//使用RemoteViews时，设置的是状态栏中的小图标，必须要设置
                .setContent(views);

        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
}
