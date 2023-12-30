package icu.freedomintrovert.YTSendCommAntiFraud.xposed.hooks;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.BaseHook;

public class SetOrientationHook extends BaseHook {
    private long lastOnResumeTime = 0;

    @Override
    public void startHook(int appVersionCode, ClassLoader classLoader) throws ClassNotFoundException {
        XposedHelpers.findAndHookMethod(Activity.class, "setRequestedOrientation", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Activity activity = (Activity) param.thisObject;

                int orientation = (int) param.args[0];
                if (orientation == ActivityInfo.SCREEN_ORIENTATION_USER && activity.getResources().getConfiguration().orientation == 2 && lastOnResumeTime + 5000 >= System.currentTimeMillis()) {
                    XposedBridge.log("已禁止返回时旋转至竖屏");
                    param.args[0] = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
                }
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

            }
        });
        XposedHelpers.findAndHookMethod("com.google.android.apps.youtube.app.watchwhile.WatchWhileActivity", classLoader, "onResume", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                lastOnResumeTime = System.currentTimeMillis();
            }
        });
    }
}
