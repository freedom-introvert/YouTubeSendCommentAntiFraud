// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: VideoComments.proto

package icu.freedomintrovert.YTSendCommAntiFraud.grpcApi;

public interface VideoCommentRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:VideoCommentRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.Context context = 1;</code>
   * @return Whether the context field is set.
   */
  boolean hasContext();
  /**
   * <code>.Context context = 1;</code>
   * @return The context.
   */
  Context getContext();
  /**
   * <code>.Context context = 1;</code>
   */
  ContextOrBuilder getContextOrBuilder();

  /**
   * <code>string continuation = 8;</code>
   * @return The continuation.
   */
  String getContinuation();
  /**
   * <code>string continuation = 8;</code>
   * @return The bytes for continuation.
   */
  com.google.protobuf.ByteString
      getContinuationBytes();
}
