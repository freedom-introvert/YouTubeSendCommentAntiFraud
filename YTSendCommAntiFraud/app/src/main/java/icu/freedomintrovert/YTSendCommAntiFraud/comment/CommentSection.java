package icu.freedomintrovert.YTSendCommAntiFraud.comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public abstract class CommentSection {
    protected OkHttpClient okHttpClient;
    protected List<Comment> currentCommentList;



    public CommentSection(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public abstract boolean nextPage() throws IOException, VideoCommentSection.Code401Exception;
    public  List<Comment> getCommentsFromThisPage(){
        return currentCommentList;
    };

}
