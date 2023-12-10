package icu.freedomintrovert.YTSendCommAntiFraud;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import icu.freedomintrovert.YTSendCommAntiFraud.view.VoidDialogInterfaceOnClickListener;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.InAppXConfig;
import icu.freedomintrovert.YTSendCommAntiFraud.xposed.XConfig;


public class MainActivity extends AppCompatActivity {

    Context context;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    LinearLayout ll_history_comment,ll_wait_time,ll_not_checked_comment;
    boolean enableRecorde;
    Config config;
    XConfig xConfig;
    private SwitchCompat sw_save_history;
    private SwitchCompat sw_enable_fuck_censorship,sw_share_url_anti_tracking,sw_enable_simplify_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        config = new Config(context);
        ActivityRec.setActivity(this);
        initView();
        initSaveHistoryCommentSW();

        ImageView imageView = findViewById(R.id.img_module_status);
        TextView textView = findViewById(R.id.txv_module_status);
        if (isModuleActive()){
            textView.setText(R.string.module_enabled);
            imageView.setImageDrawable(getDrawable(R.drawable.baseline_done_all_24));
        } else {
            textView.setText(R.string.module_is_not_enabled);
            imageView.setImageDrawable(getDrawable(R.drawable.baseline_extension_off_24));
        }

        xConfig = InAppXConfig.newInstance(context);
        initConfigurationSW();
        //StatisticsDB statisticsDB = new StatisticsDB(context);
        //statisticsDB.insertVideoHistoryComment(new VideoHistoryComment("暖心的视频网站，暖心到全球变暖\n暖心的视频网站，暖心到全球变暖","114514191", HistoryComment.STATE_NORMAL,new Date(),"XXXXXXX"));
        //Log.d("tag",statisticsDB.queryAllVideoHistoryComments().toString());

    }

    private void initConfigurationSW() {
        sw_enable_fuck_censorship = findViewById(R.id.sw_enable_fuck_censorship);
        sw_share_url_anti_tracking = findViewById(R.id.sw_share_url_anti_tracking);
        sw_enable_simplify_url = findViewById(R.id.sw_enable_simplify_url);
        //如果无法读取配置文件，则锁定开关为关闭
        if (xConfig == null){
            sw_enable_fuck_censorship.setEnabled(false);
            sw_share_url_anti_tracking.setEnabled(false);
            sw_enable_simplify_url.setEnabled(false);
        } else {
            sw_enable_fuck_censorship.setChecked(xConfig.getAntiFraudEnabled());
            sw_enable_fuck_censorship.setOnCheckedChangeListener((buttonView, isChecked) -> {
                xConfig.setAntiFraudEnabled(isChecked);
            });
            sw_share_url_anti_tracking.setChecked(xConfig.getShareUrlAntiTrackingEnabled());
            sw_share_url_anti_tracking.setOnCheckedChangeListener((buttonView, isChecked) -> {
                xConfig.setShareUrlAntiTrackingEnabled(isChecked);
            });
            sw_enable_simplify_url.setChecked(xConfig.getRemoveOpenLinkRedirectionEnabled());
            sw_enable_simplify_url.setOnCheckedChangeListener((buttonView, isChecked) -> {
                xConfig.setRemoveOpenLinkRedirectionEnabled(isChecked);
            });
        }
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        ll_wait_time = findViewById(R.id.ll_wait_time);
        ll_history_comment = findViewById(R.id.ll_history_comment);
        ll_not_checked_comment = findViewById(R.id.not_checked_comment);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        ll_history_comment.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoHistoryCommentsActivity.class);
            startActivity(intent);
        });
        ll_not_checked_comment.setOnClickListener(v -> {
            Intent intent = new Intent(context, ToBeCheckedCommentListActivity.class);
            startActivity(intent);
        });
        ll_wait_time.setOnClickListener(v -> {
            View dialogView = View.inflate(context,R.layout.dialog_set_wait_time,null);
            EditText edt_waitTime = dialogView.findViewById(R.id.edit_text_wt_by_after_comment_sent);
            EditText edt_interval = dialogView.findViewById(R.id.edit_text_interval_of_search_again);
            EditText edt_maximum = dialogView.findViewById(R.id.edit_text_maximum_number_of_search_again);
            EditText edt_backstage = dialogView.findViewById(R.id.edit_text_wait_time_by_backstage);
            edt_waitTime.setText(String.valueOf(config.getWaitTimeAfterCommentSent()));
            edt_interval.setText(String.valueOf(config.getIntervalOfSearchAgain()));
            edt_maximum.setText(String.valueOf(config.getMaximumNumberOfSearchAgain()));
            edt_backstage.setText(String.valueOf(config.getWaitTimeByBackstage()));
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle(R.string.set_the_waiting_time_after_posting_the_comment)
                    .setView(dialogView)
                    .setPositiveButton(android.R.string.ok, (dialog1, which) -> {
                        config.setWaitTimeAfterCommentSent(Long.parseLong(edt_waitTime.getText().toString()));
                        config.setIntervalOfSearchAgain(Long.parseLong(edt_interval.getText().toString()));
                        config.setMaximumNumberOfSearchAgain(Integer.parseInt(edt_maximum.getText().toString()));
                        config.setWaitTimeByBackstage(Long.parseLong(edt_backstage.getText().toString()));
                    })
                    .setNegativeButton(android.R.string.cancel, new VoidDialogInterfaceOnClickListener())
                    .setNeutralButton(R.string.help, null)
                    .show();
            dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(v1 -> {

            });
        });
    }

    private void initSaveHistoryCommentSW() {
        enableRecorde = config.getEnableRecordingHistoricalComments();
        sw_save_history = findViewById(R.id.sw_save_history);
        ConstraintLayout cl_save_history_comment_sw = findViewById(R.id.cl_recorde_history_comment_sw);
        sw_save_history.setChecked(enableRecorde);
        cl_save_history_comment_sw.setOnClickListener(v -> {
            sw_save_history.setChecked(!enableRecorde);
        });

        sw_save_history.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                config.setEnableRecordingHistoricalComments(true);
                enableRecorde = true;
                Toast.makeText(context, R.string.enable_saving_history_comments, Toast.LENGTH_SHORT).show();
            } else {
                config.setEnableRecordingHistoricalComments(false);
                enableRecorde = false;
                Toast.makeText(context, R.string.disable_saving_history_comments, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean isModuleActive(){
        return false;
    }


}