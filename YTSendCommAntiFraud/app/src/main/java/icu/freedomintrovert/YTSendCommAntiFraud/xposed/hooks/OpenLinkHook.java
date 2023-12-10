package icu.freedomintrovert.YTSendCommAntiFraud.xposed.hooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import de.robv.android.xposed.XC_MethodHook;
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
                            Toast.makeText((Context) param.thisObject, "已将追踪式重定向链接转为正常链接：" + url, Toast.LENGTH_SHORT).show();
                            XposedBridge.log(String.format("已将YT追踪式重定向链接转为正常链接[%s ==> %s]", data.toString(), url));
                        }
                    }
                }
            }
        });
    }
}
