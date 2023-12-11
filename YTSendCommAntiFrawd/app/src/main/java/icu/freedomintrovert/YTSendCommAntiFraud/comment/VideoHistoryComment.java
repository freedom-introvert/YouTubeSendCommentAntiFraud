package icu.freedomintrovert.YTSendCommAntiFraud.comment;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;


public class VideoHistoryComment extends HistoryComment{

    public String videoId;

    public VideoHistoryComment(String commentId, String commentText, String anchorCommentId, String anchorCommentText, String videoId, String state, long sendDate, String repliedCommentId, String repliedCommentText) {
        this.commentId = commentId;
        this.commentText = commentText;
        if (!TextUtils.isEmpty(anchorCommentId) && anchorCommentText != null) {
            this.anchorComment = new Comment(anchorCommentText,anchorCommentId);
        }
        this.videoId = videoId;
        this.state = state;
        this.sendDate = new Date(sendDate);
        if (!TextUtils.isEmpty(repliedCommentId) && repliedCommentText != null){
            this.repliedComment = new Comment(repliedCommentText,repliedCommentId);
        }
    }

    public VideoHistoryComment(String commentText, String commentId, String state, Date sendDate, String videoId) {
        super(commentText, commentId, state, sendDate);
        this.videoId = videoId;
    }

    public VideoHistoryComment(String commentText, String commentId, String state, Date sendDate, @Nullable Comment anchorComment, String videoId) {
        super(commentText, commentId, state, sendDate, anchorComment);
        this.videoId = videoId;
    }

    public VideoHistoryComment(String commentId,String commentText, String state, Date sendDate, @Nullable Comment anchorComment, @Nullable Comment repliedComment, String videoId) {
        super(commentText, commentId, state, sendDate, anchorComment, repliedComment);
        this.videoId = videoId;
    }

    @Override
    public String getCommentSectionSourceId() {
        return videoId;
    }

    @NonNull
    @Override
    public String toString() {
        return "VideoHistoryComment{" +
                "videoId='" + videoId + '\'' +
                ", state='" + state + '\'' +
                ", sendDate=" + sendDate +
                ", anchorComment=" + anchorComment +
                ", commentText='" + commentText + '\'' +
                ", commentId='" + commentId + '\'' +
                '}';
    }
}
