// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ContinuationCommand.proto

package icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.nestedIntoBase64;

public final class ContinuationCommand {
  private ContinuationCommand() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Continuation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Continuation_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_VideoIdMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_VideoIdMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommentSectionInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommentSectionInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommentSectionInfo_Config_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommentSectionInfo_Config_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\031ContinuationCommand.proto\"v\n\014Continuat" +
      "ion\022\037\n\nvideoIdMsg\030\002 \001(\0132\013.VideoIdMsg\022\024\n\014" +
      "unknownInt_3\030\003 \001(\005\022/\n\022commentSectionInfo" +
      "\030\006 \001(\0132\023.CommentSectionInfo\"\035\n\nVideoIdMs" +
      "g\022\017\n\007videoId\030\002 \001(\t\"\357\001\n\022CommentSectionInf" +
      "o\022*\n\006config\030\004 \001(\0132\032.CommentSectionInfo.C" +
      "onfig\022\032\n\022comments_sectionMy\030\010 \001(\t\032\220\001\n\006Co" +
      "nfig\022\017\n\007videoId\030\004 \001(\t\022\027\n\006sortBy\030\006 \001(\0162\007." +
      "SortBy\022\025\n\runknownInt_15\030\017 \001(\005\022\025\n\runknown" +
      "Int_41\030) \001(\005\022\025\n\runknownInt_44\030, \001(\005\022\027\n\017u" +
      "nknownBytes_45\030- \001(\014*\035\n\006SortBy\022\007\n\003hot\020\000\022" +
      "\n\n\006latest\020\001BZ\nAicu.freedomintrovert.YTSe" +
      "ndCommAntiFraud.grpcApi.nestedIntoBase64" +
      "B\023ContinuationCommandP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Continuation_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Continuation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Continuation_descriptor,
        new String[] { "VideoIdMsg", "UnknownInt3", "CommentSectionInfo", });
    internal_static_VideoIdMsg_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_VideoIdMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_VideoIdMsg_descriptor,
        new String[] { "VideoId", });
    internal_static_CommentSectionInfo_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_CommentSectionInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommentSectionInfo_descriptor,
        new String[] { "Config", "CommentsSectionMy", });
    internal_static_CommentSectionInfo_Config_descriptor =
      internal_static_CommentSectionInfo_descriptor.getNestedTypes().get(0);
    internal_static_CommentSectionInfo_Config_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommentSectionInfo_Config_descriptor,
        new String[] { "VideoId", "SortBy", "UnknownInt15", "UnknownInt41", "UnknownInt44", "UnknownBytes45", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
