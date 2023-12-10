package icu.freedomintrovert.YTSendCommAntiFraud;

import android.app.Activity;

public class ActivityRec {
    public static Activity activity;

    public static void setActivity(Activity activity) {
        ActivityRec.activity = activity;
    }

    public static void finish(){
        if (activity != null){
            activity.finish();
        }
    }

}
