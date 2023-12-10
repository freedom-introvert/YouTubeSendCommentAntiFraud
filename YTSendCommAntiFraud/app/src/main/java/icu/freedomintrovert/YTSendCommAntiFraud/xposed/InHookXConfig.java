package icu.freedomintrovert.YTSendCommAntiFraud.xposed;

import android.content.SharedPreferences;

import de.robv.android.xposed.XSharedPreferences;
import icu.freedomintrovert.YTSendCommAntiFraud.BuildConfig;

public class InHookXConfig extends XConfig{

    public static final InHookXConfig config = new InHookXConfig(new XSharedPreferences(BuildConfig.APPLICATION_ID, PREF_NAME));
    protected InHookXConfig(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }

    public static InHookXConfig getInstance(){
        return config;
    }



}
