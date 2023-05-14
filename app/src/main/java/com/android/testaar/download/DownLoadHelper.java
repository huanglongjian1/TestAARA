package com.android.testaar.download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.android.testaar.AppManager;
import com.android.testaar.Loge;

import java.io.File;

public class DownLoadHelper {
    public static int downLoadApk(Context context) {
        //创建request对象
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://up.deskcity.org/pic_source/2f/f4/42/2ff442798331f6cc6005098766304e39.jpg"));
        //设置什么网络情况下可以下载
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        //设置通知栏的标题
        request.setTitle("下载");
        //设置通知栏的message
        request.setDescription("今日头条正在下载.....");
        //设置漫游状态下是否可以下载
        //设置是否允许漫游网络 建立请求 默认true
        request.setAllowedOverRoaming(true);



        /*在默认的情况下，通过Download Manager下载的文件是不能被Media Scanner扫描到的 。
        进而这些下载的文件（音乐、视频等）就不会在Gallery 和  Music Player这样的应用中看到。
        为了让下载的音乐文件可以被其他应用扫描到，我们需要调用Request对象的
         */
        request.allowScanningByMediaScanner();

        /*如果我们希望下载的文件可以被系统的Downloads应用扫描到并管理，
        我们需要调用Request对象的setVisibleInDownloadsUi方法，传递参数true。*/
        request.setVisibleInDownloadsUi(true);

       // Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).mkdir();

        //设置文件存放路径
      //  request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "2ff442798331f6cc6005098766304e39.jpg");
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        //进行下载
        int id = (int) downloadManager.enqueue(request);
        Loge.e("已经下载啦" + id);
        return id;
    }

    /**
     * Created by YuShuangPing on 2018/9/4.
     */
    public static class DownLoadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
                //在广播中取出下载任务的id
                Loge.e("收到广播");
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                Toast.makeText(AppManager.getInstance().getTopActivity(), "编号：" + id + "的下载任务已经完成！", Toast.LENGTH_SHORT).show();
                DownloadManager.Query query = new DownloadManager.Query();
                DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                query.setFilterById(id);
                Cursor c = dm.query(query);
                if (c != null) {
                    try {
                        if (c.moveToFirst()) {
                            //获取文件下载路径
                            String filename = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                            int status = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                                //启动更新
                                Uri uri = Uri.fromFile(new File(filename));
                                if (uri != null) {
                                    Intent install = new Intent(Intent.ACTION_VIEW);
                                    install.setDataAndType(uri, "application/vnd.android.package-archive");
                                    install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(install);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    } finally {
                        c.close();
                    }

                }
            }
        }
    }

}
