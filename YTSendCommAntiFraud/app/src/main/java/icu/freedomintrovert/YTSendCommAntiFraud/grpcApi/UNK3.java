// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: VideoComments.proto

package icu.freedomintrovert.YTSendCommAntiFraud.grpcApi;

/**
 * Protobuf type {@code UNK3}
 */
public final class UNK3 extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:UNK3)
    UNK3OrBuilder {
private static final long serialVersionUID = 0L;
  // Use UNK3.newBuilder() to construct.
  private UNK3(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UNK3() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new UNK3();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return YTVideoComments.internal_static_UNK3_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return YTVideoComments.internal_static_UNK3_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            UNK3.class, Builder.class);
  }

  public static final int COMMENT_FIELD_NUMBER = 40;
  private Comment comment_;
  /**
   * <code>.Comment comment = 40;</code>
   * @return Whether the comment field is set.
   */
  @Override
  public boolean hasComment() {
    return comment_ != null;
  }
  /**
   * <code>.Comment comment = 40;</code>
   * @return The comment.
   */
  @Override
  public Comment getComment() {
    return comment_ == null ? Comment.getDefaultInstance() : comment_;
  }
  /**
   * <code>.Comment comment = 40;</code>
   */
  @Override
  public CommentOrBuilder getCommentOrBuilder() {
    return comment_ == null ? Comment.getDefaultInstance() : comment_;
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (comment_ != null) {
      output.writeMessage(40, getComment());
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (comment_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(40, getComment());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof UNK3)) {
      return super.equals(obj);
    }
    UNK3 other = (UNK3) obj;

    if (hasComment() != other.hasComment()) return false;
    if (hasComment()) {
      if (!getComment()
          .equals(other.getComment())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasComment()) {
      hash = (37 * hash) + COMMENT_FIELD_NUMBER;
      hash = (53 * hash) + getComment().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static UNK3 parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UNK3 parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UNK3 parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UNK3 parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UNK3 parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UNK3 parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UNK3 parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UNK3 parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static UNK3 parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static UNK3 parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static UNK3 parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UNK3 parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(UNK3 prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code UNK3}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:UNK3)
      UNK3OrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return YTVideoComments.internal_static_UNK3_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return YTVideoComments.internal_static_UNK3_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UNK3.class, Builder.class);
    }

    // Construct using icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.UNK3.newBuilder()
    private Builder() {

    }

    private Builder(
        BuilderParent parent) {
      super(parent);

    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      comment_ = null;
      if (commentBuilder_ != null) {
        commentBuilder_.dispose();
        commentBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return YTVideoComments.internal_static_UNK3_descriptor;
    }

    @Override
    public UNK3 getDefaultInstanceForType() {
      return UNK3.getDefaultInstance();
    }

    @Override
    public UNK3 build() {
      UNK3 result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public UNK3 buildPartial() {
      UNK3 result = new UNK3(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(UNK3 result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.comment_ = commentBuilder_ == null
            ? comment_
            : commentBuilder_.build();
      }
    }

    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof UNK3) {
        return mergeFrom((UNK3)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(UNK3 other) {
      if (other == UNK3.getDefaultInstance()) return this;
      if (other.hasComment()) {
        mergeComment(other.getComment());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 322: {
              input.readMessage(
                  getCommentFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 322
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private Comment comment_;
    private com.google.protobuf.SingleFieldBuilderV3<
        Comment, Comment.Builder, CommentOrBuilder> commentBuilder_;
    /**
     * <code>.Comment comment = 40;</code>
     * @return Whether the comment field is set.
     */
    public boolean hasComment() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.Comment comment = 40;</code>
     * @return The comment.
     */
    public Comment getComment() {
      if (commentBuilder_ == null) {
        return comment_ == null ? Comment.getDefaultInstance() : comment_;
      } else {
        return commentBuilder_.getMessage();
      }
    }
    /**
     * <code>.Comment comment = 40;</code>
     */
    public Builder setComment(Comment value) {
      if (commentBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        comment_ = value;
      } else {
        commentBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Comment comment = 40;</code>
     */
    public Builder setComment(
        Comment.Builder builderForValue) {
      if (commentBuilder_ == null) {
        comment_ = builderForValue.build();
      } else {
        commentBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Comment comment = 40;</code>
     */
    public Builder mergeComment(Comment value) {
      if (commentBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          comment_ != null &&
          comment_ != Comment.getDefaultInstance()) {
          getCommentBuilder().mergeFrom(value);
        } else {
          comment_ = value;
        }
      } else {
        commentBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Comment comment = 40;</code>
     */
    public Builder clearComment() {
      bitField0_ = (bitField0_ & ~0x00000001);
      comment_ = null;
      if (commentBuilder_ != null) {
        commentBuilder_.dispose();
        commentBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.Comment comment = 40;</code>
     */
    public Comment.Builder getCommentBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getCommentFieldBuilder().getBuilder();
    }
    /**
     * <code>.Comment comment = 40;</code>
     */
    public CommentOrBuilder getCommentOrBuilder() {
      if (commentBuilder_ != null) {
        return commentBuilder_.getMessageOrBuilder();
      } else {
        return comment_ == null ?
            Comment.getDefaultInstance() : comment_;
      }
    }
    /**
     * <code>.Comment comment = 40;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        Comment, Comment.Builder, CommentOrBuilder>
        getCommentFieldBuilder() {
      if (commentBuilder_ == null) {
        commentBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            Comment, Comment.Builder, CommentOrBuilder>(
                getComment(),
                getParentForChildren(),
                isClean());
        comment_ = null;
      }
      return commentBuilder_;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:UNK3)
  }

  // @@protoc_insertion_point(class_scope:UNK3)
  private static final UNK3 DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new UNK3();
  }

  public static UNK3 getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UNK3>
      PARSER = new com.google.protobuf.AbstractParser<UNK3>() {
    @Override
    public UNK3 parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<UNK3> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<UNK3> getParserForType() {
    return PARSER;
  }

  @Override
  public UNK3 getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

