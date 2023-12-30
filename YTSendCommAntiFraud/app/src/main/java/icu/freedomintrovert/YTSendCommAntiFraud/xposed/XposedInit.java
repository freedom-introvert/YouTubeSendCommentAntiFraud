package icu.freedomintrovert.YTSendCommAntiFraud.xposed;

import android.content.Context;

import java.util.Arrays;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.hooks.CopyUrlHook;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.hooks.OpenLinkHook;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.hooks.PostCommentHook;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.hooks.SetOrientationHook;


public class XposedInit implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.google.android.youtube") || loadPackageParam.packageName.equals("app.rvx.android.youtube")) {
            InHookXConfig config = InHookXConfig.getInstance();
            ClassLoader classLoader = loadPackageParam.classLoader;
            int appVersionCode = systemContext().getPackageManager().getPackageInfo(loadPackageParam.packageName, 0).versionCode;
            XposedBridge.log("YouTube version code:" + appVersionCode);
            HookStater hookStater = new HookStater(appVersionCode,classLoader);
            hookStater.startHook(new PostCommentHook());//因有时候要抓取请求头，所以若关闭该选项仅不打开activity，保留记录请求头
            if (config.getShareUrlAntiTrackingEnabled()) {
                hookStater.startHook(new CopyUrlHook());
            }
            if (config.getRemoveOpenLinkRedirectionEnabled()) {
                hookStater.startHook(new OpenLinkHook());
            }
            if (config.getEnableProhibitRotation()){
                hookStater.startHook(new SetOrientationHook());
            }
        } if (loadPackageParam.packageName.equals("icu.freedomintrovert.YTSendCommAntiFraud")){
            XposedHelpers.findAndHookMethod("icu.freedomintrovert.YTSendCommAntiFraud.MainActivity", loadPackageParam.classLoader, "isModuleActive", XC_MethodReplacement.returnConstant(true));
        }
    }

    public static Context systemContext() {
        Object obj = null;
        Class<?> findClassIfExists = XposedHelpers.findClass("android.app.ActivityThread", null);
        if (findClassIfExists != null) {
            obj = XposedHelpers.callStaticMethod(findClassIfExists, "currentActivityThread", Arrays.copyOf(new Object[0], 0));
        }
        return (Context) XposedHelpers.callMethod(obj, "getSystemContext", Arrays.copyOf(new Object[0], 0));
    }
}
