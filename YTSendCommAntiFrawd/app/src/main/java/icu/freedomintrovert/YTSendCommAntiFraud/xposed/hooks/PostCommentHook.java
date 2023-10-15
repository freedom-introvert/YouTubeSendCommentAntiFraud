package icu.freedomintrovert.YTSendCommAntiFraud.xposed.hooks;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;

import com.google.protobuf.InvalidProtocolBufferException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

import icu.freedomintrovert.YTSendCommAntiFraud.Action;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.Actions;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.CreatCommentRequest;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.CreatCommentResponse;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.Ids;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.RunAttestationCommand;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.BaseHook;

public class PostCommentHook extends BaseHook {
    Map<Object, ByteArrayOutputStream> callbackMap = new HashMap<>();
    AtomicReference<Context> currentContext = new AtomicReference<>();

    /*
    适配版本：
    18.32.36(1539433920)
    18.40.33(1540476352)
    请勿更新到不适配的版本，随时失效！
     */
    @Override
    public void startHook(int appVersionCode, ClassLoader classLoader) throws ClassNotFoundException {
        XposedHelpers.findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Context context = (Context) param.thisObject;
                currentContext.set(context);
                XposedBridge.log("context:" + context);
            }
        });

        if (appVersionCode < 1540476352) {
            hookUrlRequestCallbackImpl(classLoader,"ayng");
        } else {
            hookUrlRequestCallbackImpl(classLoader,"azyn");
        }

    }
    private void hookUrlRequestCallbackImpl(ClassLoader classLoader,String implName) throws ClassNotFoundException {
        XposedHelpers.findAndHookMethod(implName, classLoader, "onReadCompleted", classLoader.loadClass("org.chromium.net.UrlRequest"), classLoader.loadClass("org.chromium.net.UrlResponseInfo"), java.nio.ByteBuffer.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);

                Object urlRequest_Callback_sub_ayng = param.thisObject;
                Object urlResponseInfo = param.args[1];
                ByteBuffer byteBuffer = (ByteBuffer) param.args[2];
                String url = (String) XposedHelpers.callMethod(urlResponseInfo, "getUrl");
                //XposedBridge.log("url:" + url);
                if (url.contains("create_comment")) {
                    if (!callbackMap.containsKey(urlRequest_Callback_sub_ayng)) {
                        callbackMap.put(urlRequest_Callback_sub_ayng, new ByteArrayOutputStream());
                    }
                    ByteArrayOutputStream byteArrayOutputStream = callbackMap.get(urlRequest_Callback_sub_ayng);
                    //XposedBridge.log("onReadCompleted UrlResponseInfo:"+urlResponseInfo);
                    //XposedBridge.log(byteBuffer.toString());
                    byteArrayOutputStream.write(bytebuffer2ByteArray(byteBuffer));
                    //XposedBridge.log("onReadCompleted buffer:" + Base64.encodeToString(byteBuffer.array(), Base64.DEFAULT));
                }
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });

        XposedHelpers.findAndHookMethod(implName, classLoader, "onSucceeded", classLoader.loadClass("org.chromium.net.UrlRequest"), classLoader.loadClass("org.chromium.net.UrlResponseInfo"), new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Object urlRequest = param.args[0];
                Object urlResponseInfo = param.args[1];
                String url = (String) XposedHelpers.callMethod(urlResponseInfo, "getUrl");
                ArrayList<AbstractMap.SimpleImmutableEntry<String, String>> headers = (ArrayList<AbstractMap.SimpleImmutableEntry<String, String>>) XposedHelpers.getObjectField(urlRequest, "s");
                if (url.startsWith("https://youtubei.googleapis.com/youtubei/")) {
                    outHeadersToFile(headers);
                }
                if (url.contains("create_comment")) {
                    //XposedBridge.log("onSucceeded callback:"+XposedHelpers.getObjectField(param.thisObject,"a"));
                    ByteArrayOutputStream byteArrayOutputStream = callbackMap.get(param.thisObject);
                    //XposedBridge.log("list element type:"+headers.get(0).getClass().getCanonicalName());
                    FileOutputStream fileOutputStream = new FileOutputStream("/data/data/com.google.android.youtube/files/resp.pb");
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    fileOutputStream.close();
                    //XposedBridge.log("urlRequest:"+urlRequest);
                    Object cronetUploadDataStream = XposedHelpers.getObjectField(urlRequest, "f");
                    if (cronetUploadDataStream != null) {
                        Object uploadDataProvider_aynf = XposedHelpers.getObjectField(cronetUploadDataStream, "b");
                        Object uploadDataProvider_sub_aylc = XposedHelpers.getObjectField(uploadDataProvider_aynf, "a");
                        ByteBuffer byteBuffer = (ByteBuffer) XposedHelpers.getObjectField(uploadDataProvider_sub_aylc, "a");
                        FileOutputStream fileOutputStream1 = new FileOutputStream("/data/data/com.google.android.youtube/files/req.pb");
                        fileOutputStream1.write(byteBuffer.array());
                        fileOutputStream1.close();
                        startCheck(byteBuffer.array(), byteArrayOutputStream.toByteArray(), headers);

                    }
                    callbackMap.remove(param.thisObject);
                }

            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });
    }

    private synchronized void outHeadersToFile(ArrayList<AbstractMap.SimpleImmutableEntry<String,String>> headers){
        File mediaDir = new File("/storage/emulated/0/Android/media/com.google.android.youtube/");
        if (!mediaDir.exists()){
            mediaDir.mkdirs();
        }
        File headersFile = new File(mediaDir,"headers.txt");
        StringBuilder sb = new StringBuilder();
        boolean hasAuthorization = false;
        for (int i = 0; i < headers.size(); i++) {
            String key = headers.get(i).getKey();
            if (key.equals("Authorization")){
                hasAuthorization = true;
            }
            sb.append(key).append("=").append(headers.get(i).getValue());
            if (i != headers.size() -1){
                sb.append("\n");
            }
        }
        if (hasAuthorization) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(headersFile);
                fileOutputStream.write(sb.toString().getBytes(StandardCharsets.UTF_8));
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startCheck(byte[] requestBody,byte[] responseBody,ArrayList<AbstractMap.SimpleImmutableEntry<String,String>> headers) throws InvalidProtocolBufferException {
        CreatCommentRequest creatCommentRequest = CreatCommentRequest.parseFrom(requestBody);
        CreatCommentResponse creatCommentResponse = CreatCommentResponse.parseFrom(responseBody);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("icu.freedomintrovert.YTSendCommAntiFraud", "icu.freedomintrovert.YTSendCommAntiFraud.ByXposedLunchedActivity"));
        intent.putExtra("context1", creatCommentRequest.getContext1().toByteArray());
        intent.putExtra("commentText",creatCommentRequest.getCommentText());
        RunAttestationCommand runAttestationCommand = creatCommentResponse.getActions().getRunAttestationCommand();
        String commentId = runAttestationCommand.getIds(0).getCommentId();
        String videoId = runAttestationCommand.getIds(1).getEncryptedVideoId();
        intent.putExtra("commentId",commentId);
        intent.putExtra("videoId",videoId);
        Bundle headersBundle = new Bundle();
        for (AbstractMap.SimpleImmutableEntry<String, String> header : headers) {
            headersBundle.putString(header.getKey(), header.getValue());
        }
        intent.putExtra("headers", headersBundle);
        intent.putExtra("action", Action.CHECK_VIDEO_COMMENT);

        //由于视频评论区与社区评论区共用一个api但请求体响应体存在很大差异，若未获取到视频ID则说明是社区不跳转检查
        if (!TextUtils.isEmpty(videoId)) {
            currentContext.get().startActivity(intent);
        }
    }

    public static byte[] bytebuffer2ByteArray(ByteBuffer buffer) {
        //重置 limit 和postion 值
        buffer.flip();
        //获取buffer中有效大小
        int len=buffer.limit() - buffer.position();

        byte [] bytes=new byte[len];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i]=buffer.get();
        }

        return bytes;
    }
}
