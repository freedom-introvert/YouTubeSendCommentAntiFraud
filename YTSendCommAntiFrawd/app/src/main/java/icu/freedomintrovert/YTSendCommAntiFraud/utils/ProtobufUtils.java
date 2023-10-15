package icu.freedomintrovert.YTSendCommAntiFraud.utils;

import com.google.protobuf.ByteString;

import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.nestedIntoBase64.CommentSectionInfo;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.nestedIntoBase64.Continuation;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.nestedIntoBase64.SortBy;
import icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.nestedIntoBase64.VideoIdMsg;

public class ProtobufUtils {
    public static Continuation generateContinuation(String videoId, SortBy sortBy) {
        Continuation.Builder continuationBuilder = Continuation.newBuilder()
                .setVideoIdMsg(VideoIdMsg.newBuilder().setVideoId(videoId).build())
                .setUnknownInt3(6);
        CommentSectionInfo.Config config = CommentSectionInfo.Config.newBuilder()
                .setVideoId(videoId)
                .setSortBy(sortBy)
                .setUnknownInt15(2)
                .setUnknownInt41(1)
                .setUnknownInt44(1)
                .setUnknownBytes45(ByteString.copyFrom(new byte[]{1,6,8,5}))
                .build();
        continuationBuilder.setCommentSectionInfo(CommentSectionInfo.newBuilder().setConfig(config).setCommentsSectionMy("comments-section").build());
        return continuationBuilder.build();
    }

    public static String escapeBase64 (String base64){
        return base64.replace("+","-").replace("/","_").replace("=","%3D");
    }
}
