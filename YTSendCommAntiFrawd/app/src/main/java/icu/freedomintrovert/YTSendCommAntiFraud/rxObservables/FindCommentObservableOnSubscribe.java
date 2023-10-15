package icu.freedomintrovert.YTSendCommAntiFraud.rxObservables;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import icu.freedomintrovert.YTSendCommAntiFraud.Config;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.Comment;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.HistoryComment;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.ToBeCheckedComment;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.VideoComment;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.VideoCommentSection;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.VideoHistoryComment;
import icu.freedomintrovert.YTSendCommAntiFraud.db.StatisticsDB;
import icu.freedomintrovert.YTSendCommAntiFraud.view.ProgressBarDialog;
import icu.freedomintrovert.YTSendCommAntiFraud.view.ProgressTimer;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class FindCommentObservableOnSubscribe implements ObservableOnSubscribe<FindCommentObservableOnSubscribe.BaseNextValue> {

    private final VideoCommentSection videoCommentSection;
    private VideoComment toBeCheckedComment;
    private final long waitTimeAfterCommentSent;
    private final long intervalOfSearchAgain;
    private final int maximumNumberOfSearchAgain;
    private StatisticsDB statisticsDB;
    private boolean needToWait;
    private final Date sendDate;
    private ProgressTimer progressTimer;
    private boolean needContinue = true;


    public FindCommentObservableOnSubscribe(VideoCommentSection videoCommentSection, VideoComment toBeCheckedComment, StatisticsDB statisticsDB, Config config, boolean needToWait,Date sendDate) {
        this.toBeCheckedComment = toBeCheckedComment;
        this.videoCommentSection = videoCommentSection;
        this.waitTimeAfterCommentSent = config.getWaitTimeAfterCommentSent();
        this.intervalOfSearchAgain = config.getIntervalOfSearchAgain();
        if (!needToWait) {
            this.maximumNumberOfSearchAgain = 0;
        } else {
            this.maximumNumberOfSearchAgain = config.getMaximumNumberOfSearchAgain();
        }
        this.statisticsDB = statisticsDB;
        this.needToWait = needToWait;
        this.sendDate = sendDate;
    }

    @Override
    public void subscribe(@NonNull ObservableEmitter<BaseNextValue> emitter) throws Throwable {
        statisticsDB.insertToBeCheckedComment(new ToBeCheckedComment(toBeCheckedComment.commentId, toBeCheckedComment.commentText, toBeCheckedComment.videoId, ToBeCheckedComment.COMMENT_SECTION_TYPE_VIDEO, null, null, videoCommentSection.getContextPbData(), sendDate));
        if (needToWait) {
            progressTimer = new ProgressTimer(waitTimeAfterCommentSent, ProgressBarDialog.DEFAULT_MAX_PROGRESS, (progress, sleepSeg) -> {
                emitter.onNext(new OnNewSleepProgressValue(progress, progress * sleepSeg));
            });
            progressTimer.start();
            if (!needContinue){
                return;
            }
        }
        emitter.onNext(new OnStartCheckValue());
        Comment anchorComment = null;
        boolean fonndFormHasAccount = false;
        int pn = 1;
        emitter.onNext(new OnNextPageFormHasAccountValue(pn));
        turnPage:
        while (videoCommentSection.nextPage()) {
            List<Comment> comments = videoCommentSection.getCommentsFromThisPage();
            for (int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                if (comment.commentId.equals(toBeCheckedComment.commentId)) {
                    fonndFormHasAccount = true;
                    //如果要找的评论已经是最后一个，则翻页后再获取锚点评论（要找的评论下面的那个评论）
                    if (i + 1 < comments.size()) {
                        anchorComment = comments.get(i + 1);
                    } else {
                        if (videoCommentSection.nextPage()) {
                            anchorComment = videoCommentSection.getCommentsFromThisPage().get(0);
                        }
                    }
                    emitter.onNext(new OnFondYourCommentFromHasAccountVale(comment, anchorComment));
                    try {
                        //避免看不到“找到了”的提示
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {
                    }
                    break turnPage;
                }
            }
            pn++;
            emitter.onNext(new OnNextPageFormHasAccountValue(pn));
        }
        boolean foundAnchorComment = false;
        if (fonndFormHasAccount) {
            pn = 1;
            videoCommentSection.reset(false);
            emitter.onNext(new OnNextPageFormNotAccountValue(pn));
            //无账号评论区查找
            turnPage1:
            while (videoCommentSection.nextPage()) {
                List<Comment> comments = videoCommentSection.getCommentsFromThisPage();
                System.out.println(comments);
                System.out.println(anchorComment);
                for (int i = 0; i < comments.size(); i++) {
                    Comment comment = comments.get(i);
                    if (comment.commentId.equals(toBeCheckedComment.commentId)) {
                        statisticsDB.deleteToBeCheckedComment(toBeCheckedComment.commentId);
                        statisticsDB.insertVideoHistoryComment(new VideoHistoryComment(toBeCheckedComment.commentText, toBeCheckedComment.commentId, HistoryComment.STATE_NORMAL, sendDate, anchorComment, toBeCheckedComment.videoId));
                        emitter.onNext(new OnYourCommentIsNormalStateValue(comment));
                        return;
                    } else if (anchorComment != null && comment.commentId.equals(anchorComment.commentId)) {
                        //如果翻到了之前打锚点的评论，就不用继续往下翻了(节约时间)，因为你的评论在自己能看到的时候在此评论的上面，可以断定是ShadowBan了
                        for (int i1 = 0; i1 < maximumNumberOfSearchAgain; i1++) {
                            int finalI = i1;
                            new ProgressTimer(intervalOfSearchAgain, ProgressBarDialog.DEFAULT_MAX_PROGRESS, (progress, sleepSeg) -> {
                                emitter.onNext(new OnSearchAgainProgressValue(finalI + 1, maximumNumberOfSearchAgain, intervalOfSearchAgain, progress, progress * sleepSeg));
                            }).start();
                            int pageNum = 1;
                            emitter.onNext(new OnSearchAgainGetCommentsValue(pageNum));
                            videoCommentSection.reset(false);
                            turnPage:
                            while (videoCommentSection.nextPage()) {
                                for (Comment comment1 : videoCommentSection.getCommentsFromThisPage()) {
                                    if (comment1.commentId.equals(toBeCheckedComment.commentId)) {
                                        statisticsDB.deleteToBeCheckedComment(toBeCheckedComment.commentId);
                                        statisticsDB.insertVideoHistoryComment(new VideoHistoryComment(toBeCheckedComment.commentText, toBeCheckedComment.commentId, HistoryComment.STATE_NORMAL, sendDate, anchorComment, toBeCheckedComment.videoId));
                                        emitter.onNext(new OnYourCommentIsNormalStateValue(comment1));
                                        return;
                                    } else if (comment1.commentId.equals(anchorComment.commentId)) {
                                        foundAnchorComment = true;
                                        break turnPage;
                                    }
                                }
                                pageNum++;
                                emitter.onNext(new OnSearchAgainGetCommentsValue(pageNum));
                            }
                        }
                        break turnPage1;
                    }
                }
                pn++;
                emitter.onNext(new OnNextPageFormNotAccountValue(pn));
            }
             /*如果走到这里，说明没有锚点或者锚点已被删除又或者锚点是你自己发的评论但是被ShadowBan了，遇到这种情况只能翻遍评论区。
            聪明的你说可以用发送时间定位，呸！你以为这里是哔哩哔哩啊，每个评论都给你详细的发送时间戳。你也不看YouTube后端程序员是谁？
            人家可是谷歌从精神病院里拖出来的！！！看看浏览器里的请求评论返回的JSON数据，每个评论item里都夹了一个emoji数组😅，
            数组里的emoji的数量和你输入法里的emoji列表一样多，整个JSON数据格式化后能到5万行！以答辩文明的QQ、微信程序员看了都他妈要叫声爸爸！！
            踏马但凡差一点的电脑开你YouTube看个评论都要顿一下子🤮，人家小学生当程序员也没他妈你离谱！

            同时，有用的信息也是最少的，评论发送的时间信息真他妈就是个类似“1分钟前”“一小时前””一天前“……的String文本，返回的数据里你根本找不到具体的时间戳
            b站直接给具体的时间戳，然后浏览器生成对应的描述。
            若作为楼中楼评论回复，也没有对应回复评论的parent(父评论ID，此评论回复的对象)数据，root(根评论ID，楼中楼的楼主)倒是有。
            人家b站，对于评论回复，你甚至可以根据它的parent，去生成一个树状的评论回复视图，一目了然(有兴趣可以用mermaid实现)

            再者，JSON数据也写得跟个神经病一样
            以下是某段JSON数据

            "ids": [
                {
                    "commentId": "XXXXXXXXXXXXXXXXXXXXXX"
                },
                {
                    "encryptedVideoId": "XXXXXXXXX"
                },
                {
                    "externalChannelId": "XXXXXXXXXXXXXXXXXXX"
                }
            ],

            妈的，明明可以写成下面这样↓

            "ids":{
                 "commentId":"XXXXXXXXXXXXXXXXXXXXXX",
                 "encryptedVideoId":"XXXXXXXXX",
                 "externalChannelId":"XXXXXXXXXXXXXXXXXXX"
             }

             偏偏要套个他妈的数组！后端程序员你跟数组过不去是吧😅

             */
            if (!foundAnchorComment) {
                for (int i1 = 0; i1 < maximumNumberOfSearchAgain; i1++) {
                    int finalI = i1;
                    new ProgressTimer(intervalOfSearchAgain, ProgressBarDialog.DEFAULT_MAX_PROGRESS, (progress, sleepSeg) -> {
                        emitter.onNext(new OnSearchAgainProgressForNoAnchorValue(finalI + 1, maximumNumberOfSearchAgain, intervalOfSearchAgain, progress, progress * sleepSeg));
                    }).start();
                    int pageNum = 1;
                    emitter.onNext(new OnSearchAgainGetCommentsValue(pageNum));
                    videoCommentSection.reset(false);
                    while (videoCommentSection.nextPage()) {
                        for (Comment comment1 : videoCommentSection.getCommentsFromThisPage()) {
                            if (comment1.commentId.equals(toBeCheckedComment.commentId)) {
                                statisticsDB.deleteToBeCheckedComment(toBeCheckedComment.commentId);
                                statisticsDB.insertVideoHistoryComment(new VideoHistoryComment(toBeCheckedComment.commentText, toBeCheckedComment.commentId, HistoryComment.STATE_NORMAL, sendDate, anchorComment, toBeCheckedComment.videoId));
                                emitter.onNext(new OnYourCommentIsNormalStateValue(comment1));
                                return;
                            }
                        }
                        pageNum++;
                        emitter.onNext(new OnSearchAgainGetCommentsValue(pageNum));
                    }
                }
            }
            checkIfIsDeletedAndReturnShadowBan(emitter, anchorComment,maximumNumberOfSearchAgain > 0);
        } else {
            statisticsDB.deleteToBeCheckedComment(toBeCheckedComment.commentId);
            statisticsDB.insertVideoHistoryComment(new VideoHistoryComment(toBeCheckedComment.commentText, toBeCheckedComment.commentId, HistoryComment.STATE_DELETED, sendDate, toBeCheckedComment.videoId));
            emitter.onNext(new OnYourCommentIsDeleteStateValue());
        }
    }

    //需要在检查评论前的等待时间调用，否则不起作用
    public void stop(){
        if (progressTimer != null) {
            needContinue = false;
            progressTimer.stop();
        }
    }

    private void checkIfIsDeletedAndReturnShadowBan(@NonNull ObservableEmitter<BaseNextValue> emitter, Comment anchorComment,boolean recheck) throws IOException, VideoCommentSection.Code401Exception {
        videoCommentSection.reset(true);
        int pn = 1;
        if (recheck) {
            emitter.onNext(new OnReCheckIfIsDeletedValue(pn));
            while (videoCommentSection.nextPage()) {
                List<Comment> comments = videoCommentSection.getCommentsFromThisPage();
                for (Comment comment : comments) {
                    if (comment.commentId.equals(toBeCheckedComment.commentId)) {
                        statisticsDB.deleteToBeCheckedComment(comment.commentId);
                        statisticsDB.insertVideoHistoryComment(new VideoHistoryComment(toBeCheckedComment.commentText, toBeCheckedComment.commentId, HistoryComment.STATE_SHADOW_BAN, sendDate, anchorComment, toBeCheckedComment.videoId));
                        emitter.onNext(new OnYourCommentIsShadowBanStateValue());
                        return;
                    }
                }
                pn++;
                emitter.onNext(new OnReCheckIfIsDeletedValue(pn));
            }
            statisticsDB.deleteToBeCheckedComment(toBeCheckedComment.commentId);
            statisticsDB.insertVideoHistoryComment(new VideoHistoryComment(toBeCheckedComment.commentText, toBeCheckedComment.commentId, HistoryComment.STATE_DELETED, sendDate, anchorComment, toBeCheckedComment.videoId));
            emitter.onNext(new OnYourCommentIsDeleteStateValue());
        } else {
            statisticsDB.deleteToBeCheckedComment(toBeCheckedComment.commentId);
            statisticsDB.insertVideoHistoryComment(new VideoHistoryComment(toBeCheckedComment.commentText, toBeCheckedComment.commentId, HistoryComment.STATE_SHADOW_BAN, sendDate, anchorComment, toBeCheckedComment.videoId));
            emitter.onNext(new OnYourCommentIsShadowBanStateValue());
        }
    }


    public interface BaseNextValue {
    }

    public static class OnNewSleepProgressValue implements BaseNextValue {
        public int progress;
        public long waitedTime;

        public OnNewSleepProgressValue(int progress, long waitedTime) {
            this.progress = progress;
            this.waitedTime = waitedTime;
        }
    }


    public static class OnStartCheckValue implements BaseNextValue {
    }

    public static class OnNextPageFormHasAccountValue implements BaseNextValue {
        public int pageNumber;

        public OnNextPageFormHasAccountValue(int pageNumber) {
            this.pageNumber = pageNumber;
        }
    }

    public static class OnNextPageFormNotAccountValue implements BaseNextValue {
        public int pageNumber;

        public OnNextPageFormNotAccountValue(int pageNumber) {
            this.pageNumber = pageNumber;
        }
    }


    public static class OnFondYourCommentFromHasAccountVale implements BaseNextValue {
        public Comment yourComment;
        public Comment anchorComment;

        public OnFondYourCommentFromHasAccountVale(Comment yourComment, Comment anchorComment) {
            this.yourComment = yourComment;
            this.anchorComment = anchorComment;
        }
    }

    /**
     * Comment state is deleted
     */
    public static class OnYourCommentIsDeleteStateValue implements BaseNextValue {
    }

    /**
     * Comment state is ShadowBan
     */

    public static class OnSearchAgainProgressValue implements BaseNextValue {
        public int retry;
        public int totalRetry;
        public long interval;
        public int progress;
        public long waitedTime;

        public OnSearchAgainProgressValue(int retry, int totalRetry, long interval, int progress, long waitedTime) {
            this.retry = retry;
            this.totalRetry = totalRetry;
            this.interval = interval;
            this.progress = progress;
            this.waitedTime = waitedTime;
        }
    }

    public static class OnSearchAgainProgressForNoAnchorValue extends OnSearchAgainProgressValue {

        public OnSearchAgainProgressForNoAnchorValue(int retry, int totalRetry, long interval, int progress, long waitedTime) {
            super(retry, totalRetry, interval, progress, waitedTime);
        }
    }

    public static class OnSearchAgainGetCommentsValue implements BaseNextValue {
        public int pageNumber;

        public OnSearchAgainGetCommentsValue(int pageNumber) {
            this.pageNumber = pageNumber;
        }
    }

    public static class OnYourCommentIsShadowBanStateValue implements BaseNextValue {
    }

    /**
     * Comment state is normal
     */
    public static class OnYourCommentIsNormalStateValue implements BaseNextValue {
        public Comment yourComment;

        public OnYourCommentIsNormalStateValue(Comment yourComment) {
            this.yourComment = yourComment;
        }
    }

    public static class OnReCheckIfIsDeletedValue implements BaseNextValue {
        public int pageNumber;

        public OnReCheckIfIsDeletedValue(int pageNumber) {
            this.pageNumber = pageNumber;
        }
    }


}

