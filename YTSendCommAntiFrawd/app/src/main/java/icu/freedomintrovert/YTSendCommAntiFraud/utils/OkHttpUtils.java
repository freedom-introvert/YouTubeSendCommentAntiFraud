package icu.freedomintrovert.YTSendCommAntiFraud.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

public class OkHttpUtils {
    private static OkHttpClient okHttpClient;

    public static synchronized OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .build();
        }
        return okHttpClient;
    }

    public static void checkResponseBodyNotNull(ResponseBody body) throws IOException {
        if (body == null) {
            throw new IOException("ResponseBody is null");
        }
    }
}
