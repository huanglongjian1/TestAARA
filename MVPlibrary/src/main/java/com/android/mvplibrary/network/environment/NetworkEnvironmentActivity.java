package com.android.mvplibrary.network.environment;

import android.app.Application;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.android.mvplibrary.R;


/**
 * 设置网络环境Activity
 *
 * @author llw
 */
public class NetworkEnvironmentActivity extends AppCompatActivity {

    //网络环境
    public static final String NETWORK_ENVIRONMENT = "network_environment";
    //当前网络环境
    private static String mCurrentNetworkEnvironment = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_environment);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new MyPreferenceFragment())
                .commit();
        //获取默认缓存
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //如果没有值就默认为 “1”  在这里 1 表示正式环境
        mCurrentNetworkEnvironment = preferences.getString(NETWORK_ENVIRONMENT, "1");
    }

    /**
     * 内部缓存变化监听类
     */
    public static class MyPreferenceFragment extends PreferenceFragmentCompat
            implements androidx.preference.Preference.OnPreferenceChangeListener {

        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            //这个相当于Activity的setContentView，从资源文件中添Preferences ，选择的值将会自动保存到SharePreferences
            addPreferencesFromResource(R.xml.environment_preference);
            //设置缓存变化监听 ， 通过键来设置监听
            findPreference(NETWORK_ENVIRONMENT).setOnPreferenceChangeListener(this);
        }


        @Override
        public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
            if (!mCurrentNetworkEnvironment.equalsIgnoreCase(String.valueOf(newValue))) {
                //当前值与缓存中不一致时，说明切换了网络，这时提醒一下

                boolean result = (boolean) newValue;
                if (result) {
                    // 返回true  本次值变化生效
                    return true;
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("选择")
                            .setMessage("是否确定关闭？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //用户选择取消，不关闭。直接关闭Dialog即可，因为后面已经返回了false，本次修改不造成变化

                                    Toast.makeText(getContext(), "设置没有生效", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //用户选择确定，那么本次值虽然后面已经返回false，不再接受，但是这里可以继续根据用户的选择更改。
                                    Toast.makeText(getContext(), R.string.network_change_tip, Toast.LENGTH_SHORT).show();

                                }
                            })
                            .create()
                            .show();
                    // 返回false 本次变化失效。在Dialog中通过选择控制值。

                }


            }
            return true;
        }
    }

    /**
     * 页面返回
     */
    @Override
    public void onBackPressed() {
        //获取缓存对象
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //通过键获取缓存则，没有则使用默认值
        String value = preferences.getString(NETWORK_ENVIRONMENT, "1");
        if (!mCurrentNetworkEnvironment.equalsIgnoreCase(value)) {
            //不一致.说明有修改，从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
        } else {
            //一致  没有修改则关闭当前页面
            finish();
        }
    }

    /**
     * 是否为正式环境
     */
    public static boolean isFormalEnvironment(Application application) {
        //获取当前应用的缓存
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(application);
        String networkEnvironment = preferences.getString(NETWORK_ENVIRONMENT, "1");
        return "1".equalsIgnoreCase(networkEnvironment);
    }
}
