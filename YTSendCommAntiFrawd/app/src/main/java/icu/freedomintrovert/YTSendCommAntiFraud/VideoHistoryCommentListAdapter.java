package icu.freedomintrovert.YTSendCommAntiFraud;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import icu.freedomintrovert.YTSendCommAntiFraud.comment.HistoryComment;
import icu.freedomintrovert.YTSendCommAntiFraud.db.StatisticsDB;

public class VideoHistoryCommentListAdapter extends HistoryCommentListAdapter{
    StatisticsDB statisticsDB;
    public VideoHistoryCommentListAdapter(Context context,StatisticsDB statisticsDB) {
        super(context);
        this.statisticsDB = statisticsDB;
    }

    @Override
    protected boolean onHistoryCommentDelete(HistoryComment historyComment, ViewHolder viewHolder) {
        return statisticsDB.deleteVideoHistoryComment(historyComment.commentId) > 0;
    }

    @Override
    protected String getSourceIdDesc() {
        return context.getString(R.string.video_id);
    }

    @Override
    protected void openDetailPage(HistoryComment historyComment) {
        Uri uri = Uri.parse("https://www.youtube.com/watch?v="+historyComment.getCommentSectionSourceId()+"&lc="+historyComment.commentId);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }
}
