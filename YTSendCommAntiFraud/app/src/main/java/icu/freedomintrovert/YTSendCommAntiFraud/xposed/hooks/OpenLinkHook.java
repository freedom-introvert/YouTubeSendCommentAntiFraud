package icu.freedomintrovert.YTSendCommAntiFraud.xposed.hooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Locale;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import icu.freedomintrovert.YTSendCommAntiFraud.BuildConfig;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.BaseHook;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.InHookXConfig;

public class OpenLinkHook extends BaseHook {

    @Override
    public void startHook(int appVersionCode, ClassLoader classLoader) throws ClassNotFoundException {
        XposedHelpers.findAndHookMethod(Activity.class, "startActivity", Intent.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Intent intent = (Intent) param.args[0];
                String action = intent.getAction();
                if (action != null && action.equals(Intent.ACTION_VIEW)) {
                    Uri data = intent.getData();
                    if (data != null && data.getPath() != null && data.getPath().equals("/redirect")) {
                        String url = data.getQueryParameter("q");
                        if (url != null) {
                            intent.setData(Uri.parse(url));
                            String msg = "Tracked redirect links have been converted into normal links:";
                            if (Locale.getDefault().getLanguage().equals("zh")){
                                msg = "已将追踪式重定向链接转为正常链接：";
                            }
                            Toast.makeText((Context) param.thisObject, msg + url, Toast.LENGTH_SHORT).show();
                            XposedBridge.log(String.format(msg+"[%s ==> %s]", data.toString(), url));
                        }
                    }
                }
            }
        });

       /* XposedHelpers.findAndHookMethod("hfq", classLoader, "c", android.content.res.Configuration.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                XposedBridge.log("Configuration:"+param.args[0]);
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });*/
    }
}
