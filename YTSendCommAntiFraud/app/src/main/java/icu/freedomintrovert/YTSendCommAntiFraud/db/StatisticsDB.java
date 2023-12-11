package icu.freedomintrovert.YTSendCommAntiFraud.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import icu.freedomintrovert.YTSendCommAntiFraud.comment.ToBeCheckedComment;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.VideoHistoryComment;

public class StatisticsDB extends SQLiteOpenHelper {
    public static final String TABLE_NAME_VIDEO_HISTORY_COMMENTS = "history_comments_from_video";
    private static final String TABLE_NAME_TO_BE_CHECKED_COMMENTS = "to_be_checked_comments";
    SQLiteDatabase db;

    public static final int DB_VERSION = 1;

    public StatisticsDB(@Nullable Context context) {
        super(context, "statistics", null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE history_comments_from_video ( comment_id TEXT PRIMARY KEY UNIQUE NOT NULL, comment_text TEXT NOT NULL,anchor_comment_id TEXT,anchor_comment_text TEXT, video_id TEXT NOT NULL, state TEXT NOT NULL,send_date INTEGER NOT NULL,replied_comment_id TEXT,replied_comment_text TEXT ); ");
        db.execSQL("CREATE TABLE to_be_checked_comments ( comment_id TEXT PRIMARY KEY NOT NULL UNIQUE, comment_text TEXT NOT NULL, source_id TEXT NOT NULL, comment_section_type INTEGER NOT NULL, replied_comment_id TEXT, replied_comment_text TEXT, context1 BLOB NOT NULL ,send_time INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertVideoHistoryComment(VideoHistoryComment comment){
        ContentValues v = new ContentValues();
        v.put("comment_id", comment.commentId);
        v.put("comment_text", comment.commentText);
        if (comment.anchorComment != null) {
            v.put("anchor_comment_id", comment.anchorComment.commentId);
            v.put("anchor_comment_text", comment.anchorComment.commentText);
        }
        v.put("video_id", comment.videoId);
        v.put("state", comment.state);
        v.put("send_date", comment.sendDate.getTime());
        if (comment.repliedComment != null) {
            v.put("replied_comment_id", comment.repliedComment.commentId);
            v.put("replied_comment_text", comment.repliedComment.commentText);
        }
        return db.insert(TABLE_NAME_VIDEO_HISTORY_COMMENTS,null,v);
    }

    @SuppressLint("Range")
    public List<VideoHistoryComment> queryAllVideoHistoryComments(){
        List<VideoHistoryComment> commentList = new ArrayList<>();
        String[] columns = {"comment_id", "comment_text", "anchor_comment_id", "anchor_comment_text", "video_id", "state", "send_date", "replied_comment_id", "replied_comment_text"};
        Cursor cursor = db.query("history_comments_from_video", columns, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String commentId = cursor.getString(cursor.getColumnIndex("comment_id"));
                String commentText = cursor.getString(cursor.getColumnIndex("comment_text"));
                String anchorCommentId = cursor.getString(cursor.getColumnIndex("anchor_comment_id"));
                String anchorCommentText = cursor.getString(cursor.getColumnIndex("anchor_comment_text"));
                String videoId = cursor.getString(cursor.getColumnIndex("video_id"));
                String state = cursor.getString(cursor.getColumnIndex("state"));
                long sendDate = cursor.getLong(cursor.getColumnIndex("send_date"));
                String repliedCommentId = cursor.getString(cursor.getColumnIndex("replied_comment_id"));
                String repliedCommentText = cursor.getString(cursor.getColumnIndex("replied_comment_text"));
                VideoHistoryComment comment = new VideoHistoryComment(commentId, commentText, anchorCommentId, anchorCommentText, videoId, state, sendDate, repliedCommentId, repliedCommentText);
                commentList.add(comment);
            }
            cursor.close();
        }
        return commentList;
    }

    public long deleteVideoHistoryComment(String commentId) {
        return db.delete(TABLE_NAME_VIDEO_HISTORY_COMMENTS,"comment_id = ?",new String[]{commentId});
    }

    public void insertToBeCheckedComment(ToBeCheckedComment comment) {
        ContentValues v = new ContentValues();
        v.put("comment_id", comment.commentId);
        v.put("comment_text", comment.commentText);
        v.put("source_id", comment.sourceId);
        v.put("comment_section_type", comment.commentSectionType);
        v.put("replied_comment_id", comment.repliedCommentId);
        v.put("replied_comment_text", comment.repliedCommentText);
        v.put("context1", comment.context1);
        v.put("send_time",comment.sendTime.getTime());
        db.insert(TABLE_NAME_TO_BE_CHECKED_COMMENTS, null, v);
    }
    @SuppressLint("Range")
    public List<ToBeCheckedComment> getAllToBeCheckedComments() {
        List<ToBeCheckedComment> commentList = new ArrayList<>();
        String[] columns = {"comment_id", "comment_text", "source_id", "comment_section_type", "replied_comment_id", "replied_comment_text", "context1","send_time"};
        Cursor cursor = db.query("to_be_checked_comments", columns, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String commentId = cursor.getString(cursor.getColumnIndex("comment_id"));
                String commentText = cursor.getString(cursor.getColumnIndex("comment_text"));
                String sourceId = cursor.getString(cursor.getColumnIndex("source_id"));
                int commentSectionType = cursor.getInt(cursor.getColumnIndex("comment_section_type"));
                String repliedCommentId = cursor.getString(cursor.getColumnIndex("replied_comment_id"));
                String repliedCommentText = cursor.getString(cursor.getColumnIndex("replied_comment_text"));
                byte[] context1 = cursor.getBlob(cursor.getColumnIndex("context1"));
                long sendTime = cursor.getLong(cursor.getColumnIndex("send_time"));
                ToBeCheckedComment comment = new ToBeCheckedComment(commentId, commentText, sourceId, commentSectionType, repliedCommentId, repliedCommentText, context1,new Date(sendTime));
                commentList.add(comment);
            }
            cursor.close();
        }
        return commentList;
    }

    public long deleteToBeCheckedComment(String commentId){
        return db.delete(TABLE_NAME_TO_BE_CHECKED_COMMENTS,"comment_id = ?",new String[]{commentId});
    }

}
