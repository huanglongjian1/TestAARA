package com.android.testaar.test1;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.android.testaar.Loge;
import com.android.testaar.R;

import java.util.UUID;

/**
 * 桌面小组件
 *
 * @author Admin
 */
public class TestAppWidget extends AppWidgetProvider {

    /**
     * 每次窗口小部件被更新都调用一次该方法（创建、时间到更新周期都会调起这里）
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        //更新数据
        updateWidgetView(context, UUID.randomUUID().toString());
    }

    /**
     * 接收窗口小部件点击时发送的广播
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    /**
     * 每删除一次窗口小部件就调用一次
     */
    @Override

    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    /**
     * 当最后一个该窗口小部件删除时调用该方法
     */
    @Override

    public void onDisabled(Context context) {
        super.onDisabled(context);

    }

    /**
     * 当该窗口小部件第一次添加到桌面时调用该方法
     */
    @Override

    public void onEnabled(Context context) {
        super.onEnabled(context);

    }

    /**
     * 当小部件大小改变时
     */
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    /**
     * 当小部件从备份恢复时调用该方法
     */
    @Override

    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Loge.e("当小部件从备份恢复时调用该方法");
    }

    /**
     * 更新桌面小组件数据用，APP中也可以在任意地方传入任意数据进来主动更新小组件数据
     */
    public static void updateWidgetView(Context context, String str) {
        //初始化RemoteViews
        ComponentName componentName = new ComponentName(context, TestAppWidget.class);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);

        //点击事件，点击跳转到MainActivity页面
        Intent startActivityIntent = new Intent(context, MyActivity.class);
        PendingIntent processInfoIntent;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            //31，Android11以上系统
            processInfoIntent = PendingIntent.getActivity(context, 0, startActivityIntent, PendingIntent.FLAG_IMMUTABLE);
        } else {
            processInfoIntent = PendingIntent.getActivity(context, 0, startActivityIntent, PendingIntent.FLAG_ONE_SHOT);
        }
        remoteViews.setOnClickPendingIntent(R.id.appwidget_text, processInfoIntent);

        //更新文本数据
        remoteViews.setTextViewText(R.id.appwidget_text, str);

        //开始更新视图
        AppWidgetManager awm = AppWidgetManager.getInstance(context);
        awm.updateAppWidget(componentName, remoteViews);
    }

}