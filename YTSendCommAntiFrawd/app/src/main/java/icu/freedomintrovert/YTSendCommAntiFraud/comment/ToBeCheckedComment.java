package icu.freedomintrovert.YTSendCommAntiFraud.comment;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ToBeCheckedComment extends Comment{
    public static final int COMMENT_SECTION_TYPE_VIDEO = 1;

    public String sourceId;
    public int commentSectionType;
    public String repliedCommentId;
    public String repliedCommentText;
    public byte[] context1;
    public Date sendTime;

    public ToBeCheckedComment(String commentId, String commentText, String sourceId, int commentSectionType, String repliedCommentId, String repliedCommentText, byte[] context1,Date sendTime) {
        this.commentId = commentId;
        this.commentText = commentText;
        this.sourceId = sourceId;
        this.commentSectionType = commentSectionType;
        this.repliedCommentId = repliedCommentId;
        this.repliedCommentText = repliedCommentText;
        this.context1 = context1;
        this.sendTime = sendTime;
    }

    public String getFormatDateFor_yMdHms(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(sendTime);
    }



}
