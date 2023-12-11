package icu.freedomintrovert.YTSendCommAntiFraud;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.Date;
import java.util.Locale;

import icu.freedomintrovert.YTSendCommAntiFraud.comment.Comment;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.HistoryComment;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.VideoComment;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.VideoCommentSection;
import icu.freedomintrovert.YTSendCommAntiFraud.db.StatisticsDB;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.nestedIntoBase64.SortBy;
import icu.freedomintrovert.YTSendCommAntiFraud.rxObservables.FindCommentObservableOnSubscribe;
import icu.freedomintrovert.YTSendCommAntiFraud.utils.OkHttpUtils;
import icu.freedomintrovert.YTSendCommAntiFraud.utils.ProtobufUtils;
import icu.freedomintrovert.YTSendCommAntiFraud.view.ProgressBarDialog;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DialogCommentChecker {
    Context context;
    StatisticsDB statisticsDB;
    Config config;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public DialogCommentChecker(Context context, StatisticsDB statisticsDB, Config config) {
        this.context = context;
        this.statisticsDB = statisticsDB;
        this.config = config;
    }

    public void check(Comment comment, String videoId, int commentSectionType, byte[] context1, Bundle headers, boolean needToWait, OnExitListener onExitListener, Date sendDate){
        VideoCommentSection videoCommentSection = new VideoCommentSection(OkHttpUtils.getOkHttpClient(),
                context1,
                ProtobufUtils.escapeBase64(Base64.encodeToString(ProtobufUtils.generateContinuation(videoId, SortBy.latest).toByteArray(), Base64.DEFAULT)),
                headers,
                true);
        String msg = context.getString(R.string.wait_xxx_ms);
        long waitTime = config.getWaitTimeAfterCommentSent();
        ProgressBarDialog progressBarDialog = new ProgressBarDialog.Builder(context)
                .setTitle(context.getString(R.string.checking))
                .setMessage(String.format(Locale.getDefault(), msg, 0, waitTime))
                .setIndeterminate(true)
                .setPositiveButton(context.getString(R.string.waiting_in_the_background),null)
                .setCancelable(false)
                .show();
        FindCommentObservableOnSubscribe findCommentObservableOnSubscribe = new FindCommentObservableOnSubscribe(videoCommentSection, new VideoComment(comment.commentText, comment.commentId, videoId), statisticsDB, config, needToWait, sendDate);
        Observable<FindCommentObservableOnSubscribe.BaseNextValue> observable = Observable.create(findCommentObservableOnSubscribe);
        DisposableObserver<FindCommentObservableOnSubscribe.BaseNextValue> disposableObserver = new DisposableObserver<>() {

            @Override
            protected void onStart() {
                progressBarDialog.setIndeterminate(false);
            }

            @Override
            public void onNext(@NonNull FindCommentObservableOnSubscribe.BaseNextValue value) {
                if (value instanceof FindCommentObservableOnSubscribe.OnNewSleepProgressValue) {
                    FindCommentObservableOnSubscribe.OnNewSleepProgressValue progressValue = (FindCommentObservableOnSubscribe.OnNewSleepProgressValue) value;
                    progressBarDialog.setMessage(String.format(Locale.getDefault(), msg, progressValue.waitedTime, waitTime));
                    progressBarDialog.setProgress(progressValue.progress);
                } else if (value instanceof FindCommentObservableOnSubscribe.OnStartCheckValue) {
                    progressBarDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                    progressBarDialog.setIndeterminate(true);
                } else if (value instanceof FindCommentObservableOnSubscribe.OnNextPageFormHasAccountValue) {
                    FindCommentObservableOnSubscribe.OnNextPageFormHasAccountValue nextPageValue = (FindCommentObservableOnSubscribe.OnNextPageFormHasAccountValue) value;
                    progressBarDialog.setMessage(String.format(Locale.getDefault(), context.getString(R.string.find_has_account_comment_list_dialog), nextPageValue.pageNumber));
                } else if (value instanceof FindCommentObservableOnSubscribe.OnNextPageFormNotAccountValue) {
                    FindCommentObservableOnSubscribe.OnNextPageFormNotAccountValue nextPageValue = (FindCommentObservableOnSubscribe.OnNextPageFormNotAccountValue) value;
                    progressBarDialog.setMessage(String.format(Locale.getDefault(), context.getString(R.string.find_not_account_comment_list_dialog), nextPageValue.pageNumber));
                } else if (value instanceof FindCommentObservableOnSubscribe.OnFondYourCommentFromHasAccountVale) {
                    progressBarDialog.setMessage(context.getString(R.string.continue_to_check_if_is_shadow_banned_dialog));
                } else if (value instanceof FindCommentObservableOnSubscribe.OnSearchAgainProgressForNoAnchorValue){
                    FindCommentObservableOnSubscribe.OnSearchAgainProgressForNoAnchorValue value1 = (FindCommentObservableOnSubscribe.OnSearchAgainProgressForNoAnchorValue) value;
                    progressBarDialog.setMessage(String.format(Locale.getDefault(), context.getString(R.string.OnSearchAgainProgressForNoAnchor), value1.retry, value1.totalRetry, value1.waitedTime, value1.interval));
                    progressBarDialog.setIndeterminate(false);
                    progressBarDialog.setProgress(value1.progress);
                } else if (value instanceof FindCommentObservableOnSubscribe.OnSearchAgainProgressValue) {
                    FindCommentObservableOnSubscribe.OnSearchAgainProgressValue value1 = (FindCommentObservableOnSubscribe.OnSearchAgainProgressValue) value;
                    progressBarDialog.setMessage(String.format(Locale.getDefault(), context.getString(R.string.OnSearchAgainProgress), value1.retry, value1.totalRetry, value1.waitedTime, value1.interval));
                    progressBarDialog.setIndeterminate(false);
                    progressBarDialog.setProgress(value1.progress);
                } else if (value instanceof FindCommentObservableOnSubscribe.OnSearchAgainGetCommentsValue) {
                    FindCommentObservableOnSubscribe.OnSearchAgainGetCommentsValue value1 = (FindCommentObservableOnSubscribe.OnSearchAgainGetCommentsValue) value;
                    progressBarDialog.setMessage(String.format(Locale.getDefault(), context.getString(R.string.OnSearchAgainGetComments), value1.pageNumber));
                    progressBarDialog.setIndeterminate(true);
                } else if (value instanceof FindCommentObservableOnSubscribe.OnReCheckIfIsDeletedValue){
                    FindCommentObservableOnSubscribe.OnReCheckIfIsDeletedValue value1 = (FindCommentObservableOnSubscribe.OnReCheckIfIsDeletedValue) value;
                    progressBarDialog.setMessage(String.format(Locale.getDefault(),context.getString(R.string.OnReCheckIfIsDeleted),value1.pageNumber));
                } else if (value instanceof FindCommentObservableOnSubscribe.OnYourCommentIsDeleteStateValue) {
                    progressBarDialog.dismiss();
                    dialogState(context.getString(R.string.check_result), context.getString(R.string.comment_has_been_deleted) + comment.commentText, HistoryComment.STATE_DELETED,onExitListener);
                } else if (value instanceof FindCommentObservableOnSubscribe.OnYourCommentIsShadowBanStateValue) {
                    progressBarDialog.dismiss();
                    dialogState(context.getString(R.string.check_result), context.getString(R.string.comment_has_been_shadow_banned) + comment.commentText,HistoryComment.STATE_SHADOW_BAN,onExitListener);
                } else if (value instanceof FindCommentObservableOnSubscribe.OnYourCommentIsNormalStateValue) {
                    progressBarDialog.dismiss();
                    dialogState(context.getString(R.string.check_result), context.getString(R.string.the_comment_is_normal) + comment.commentText,HistoryComment.STATE_NORMAL,onExitListener);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                progressBarDialog.dismiss();
                if (e instanceof VideoCommentSection.Code401Exception){
                    onExitListener.onExit(OnExitListener.EXIT_CODE_HTTP_STATUS_401);
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.error)
                            .setMessage(String.format(context.getString(R.string.an_error_occurred),e.getMessage()))
                            .setCancelable(false)
                            .setPositiveButton(android.R.string.ok, (dialog, which) -> check(comment, videoId, commentSectionType, context1, headers, needToWait, onExitListener, sendDate))
                            .setNegativeButton(android.R.string.cancel, (dialog, which) -> onExitListener.onExit(OnExitListener.EXIT_CODE_NET_ERROR)).show();
                }
            }

            @Override
            public void onComplete() {}
        };
        observable.subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);

        progressBarDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(v -> {
            if (checkNotificationPermission(context)){
                findCommentObservableOnSubscribe.stop();
                progressBarDialog.dismiss();
                onExitListener.onExit(OnExitListener.EXIT_CODE_BACKSTAGE_WAIT);
                Intent intent = new Intent(context, BackstageWaitService.class);
                intent.putExtra("action",Action.RESUME_CHECK_VIDEO_COMMENT);
                intent.putExtra("commentId",comment.commentId);
                intent.putExtra("commentText",comment.commentText);
                intent.putExtra("videoId",videoId);
                intent.putExtra("context1",context1);
                intent.putExtra("headers",headers);
                context.startService(intent);
            } else {
                Toast.makeText(context, R.string.please_grant_notification_permission, Toast.LENGTH_LONG).show();
                requestNotificationPermission(context);
            }
        });
    }

    private boolean checkNotificationPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                return notificationManager.areNotificationsEnabled();
            }
        } else {
            // 对于Android 6.0及以下版本，无法直接检查通知权限，需要用户手动设置
            return true;
        }
        return false;
    }

    public static void requestNotificationPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "无法自动请求通知权限，请手动设置", Toast.LENGTH_SHORT).show();
        }
    }

    private void dialogState(String title, String message,String state,OnExitListener onExitListener) {
        compositeDisposable.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle(title)
                .setCancelable(false);
        switch (state) {
            case HistoryComment.STATE_NORMAL:
                builder.setPositiveButton(android.R.string.ok,new OkOnClickListener(onExitListener,OnExitListener.EXIT_CODE_COMMENT_NORMAL));
                builder.setIcon(R.drawable.normal_black);
                break;
            case HistoryComment.STATE_SHADOW_BAN:
                builder.setPositiveButton(android.R.string.ok,new OkOnClickListener(onExitListener,OnExitListener.EXIT_CODE_COMMENT_SHADOW_BAN));
                builder.setIcon(R.drawable.hide_black);
                break;
            case HistoryComment.STATE_DELETED:
                builder.setPositiveButton(android.R.string.ok,new OkOnClickListener(onExitListener,OnExitListener.EXIT_CODE_COMMENT_DELETED));
                builder.setIcon(R.drawable.deleted_black);
                break;
        }
        builder.show();
    }

    public static class OkOnClickListener implements DialogInterface.OnClickListener{

        OnExitListener listener;
        int exitCode;

        public OkOnClickListener(OnExitListener listener, int exitCode) {
            this.listener = listener;
            this.exitCode = exitCode;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            listener.onExit(exitCode);
        }
    }

    public interface OnExitListener{
        int EXIT_CODE_HTTP_STATUS_401 = -401;
        int EXIT_CODE_NET_ERROR = -1;
        int EXIT_CODE_BACKSTAGE_WAIT = 0;
        int EXIT_CODE_COMMENT_NORMAL = 1;
        int EXIT_CODE_COMMENT_SHADOW_BAN = 2;
        int EXIT_CODE_COMMENT_DELETED = 3;
        void onExit(int exitCode);
    }
}
