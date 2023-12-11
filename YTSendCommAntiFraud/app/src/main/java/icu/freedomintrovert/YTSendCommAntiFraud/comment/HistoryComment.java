package icu.freedomintrovert.YTSendCommAntiFraud.comment;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public abstract class HistoryComment extends Comment{
    public static final String STATE_NORMAL = "NORMAL";
    public static final String STATE_SHADOW_BAN = "SHADOW_BAN";
    public static final String STATE_DELETED = "DELETED";

    public String state;
    public Date sendDate;
    @Nullable
    public Comment anchorComment;
    @Nullable
    public Comment repliedComment;

    public HistoryComment(){}

    public HistoryComment(String commentText, String commentId, String state, Date sendDate, @Nullable Comment anchorComment) {
        this(commentText,commentId,state,sendDate);
        this.anchorComment = anchorComment;
    }

    public HistoryComment(String commentText, String commentId, String state, Date sendDate) {
        super(commentText, commentId);
        this.state = state;
        this.sendDate = sendDate;
    }

    public HistoryComment(String commentText, String commentId, String state, Date sendDate, @Nullable Comment anchorComment, @Nullable Comment repliedComment) {
        super(commentText, commentId);
        this.state = state;
        this.sendDate = sendDate;
        this.anchorComment = anchorComment;
        this.repliedComment = repliedComment;
    }

    public abstract String getCommentSectionSourceId();

    public String getFormatDateFor_yMd(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(sendDate);
    }

    public String getFormatDateFor_yMdHms(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(sendDate);
    }
}
