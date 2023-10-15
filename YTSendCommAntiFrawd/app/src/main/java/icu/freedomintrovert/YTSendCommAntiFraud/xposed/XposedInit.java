package icu.freedomintrovert.YTSendCommAntiFraud.xposed;

import android.content.Context;

import java.util.Arrays;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.hooks.PostCommentHook;


public class XposedInit implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.google.android.youtube")) {

            ClassLoader classLoader = loadPackageParam.classLoader;
            int appVersionCode = systemContext().getPackageManager().getPackageInfo(loadPackageParam.packageName, 0).versionCode;
            XposedBridge.log("YouTube version code:" + appVersionCode);
            HookStater hookStater = new HookStater(appVersionCode,classLoader);
            hookStater.startHook(new PostCommentHook());
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
