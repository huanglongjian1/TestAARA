package com.android.testaar;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * 继承 GTIntentService 接收来自个推的消息，所有消息在主线程中回调，如果注册了该服务，则务必要在 AndroidManifest 中声明，否则无法接受消息
 */
public class DemoIntentService extends GTIntentService {

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    /**
     * 此方法用于接收和处理透传消息。透传消息个推只传递数据，不做任何处理，客户端接收到透传消息后需要自己去做后续动作处理，如通知栏展示、弹框等。
     * 如果开发者在客户端将透传消息创建了通知栏展示，建议将展示和点击回执上报给个推。
     */
    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        byte[] payload = msg.getPayload();
        String data = new String(payload);
        Log.d(TAG, "receiver payload = " + data);//透传消息文本内容

        //taskid和messageid字段，是用于回执上报的必要参数。详情见下方文档“6.2 上报透传消息的展示和点击数据”
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();

    }

    // 接收 cid
    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
        EventBus.getDefault().post("clientid");
    }

    // cid 离线上线通知
    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    // 各种事件处理回执
    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
    }

    // 通知到达，只有个推通道下发的通知会回调此方法
    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage msg) {
        Loge.e(msg.getContent() + msg.getMessageId() + msg.getPayload() + msg.getTitle());


        CustomDialog customDialog = new CustomDialog(AppManager.getInstance().getTopActivity());
        customDialog.setsTitle("msg.getTitle()").setsMessage("msg.getContent()").setsCancel("cancel", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        }).setsConfirm("sure", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loge.e("听到消息啦");

            }
        }).create();
        customDialog.setCanceledOnTouchOutside(true);
      //  customDialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG));
        customDialog.show();

//        AlertDialog dialog = new AlertDialog.Builder(AppManager.getInstance().getTopActivity())
//                .setIcon(R.drawable.push)//设置标题的图片
//                .setTitle("msg.getTitle()")//设置对话框的标题
//                .setMessage("msg.getContent()")//设置对话框的内容
//                //设置对话框的按钮
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(AppManager.getInstance().getTopActivity(), "点击了取消按钮", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                })
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(AppManager.getInstance().getTopActivity(), "点击了确定的按钮", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                }).create();
//        dialog.show();


    }

    // 通知点击，只有个推通道下发的通知会回调此方法
    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage msg) {
    }
}
