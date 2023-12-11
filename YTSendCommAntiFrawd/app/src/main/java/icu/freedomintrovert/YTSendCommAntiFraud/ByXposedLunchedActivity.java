package icu.freedomintrovert.YTSendCommAntiFraud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import java.util.Date;

import icu.freedomintrovert.YTSendCommAntiFraud.comment.Comment;

import icu.freedomintrovert.YTSendCommAntiFraud.comment.ToBeCheckedComment;

import icu.freedomintrovert.YTSendCommAntiFraud.db.StatisticsDB;


public class ByXposedLunchedActivity extends AppCompatActivity {

    Context context;
    Config config;
    StatisticsDB statisticsDB;
    DialogCommentChecker dialogCommentChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityRec.finish();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_xposed_lunched);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        context = this;
        config = new Config(context);
        statisticsDB = new StatisticsDB(context);
        dialogCommentChecker = new DialogCommentChecker(context,statisticsDB,config);
        if (extras == null){
            return;
        }
        if (extras.getInt("action", -1) == Action.CHECK_VIDEO_COMMENT) {
            String comment = extras.getString("commentText");
            String videoId = extras.getString("videoId");
            String commentId = extras.getString("commentId");
            dialogCommentChecker.check(new Comment(comment, commentId), videoId, ToBeCheckedComment.COMMENT_SECTION_TYPE_VIDEO, extras.getByteArray("context1"), extras.getBundle("headers"), true, exitCode -> {
                if (exitCode == DialogCommentChecker.OnExitListener.EXIT_CODE_HTTP_STATUS_401){
                    Toast.makeText(context, "获取评论列表失败！HTTP状态码401", Toast.LENGTH_SHORT).show();
                }
                finish();
            },new Date());
            //可以开发反诈了嘿嘿嘿！！
        } else if (extras.getInt("action", -1) == Action.RESUME_CHECK_VIDEO_COMMENT) {
            String comment = extras.getString("commentText");
            String videoId = extras.getString("videoId");
            String commentId = extras.getString("commentId");
            dialogCommentChecker.check(new Comment(comment, commentId), videoId, ToBeCheckedComment.COMMENT_SECTION_TYPE_VIDEO, extras.getByteArray("context1"), extras.getBundle("headers"), false, exitCode -> {
                if (exitCode == DialogCommentChecker.OnExitListener.EXIT_CODE_HTTP_STATUS_401){
                    Toast.makeText(context, "获取评论列表失败！HTTP状态码401", Toast.LENGTH_SHORT).show();
                }
                finish();
            },new Date());
        }

    }


}