package icu.freedomintrovert.YTSendCommAntiFraud;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {
    public static final int SORT_RULER_DATE_DESC = 0;
    public static final int SORT_RULER_DATE_ASC = 1;
    private final SharedPreferences sp_config;


    public Config(Context context) {
        sp_config = context.getSharedPreferences("config",Context.MODE_PRIVATE);
    }

    public void setEnableRecordingHistoricalComments(boolean enable){
        sp_config.edit().putBoolean("recording_historical_comments",enable).apply();
    }

    public boolean getEnableRecordingHistoricalComments(){
        return sp_config.getBoolean("recording_historical_comments",true);
    }

    public void setWaitTimeAfterCommentSent(long waitTime){
        sp_config.edit().putLong("wait_time_after_comment_sent",waitTime).apply();
    }

    public long getWaitTimeAfterCommentSent(){
        return sp_config.getLong("wait_time_after_comment_sent", 15000);
    }

    public void setIntervalOfSearchAgain(long interval){
        sp_config.edit().putLong("interval_of_search_again",interval).apply();
    }

    public long getIntervalOfSearchAgain(){
        return sp_config.getLong("interval_of_search_again",5000);
    }

    public void setMaximumNumberOfSearchAgain(int maximumNumberOfSearchAgain){
        sp_config.edit().putInt("maximum_number_of_search_again",maximumNumberOfSearchAgain).apply();
    }

    public int getMaximumNumberOfSearchAgain(){
        return sp_config.getInt("maximum_number_of_search_again",6);
    }

    public void setWaitTimeByBackstage(long time){
        sp_config.edit().putLong("wait_time_by_backstage",time).apply();
    }

    public long getWaitTimeByBackstage(){
        return sp_config.getLong("wait_time_by_backstage", 120);
    }

    public void setFilterRulerEnableNormal(boolean enable){
        sp_config.edit().putBoolean("filter_ruler_enable_normal",enable).apply();
    }

    public boolean getFilterRulerEnableNormal(){
        return sp_config.getBoolean("filter_ruler_enable_normal", true);
    }

    public void setFilterRulerEnableShadowBan(boolean enable){
        sp_config.edit().putBoolean("filter_ruler_enable_shadow_ban",enable).apply();
    }

    public boolean getFilterRulerEnableShadowBan(){
        return sp_config.getBoolean("filter_ruler_enable_shadow_ban",true);
    }

    public void setFilterRulerEnableDeleted(boolean enable){
        sp_config.edit().putBoolean("filter_ruler_enable_deleted",enable).apply();
    }

    public boolean getFilterRulerEnableDelete(){
        return sp_config.getBoolean("filter_ruler_enable_deleted",true);
    }

    public void setSortRuler(int sortRuler){
        sp_config.edit().putInt("sort_ruler",sortRuler).apply();
    }

    public int getSortRuler(){
        return sp_config.getInt("sort_ruler", SORT_RULER_DATE_DESC);
    }


}
