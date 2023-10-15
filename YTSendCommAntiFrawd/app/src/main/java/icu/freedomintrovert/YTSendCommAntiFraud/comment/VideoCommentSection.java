package icu.freedomintrovert.YTSendCommAntiFraud.comment;

import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.Context;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.ContinuationItem;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.NextInfo8;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.UnkMsg49399797;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.UnkMsg50195462;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.VideoCommentRequest;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.VideoCommentResponse;
import icu.freedomintrovert.YTSendCommAntiFraud.utils.OkHttpUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class VideoCommentSection extends CommentSection {

    private final byte[] contextPbData;
    private boolean hasAccount;
    private final Bundle headersBundle;

    private final String firstContinuationBase64Text;
    private String currentContinuationBase64Text;

    public VideoCommentSection(OkHttpClient okHttpClient, byte[] contextPbData, String continuationBase64Text, Bundle headersBundle, boolean hasAccount) {
        super(okHttpClient);
        this.contextPbData = contextPbData;
        this.firstContinuationBase64Text = continuationBase64Text;
        this.currentContinuationBase64Text = firstContinuationBase64Text;
        this.headersBundle = headersBundle;
        this.hasAccount = hasAccount;
    }

    @Override
    public boolean nextPage() throws IOException, Code401Exception {
        if (currentContinuationBase64Text != null) {
            currentCommentList = new ArrayList<>();
            //VideoCommentRequest.Builder builder = VideoCommentRequest.newBuilder();
            //builder.setContext(Context.parseFrom(contextPbData));
            //builder.setContinuation(continuationBase64Text);
            //VideoCommentRequest videoCommentRequest = builder.build();
            VideoCommentRequest videoCommentRequest1 = VideoCommentRequest.newBuilder().setContext(Context.parseFrom(contextPbData)).setContinuation(currentContinuationBase64Text).build();
            RequestBody requestBody = RequestBody.create(videoCommentRequest1.toByteArray(), MediaType.parse("application/x-protobuf"));
            Request.Builder requestBuilder = new Request.Builder()
                    .url("https://youtubei.googleapis.com/youtubei/v1/next?key=AIzaSyA8eiZmM1FaDVjRy-df2KTyQ_vz_yYM39w")
                    .addHeader("X-GOOG-API-FORMAT-VERSION",headersBundle.getString("X-GOOG-API-FORMAT-VERSION"))
                    .addHeader("X-Goog-Visitor-Id",headersBundle.getString("X-Goog-Visitor-Id"))
                    .addHeader("User-Agent",headersBundle.getString("User-Agent"))
                    .post(requestBody);
            if (hasAccount){
                requestBuilder.addHeader("Authorization", headersBundle.getString("Authorization"));//跟cookie一样的作用
            }
            Request request = requestBuilder.build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 401){
                throw new Code401Exception("Received status code 401, the token may have expired");
            }
            ResponseBody body = response.body();
            OkHttpUtils.checkResponseBodyNotNull(body);

            VideoCommentResponse videoCommentResponse = VideoCommentResponse.parseFrom(body.bytes());
            List<ContinuationItem> continuationItemList = videoCommentResponse.getContinuationItemsAction().getContinuationItems().getContinuationItemList();
            for (ContinuationItem continuationItem : continuationItemList) {
                if (continuationItem.getUnk3().hasComment()) {
                    icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.Comment comment = continuationItem.getUnk3().getComment();
                    currentCommentList.add(new Comment(comment.getContent().getMsg().getMessage(),comment.getContent().getCommentId()));
                }
            }

            NextInfo8 nextInfo8 = videoCommentResponse.getNextInfo8();
            if (nextInfo8.hasUnkMsg50195462()) {
                for (UnkMsg50195462.UnkMsg2 unkMsg2 : nextInfo8.getUnkMsg50195462().getUnkMsg2List()) {
                    if (unkMsg2.hasUnkMsg52047593()) {
                        currentContinuationBase64Text = unkMsg2.getUnkMsg52047593().getContinuation();
                        return true;
                    }
                }
            } else {
                for (UnkMsg49399797.UnkMsg49399797_1 unkMsg49399797_1 : nextInfo8.getUnkMsg49399797().getUnkMsg1List()) {
                    List<UnkMsg50195462.UnkMsg2> unkMsg2List = unkMsg49399797_1.getUnkMsg50195462().getUnkMsg2List();
                    for (UnkMsg50195462.UnkMsg2 unkMsg2 : unkMsg2List) {
                        if (unkMsg2.hasUnkMsg52047593()) {
                            currentContinuationBase64Text = unkMsg2.getUnkMsg52047593().getContinuation();
                            return true;
                        }
                    }
                }
            }
            currentContinuationBase64Text = null;
            return true;
            //stringBuilder.append("\n" + videoCommentResponse);
        }
        currentCommentList = null;
        return false;
    }

    public void reset(boolean hasAccount){
        currentContinuationBase64Text = firstContinuationBase64Text;
        this.hasAccount = hasAccount;
    }

    public String getCurrentContinuationBase64Text(){
        return currentContinuationBase64Text;
    }

    public byte[] getContextPbData() {
        return contextPbData;
    }

    public static class Code401Exception extends Exception{
        public Code401Exception(String message) {
            super(message);
        }
    }
}
