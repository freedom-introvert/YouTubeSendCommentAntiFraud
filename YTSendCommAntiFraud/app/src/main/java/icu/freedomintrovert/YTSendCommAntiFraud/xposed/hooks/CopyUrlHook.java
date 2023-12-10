package icu.freedomintrovert.YTSendCommAntiFraud.xposed.hooks;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import icu.freedomintrovert.YTSendCommAntiFraud.BuildConfig;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.BaseHook;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.InHookXConfig;

public class CopyUrlHook extends BaseHook {
    @Override
    public void startHook(int appVersionCode, ClassLoader classLoader) throws ClassNotFoundException {
        XposedHelpers.findAndHookMethod(ClipboardManager.class, "setPrimaryClip", ClipData.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Context context = (Context) XposedHelpers.getObjectField(param.thisObject,"mContext");
                ClipData clipData = (ClipData) param.args[0];
                ClipData.Item item = clipData.getItemAt(0);
                if (item == null) {
                    return;
                }
                if (item.getText() == null){
                    return;
                }
                String text = item.getText().toString();
                XposedBridge.log("YouTube要复制的文本："+text);
                int questionMarkIndex = text.indexOf("?si=");
                String newText = questionMarkIndex != -1 ? text.substring(0, questionMarkIndex) : text;
                if (questionMarkIndex != -1){
                    XposedBridge.log(String.format("已将YT分享链接的追踪参数\"?si=xxx\"去除:[%s ==> %s]",text,newText));
                    Toast.makeText(context, "已将YT分享链接的追踪参数\"?si=xxx\"去除，净化后的链接："+newText, Toast.LENGTH_LONG).show();
                }
                param.args[0] = ClipData.newPlainText(null,newText);
            }
        });
    }

}
