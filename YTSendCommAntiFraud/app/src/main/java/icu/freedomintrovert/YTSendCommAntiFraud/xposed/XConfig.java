package icu.freedomintrovert.YTSendCommAntiFraud.xposed;

import android.content.SharedPreferences;

public abstract class XConfig {
    public static final String PREF_NAME = "yt_helper_config";
    private final SharedPreferences sp;

    protected XConfig(SharedPreferences sharedPreferences) {
        this.sp = sharedPreferences;
    }

    public boolean getAntiFraudEnabled() {
        return sp.getBoolean("anti_fraud_enabled", true);
    }

    public void setAntiFraudEnabled(boolean enable) {
        sp.edit().putBoolean("anti_fraud_enabled", enable).apply();
    }

    public boolean getShareUrlAntiTrackingEnabled() {
        return sp.getBoolean("share_url_anti_enabled", true);
    }

    public void setShareUrlAntiTrackingEnabled(boolean enabled) {
        sp.edit().putBoolean("share_url_anti_enabled", enabled).apply();
    }

    public boolean getRemoveOpenLinkRedirectionEnabled() {
        return sp.getBoolean("remove_open_link_redirection_enabled", true);
    }

    public void setRemoveOpenLinkRedirectionEnabled(boolean enable) {
        sp.edit().putBoolean("remove_open_link_redirection_enabled", enable).apply();
    }

    public boolean getEnableProhibitRotation() {
        return sp.getBoolean("enable_prohibit_rotation",true);
    }

    public void setEnableProhibitRotation(boolean enableProhibitRotation) {
        sp.edit().putBoolean("enable_prohibit_rotation",enableProhibitRotation).apply();
    }

}
