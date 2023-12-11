// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: VideoComments.proto

package icu.freedomintrovert.YTSendCommAntiFraud.grpcApi;

/**
 * Protobuf type {@code Comment}
 */
public final class Comment extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Comment)
    CommentOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Comment.newBuilder() to construct.
  private Comment(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Comment() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new Comment();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return YTVideoComments.internal_static_Comment_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return YTVideoComments.internal_static_Comment_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            Comment.class, Builder.class);
  }

  public static final int CONTENT_FIELD_NUMBER = 2;
  private Content content_;
  /**
   * <code>.Content content = 2;</code>
   * @return Whether the content field is set.
   */
  @Override
  public boolean hasContent() {
    return content_ != null;
  }
  /**
   * <code>.Content content = 2;</code>
   * @return The content.
   */
  @Override
  public Content getContent() {
    return content_ == null ? Content.getDefaultInstance() : content_;
  }
  /**
   * <code>.Content content = 2;</code>
   */
  @Override
  public ContentOrBuilder getContentOrBuilder() {
    return content_ == null ? Content.getDefaultInstance() : content_;
  }

  public static final int USER_FIELD_NUMBER = 3;
  private User user_;
  /**
   * <code>.User user = 3;</code>
   * @return Whether the user field is set.
   */
  @Override
  public boolean hasUser() {
    return user_ != null;
  }
  /**
   * <code>.User user = 3;</code>
   * @return The user.
   */
  @Override
  public User getUser() {
    return user_ == null ? User.getDefaultInstance() : user_;
  }
  /**
   * <code>.User user = 3;</code>
   */
  @Override
  public UserOrBuilder getUserOrBuilder() {
    return user_ == null ? User.getDefaultInstance() : user_;
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
    if (content_ != null) {
      output.writeMessage(2, getContent());
    }
    if (user_ != null) {
      output.writeMessage(3, getUser());
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (content_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getContent());
    }
    if (user_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getUser());
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
    if (!(obj instanceof Comment)) {
      return super.equals(obj);
    }
    Comment other = (Comment) obj;

    if (hasContent() != other.hasContent()) return false;
    if (hasContent()) {
      if (!getContent()
          .equals(other.getContent())) return false;
    }
    if (hasUser() != other.hasUser()) return false;
    if (hasUser()) {
      if (!getUser()
          .equals(other.getUser())) return false;
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
    if (hasContent()) {
      hash = (37 * hash) + CONTENT_FIELD_NUMBER;
      hash = (53 * hash) + getContent().hashCode();
    }
    if (hasUser()) {
      hash = (37 * hash) + USER_FIELD_NUMBER;
      hash = (53 * hash) + getUser().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static Comment parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Comment parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Comment parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Comment parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Comment parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Comment parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Comment parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Comment parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static Comment parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static Comment parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static Comment parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Comment parseFrom(
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
  public static Builder newBuilder(Comment prototype) {
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
   * Protobuf type {@code Comment}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Comment)
      CommentOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return YTVideoComments.internal_static_Comment_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return YTVideoComments.internal_static_Comment_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Comment.class, Builder.class);
    }

    // Construct using icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.Comment.newBuilder()
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
      content_ = null;
      if (contentBuilder_ != null) {
        contentBuilder_.dispose();
        contentBuilder_ = null;
      }
      user_ = null;
      if (userBuilder_ != null) {
        userBuilder_.dispose();
        userBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return YTVideoComments.internal_static_Comment_descriptor;
    }

    @Override
    public Comment getDefaultInstanceForType() {
      return Comment.getDefaultInstance();
    }

    @Override
    public Comment build() {
      Comment result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public Comment buildPartial() {
      Comment result = new Comment(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(Comment result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.content_ = contentBuilder_ == null
            ? content_
            : contentBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.user_ = userBuilder_ == null
            ? user_
            : userBuilder_.build();
      }
    }

    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof Comment) {
        return mergeFrom((Comment)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(Comment other) {
      if (other == Comment.getDefaultInstance()) return this;
      if (other.hasContent()) {
        mergeContent(other.getContent());
      }
      if (other.hasUser()) {
        mergeUser(other.getUser());
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
            case 18: {
              input.readMessage(
                  getContentFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 18
            case 26: {
              input.readMessage(
                  getUserFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000002;
              break;
            } // case 26
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

    private Content content_;
    private com.google.protobuf.SingleFieldBuilderV3<
        Content, Content.Builder, ContentOrBuilder> contentBuilder_;
    /**
     * <code>.Content content = 2;</code>
     * @return Whether the content field is set.
     */
    public boolean hasContent() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.Content content = 2;</code>
     * @return The content.
     */
    public Content getContent() {
      if (contentBuilder_ == null) {
        return content_ == null ? Content.getDefaultInstance() : content_;
      } else {
        return contentBuilder_.getMessage();
      }
    }
    /**
     * <code>.Content content = 2;</code>
     */
    public Builder setContent(Content value) {
      if (contentBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        content_ = value;
      } else {
        contentBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Content content = 2;</code>
     */
    public Builder setContent(
        Content.Builder builderForValue) {
      if (contentBuilder_ == null) {
        content_ = builderForValue.build();
      } else {
        contentBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Content content = 2;</code>
     */
    public Builder mergeContent(Content value) {
      if (contentBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          content_ != null &&
          content_ != Content.getDefaultInstance()) {
          getContentBuilder().mergeFrom(value);
        } else {
          content_ = value;
        }
      } else {
        contentBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Content content = 2;</code>
     */
    public Builder clearContent() {
      bitField0_ = (bitField0_ & ~0x00000001);
      content_ = null;
      if (contentBuilder_ != null) {
        contentBuilder_.dispose();
        contentBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.Content content = 2;</code>
     */
    public Content.Builder getContentBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getContentFieldBuilder().getBuilder();
    }
    /**
     * <code>.Content content = 2;</code>
     */
    public ContentOrBuilder getContentOrBuilder() {
      if (contentBuilder_ != null) {
        return contentBuilder_.getMessageOrBuilder();
      } else {
        return content_ == null ?
            Content.getDefaultInstance() : content_;
      }
    }
    /**
     * <code>.Content content = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        Content, Content.Builder, ContentOrBuilder>
        getContentFieldBuilder() {
      if (contentBuilder_ == null) {
        contentBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            Content, Content.Builder, ContentOrBuilder>(
                getContent(),
                getParentForChildren(),
                isClean());
        content_ = null;
      }
      return contentBuilder_;
    }

    private User user_;
    private com.google.protobuf.SingleFieldBuilderV3<
        User, User.Builder, UserOrBuilder> userBuilder_;
    /**
     * <code>.User user = 3;</code>
     * @return Whether the user field is set.
     */
    public boolean hasUser() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>.User user = 3;</code>
     * @return The user.
     */
    public User getUser() {
      if (userBuilder_ == null) {
        return user_ == null ? User.getDefaultInstance() : user_;
      } else {
        return userBuilder_.getMessage();
      }
    }
    /**
     * <code>.User user = 3;</code>
     */
    public Builder setUser(User value) {
      if (userBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        user_ = value;
      } else {
        userBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.User user = 3;</code>
     */
    public Builder setUser(
        User.Builder builderForValue) {
      if (userBuilder_ == null) {
        user_ = builderForValue.build();
      } else {
        userBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.User user = 3;</code>
     */
    public Builder mergeUser(User value) {
      if (userBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
          user_ != null &&
          user_ != User.getDefaultInstance()) {
          getUserBuilder().mergeFrom(value);
        } else {
          user_ = value;
        }
      } else {
        userBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.User user = 3;</code>
     */
    public Builder clearUser() {
      bitField0_ = (bitField0_ & ~0x00000002);
      user_ = null;
      if (userBuilder_ != null) {
        userBuilder_.dispose();
        userBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.User user = 3;</code>
     */
    public User.Builder getUserBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getUserFieldBuilder().getBuilder();
    }
    /**
     * <code>.User user = 3;</code>
     */
    public UserOrBuilder getUserOrBuilder() {
      if (userBuilder_ != null) {
        return userBuilder_.getMessageOrBuilder();
      } else {
        return user_ == null ?
            User.getDefaultInstance() : user_;
      }
    }
    /**
     * <code>.User user = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        User, User.Builder, UserOrBuilder>
        getUserFieldBuilder() {
      if (userBuilder_ == null) {
        userBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            User, User.Builder, UserOrBuilder>(
                getUser(),
                getParentForChildren(),
                isClean());
        user_ = null;
      }
      return userBuilder_;
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


    // @@protoc_insertion_point(builder_scope:Comment)
  }

  // @@protoc_insertion_point(class_scope:Comment)
  private static final Comment DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new Comment();
  }

  public static Comment getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Comment>
      PARSER = new com.google.protobuf.AbstractParser<Comment>() {
    @Override
    public Comment parsePartialFrom(
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

  public static com.google.protobuf.Parser<Comment> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<Comment> getParserForType() {
    return PARSER;
  }

  @Override
  public Comment getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
