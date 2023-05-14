package com.android.testaar;

import java.lang.ref.WeakReference;
import java.util.ListIterator;
import java.util.Stack;

public class AppManager {

    private Stack<WeakReference<BaseActivity>> mStack;

    /**
     * 单例静态内部类
     */
    private static final class SingletonHodler {
        private static final AppManager INSTANCE = new AppManager();
    }


    private AppManager() {
        mStack = new Stack<>();
    }

    public static AppManager getInstance() {
        return SingletonHodler.INSTANCE;
    }

    /**
     * 添加activity
     *
     * @param activity act
     */
    public void addActivity(BaseActivity activity) {
        WeakReference<BaseActivity> weakReference = new WeakReference<>(activity);
        mStack.add(weakReference);
    }

    /**
     * activity出栈
     * 同时结束指定类名的activity
     *
     * @param cls 类对象
     */
    public void removeActivity(Class<?> cls) {
        if (mStack.empty()) {
          //  ToastUtils.show("activity stack is empty, can't remove");
            return;
        }
        ListIterator<WeakReference<BaseActivity>> iterator = mStack.listIterator();
        while (iterator.hasNext()) {
            BaseActivity activity = iterator.next().get();
            if (activity == null) {
                iterator.remove();
                continue;
            }
            if (activity.getClass() == cls) {
                activity.finish();
                iterator.remove();
            }
        }
    }

    /**
     * activity出栈
     * 同时结束指定类名的activity
     *
     * @param activity act
     */
    public void removeActivity(BaseActivity activity) {
        if (mStack.empty()) {
            activity.finish();
            return;
        }

        ListIterator<WeakReference<BaseActivity>> iterator = mStack.listIterator();
        while (iterator.hasNext()) {
            BaseActivity act = iterator.next().get();
            if (act == null) {
                iterator.remove();
                continue;
            }
            if (act == activity) {
                activity.finish();
                iterator.remove();
            }
        }
    }

    /**
     * @param activity activity
     */
    public void removeOtherActivity(BaseActivity activity){
        if (mStack.empty()) {
            return;
        }

        ListIterator<WeakReference<BaseActivity>> iterator = mStack.listIterator();
        while (iterator.hasNext()) {
            BaseActivity act = iterator.next().get();
            if (act == null) {
                iterator.remove();
                continue;
            }
            if (act != activity) {
                activity.finish();
                iterator.remove();
            }
        }
    }

    /**
     * @param cls activity
     */
    public void removeOtherActivity(Class<?> cls){
        if (mStack.empty()) {
            return;
        }

        ListIterator<WeakReference<BaseActivity>> iterator = mStack.listIterator();
        while (iterator.hasNext()) {
            BaseActivity activity = iterator.next().get();
            if (activity == null) {
                iterator.remove();
                continue;
            }
            if (activity.getClass() != cls) {
                activity.finish();
                iterator.remove();
            }
        }
    }

    /**
     * 结束所有activity
     * 从最后入栈的开始finish
     */
    public void removeAllActivity() {
        if (mStack.empty()) {
            return;
        }
        for (int i = mStack.size() - 1; i >= 0; i--) {
            BaseActivity activity = mStack.get(i).get();
            if (activity != null) {
                activity.finish();
            }
        }
        mStack.clear();
    }

    /**
     * 判断栈里面是否存在指定的activity
     *
     * @param cls 指定activity对象
     * @return true：存在；false：不存在
     */
    public boolean existActivity(Class<?> cls) {
        if (mStack.empty()) {
            return false;
        }
        ListIterator<WeakReference<BaseActivity>> iterator = mStack.listIterator();
        while (iterator.hasNext()) {
            BaseActivity activity = iterator.next().get();
            if (activity == null) {
                iterator.remove();
                continue;
            }
            if (activity.getClass() == cls) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取栈顶元素
     */
    public BaseActivity getTopActivity() {
        if (mStack.empty()) {
            return null;
        }
        WeakReference<BaseActivity> peek = mStack.peek();
        return peek.get();
    }

    /**
     * 判断当前activity是不是栈顶
     *
     * @param cls 类
     * @return true:栈顶；false:不是栈顶
     */
    public boolean isTop(Class<?> cls) {
        if (mStack.empty()) {
            return false;
        }
        WeakReference<BaseActivity> weakReference = mStack.peek();
        BaseActivity baseActivity = weakReference.get();
        return cls == baseActivity.getClass();
    }
}