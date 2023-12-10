package icu.freedomintrovert.YTSendCommAntiFraud;

import androidx.annotation.Nullable;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import icu.freedomintrovert.YTSendCommAntiFraud.comment.Comment;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.HistoryComment;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.VideoHistoryComment;
import icu.freedomintrovert.YTSendCommAntiFraud.db.StatisticsDB;
import icu.freedomintrovert.YTSendCommAntiFraud.view.ProgressBarDialog;

public class VideoHistoryCommentsActivity extends HistoryCommentActivity {

    StatisticsDB statisticsDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected String getExportFileName() {
        return "视频历史评论记录";
    }

    @Override
    protected List<? extends HistoryComment> getHistoryCommentList() {
        return getVideoHistoryCommentList();
    }

    private List<VideoHistoryComment> getVideoHistoryCommentList() {
        return getStatisticsDB().queryAllVideoHistoryComments();
    }

    private synchronized StatisticsDB getStatisticsDB() {
        if (statisticsDB == null) {
            statisticsDB = new StatisticsDB(context);
        }
        return statisticsDB;
    }


    @Override
    protected HistoryCommentListAdapter getAdapter() {
        return new VideoHistoryCommentListAdapter(context, getStatisticsDB());
    }

    @Override
    protected void onExport(OutputStream outputStream) {
        ProgressBarDialog progressBarDialog = new ProgressBarDialog.Builder(context)
                .setIndeterminate(true)
                .setTitle("导出中……")
                .setCancelable(false)
                .show();
        new Thread(() -> {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            CSVWriter csvWriter = new CSVWriter(outputStreamWriter);
            csvWriter.writeNext(new String[]{"comment_id", "comment_text", "anchor_comment_id", "anchor_comment_text", "video_id", "state", "send_date", "replied_comment_id", "replied_comment_text"});
            List<VideoHistoryComment> videoHistoryCommentList = getVideoHistoryCommentList();
            for (VideoHistoryComment videoHistoryComment : videoHistoryCommentList) {
                System.out.println(videoHistoryComment);
                String anchorCommentId = null;
                String anchorCommentText = null;
                String replyCommentId = null;
                String replyCommentText = null;
                if (videoHistoryComment.anchorComment != null) {
                    anchorCommentId = videoHistoryComment.anchorComment.commentId;
                    anchorCommentText = videoHistoryComment.anchorComment.commentText;
                }
                csvWriter.writeNext(new String[]{videoHistoryComment.commentId,
                        videoHistoryComment.commentText,
                        anchorCommentId, anchorCommentText,
                        videoHistoryComment.videoId,
                        videoHistoryComment.state,
                        String.valueOf(videoHistoryComment.sendDate.getTime())
                        ,replyCommentId
                        ,replyCommentText});
                System.out.println(videoHistoryComment);
            }
            try {
                csvWriter.close();
            } catch (IOException e){
                showIEResult("导出失败！异常信息："+e.getMessage(),progressBarDialog);
            }
            showIEResult("导出完成！",progressBarDialog);
        }).start();
    }

    @Override
    protected void onImport(InputStream inputStream,HistoryCommentListAdapter adapter) {
        ProgressBarDialog progressBarDialog = new ProgressBarDialog.Builder(context)
                .setTitle("导入中……")
                .setIndeterminate(true)
                .setCancelable(false)
                .show();
        new Thread(() -> {
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
            try {
                String[] csvData = csvReader.readNext();
                int importedCount = 0;
                if (Arrays.equals(csvData,new String[]{"comment_id", "comment_text", "anchor_comment_id", "anchor_comment_text", "video_id", "state", "send_date", "replied_comment_id", "replied_comment_text"})) {
                    while ((csvData = csvReader.readNext()) != null) {
                        Comment anchorComment = null;
                        Comment repliedComment = null;
                        if (csvData[2] != null && csvData[3] != null){
                            anchorComment = new Comment(csvData[2],csvData[3]);
                        }
                        if (csvData[7] != null && csvData[8] != null){
                            repliedComment = new Comment(csvData[7],csvData[8]);
                        }
                        if (statisticsDB.insertVideoHistoryComment(new VideoHistoryComment(csvData[0],csvData[1],csvData[5],new Date(Long.parseLong(csvData[6])),anchorComment,repliedComment,csvData[4])) > 0) {
                            importedCount ++;
                        }
                    }
                    csvReader.close();
                    List<VideoHistoryComment> commentList = getVideoHistoryCommentList();
                    runOnUiThread(() -> adapter.reloadData(commentList));
                    showIEResult(String.format(Locale.getDefault(),"成功导入%d条评论！",importedCount),progressBarDialog);
                } else {
                    showIEResult("CSV表头与要求的不符，导入失败！",progressBarDialog);
                }
            } catch (IOException | CsvValidationException e){
                showIEResult("导入失败！异常信息："+e.getMessage(),progressBarDialog);
            }

        }).start();
    }

    private void showIEResult(String message, DialogInterface dialog){
        runOnUiThread(() -> {
            dialog.dismiss();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        });
    }

}