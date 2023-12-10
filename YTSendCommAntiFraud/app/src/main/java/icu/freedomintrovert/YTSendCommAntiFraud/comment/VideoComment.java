package icu.freedomintrovert.YTSendCommAntiFraud.comment;

import android.os.Bundle;

public class VideoComment extends Comment{
    public String videoId;

    public VideoComment(String commentText, String commentId, String videoId) {
        super(commentText, commentId);
        this.videoId = videoId;
    }
}
