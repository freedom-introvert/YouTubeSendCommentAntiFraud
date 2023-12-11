// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: VideoComments.proto

package icu.freedomintrovert.YTSendCommAntiFraud.grpcApi;

/**
 * Protobuf type {@code Context}
 */
public final class Context extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Context)
    ContextOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Context.newBuilder() to construct.
  private Context(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Context() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new Context();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return YTVideoComments.internal_static_Context_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return YTVideoComments.internal_static_Context_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            Context.class, Builder.class);
  }

  public static final int CLIENT_FIELD_NUMBER = 1;
  private Client client_;
  /**
   * <code>.Client client = 1;</code>
   * @return Whether the client field is set.
   */
  @Override
  public boolean hasClient() {
    return client_ != null;
  }
  /**
   * <code>.Client client = 1;</code>
   * @return The client.
   */
  @Override
  public Client getClient() {
    return client_ == null ? Client.getDefaultInstance() : client_;
  }
  /**
   * <code>.Client client = 1;</code>
   */
  @Override
  public ClientOrBuilder getClientOrBuilder() {
    return client_ == null ? Client.getDefaultInstance() : client_;
  }

  public static final int MSGU1_6_FIELD_NUMBER = 6;
  private MsgU1_6 msgu16_;
  /**
   * <code>.MsgU1_6 msgu1_6 = 6;</code>
   * @return Whether the msgu16 field is set.
   */
  @Override
  public boolean hasMsgu16() {
    return msgu16_ != null;
  }
  /**
   * <code>.MsgU1_6 msgu1_6 = 6;</code>
   * @return The msgu16.
   */
  @Override
  public MsgU1_6 getMsgu16() {
    return msgu16_ == null ? MsgU1_6.getDefaultInstance() : msgu16_;
  }
  /**
   * <code>.MsgU1_6 msgu1_6 = 6;</code>
   */
  @Override
  public MsgU1_6OrBuilder getMsgu16OrBuilder() {
    return msgu16_ == null ? MsgU1_6.getDefaultInstance() : msgu16_;
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
    if (client_ != null) {
      output.writeMessage(1, getClient());
    }
    if (msgu16_ != null) {
      output.writeMessage(6, getMsgu16());
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (client_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getClient());
    }
    if (msgu16_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(6, getMsgu16());
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
    if (!(obj instanceof Context)) {
      return super.equals(obj);
    }
    Context other = (Context) obj;

    if (hasClient() != other.hasClient()) return false;
    if (hasClient()) {
      if (!getClient()
          .equals(other.getClient())) return false;
    }
    if (hasMsgu16() != other.hasMsgu16()) return false;
    if (hasMsgu16()) {
      if (!getMsgu16()
          .equals(other.getMsgu16())) return false;
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
    if (hasClient()) {
      hash = (37 * hash) + CLIENT_FIELD_NUMBER;
      hash = (53 * hash) + getClient().hashCode();
    }
    if (hasMsgu16()) {
      hash = (37 * hash) + MSGU1_6_FIELD_NUMBER;
      hash = (53 * hash) + getMsgu16().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static Context parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Context parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Context parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Context parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Context parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Context parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Context parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Context parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static Context parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static Context parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static Context parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Context parseFrom(
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
  public static Builder newBuilder(Context prototype) {
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
   * Protobuf type {@code Context}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Context)
      ContextOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return YTVideoComments.internal_static_Context_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return YTVideoComments.internal_static_Context_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Context.class, Builder.class);
    }

    // Construct using icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.Context.newBuilder()
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
      client_ = null;
      if (clientBuilder_ != null) {
        clientBuilder_.dispose();
        clientBuilder_ = null;
      }
      msgu16_ = null;
      if (msgu16Builder_ != null) {
        msgu16Builder_.dispose();
        msgu16Builder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return YTVideoComments.internal_static_Context_descriptor;
    }

    @Override
    public Context getDefaultInstanceForType() {
      return Context.getDefaultInstance();
    }

    @Override
    public Context build() {
      Context result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public Context buildPartial() {
      Context result = new Context(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(Context result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.client_ = clientBuilder_ == null
            ? client_
            : clientBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.msgu16_ = msgu16Builder_ == null
            ? msgu16_
            : msgu16Builder_.build();
      }
    }

    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof Context) {
        return mergeFrom((Context)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(Context other) {
      if (other == Context.getDefaultInstance()) return this;
      if (other.hasClient()) {
        mergeClient(other.getClient());
      }
      if (other.hasMsgu16()) {
        mergeMsgu16(other.getMsgu16());
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
            case 10: {
              input.readMessage(
                  getClientFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 50: {
              input.readMessage(
                  getMsgu16FieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000002;
              break;
            } // case 50
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

    private Client client_;
    private com.google.protobuf.SingleFieldBuilderV3<
        Client, Client.Builder, ClientOrBuilder> clientBuilder_;
    /**
     * <code>.Client client = 1;</code>
     * @return Whether the client field is set.
     */
    public boolean hasClient() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.Client client = 1;</code>
     * @return The client.
     */
    public Client getClient() {
      if (clientBuilder_ == null) {
        return client_ == null ? Client.getDefaultInstance() : client_;
      } else {
        return clientBuilder_.getMessage();
      }
    }
    /**
     * <code>.Client client = 1;</code>
     */
    public Builder setClient(Client value) {
      if (clientBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        client_ = value;
      } else {
        clientBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Client client = 1;</code>
     */
    public Builder setClient(
        Client.Builder builderForValue) {
      if (clientBuilder_ == null) {
        client_ = builderForValue.build();
      } else {
        clientBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Client client = 1;</code>
     */
    public Builder mergeClient(Client value) {
      if (clientBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          client_ != null &&
          client_ != Client.getDefaultInstance()) {
          getClientBuilder().mergeFrom(value);
        } else {
          client_ = value;
        }
      } else {
        clientBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Client client = 1;</code>
     */
    public Builder clearClient() {
      bitField0_ = (bitField0_ & ~0x00000001);
      client_ = null;
      if (clientBuilder_ != null) {
        clientBuilder_.dispose();
        clientBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.Client client = 1;</code>
     */
    public Client.Builder getClientBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getClientFieldBuilder().getBuilder();
    }
    /**
     * <code>.Client client = 1;</code>
     */
    public ClientOrBuilder getClientOrBuilder() {
      if (clientBuilder_ != null) {
        return clientBuilder_.getMessageOrBuilder();
      } else {
        return client_ == null ?
            Client.getDefaultInstance() : client_;
      }
    }
    /**
     * <code>.Client client = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        Client, Client.Builder, ClientOrBuilder>
        getClientFieldBuilder() {
      if (clientBuilder_ == null) {
        clientBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            Client, Client.Builder, ClientOrBuilder>(
                getClient(),
                getParentForChildren(),
                isClean());
        client_ = null;
      }
      return clientBuilder_;
    }

    private MsgU1_6 msgu16_;
    private com.google.protobuf.SingleFieldBuilderV3<
        MsgU1_6, MsgU1_6.Builder, MsgU1_6OrBuilder> msgu16Builder_;
    /**
     * <code>.MsgU1_6 msgu1_6 = 6;</code>
     * @return Whether the msgu16 field is set.
     */
    public boolean hasMsgu16() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>.MsgU1_6 msgu1_6 = 6;</code>
     * @return The msgu16.
     */
    public MsgU1_6 getMsgu16() {
      if (msgu16Builder_ == null) {
        return msgu16_ == null ? MsgU1_6.getDefaultInstance() : msgu16_;
      } else {
        return msgu16Builder_.getMessage();
      }
    }
    /**
     * <code>.MsgU1_6 msgu1_6 = 6;</code>
     */
    public Builder setMsgu16(MsgU1_6 value) {
      if (msgu16Builder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        msgu16_ = value;
      } else {
        msgu16Builder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.MsgU1_6 msgu1_6 = 6;</code>
     */
    public Builder setMsgu16(
        MsgU1_6.Builder builderForValue) {
      if (msgu16Builder_ == null) {
        msgu16_ = builderForValue.build();
      } else {
        msgu16Builder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.MsgU1_6 msgu1_6 = 6;</code>
     */
    public Builder mergeMsgu16(MsgU1_6 value) {
      if (msgu16Builder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
          msgu16_ != null &&
          msgu16_ != MsgU1_6.getDefaultInstance()) {
          getMsgu16Builder().mergeFrom(value);
        } else {
          msgu16_ = value;
        }
      } else {
        msgu16Builder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.MsgU1_6 msgu1_6 = 6;</code>
     */
    public Builder clearMsgu16() {
      bitField0_ = (bitField0_ & ~0x00000002);
      msgu16_ = null;
      if (msgu16Builder_ != null) {
        msgu16Builder_.dispose();
        msgu16Builder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.MsgU1_6 msgu1_6 = 6;</code>
     */
    public MsgU1_6.Builder getMsgu16Builder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getMsgu16FieldBuilder().getBuilder();
    }
    /**
     * <code>.MsgU1_6 msgu1_6 = 6;</code>
     */
    public MsgU1_6OrBuilder getMsgu16OrBuilder() {
      if (msgu16Builder_ != null) {
        return msgu16Builder_.getMessageOrBuilder();
      } else {
        return msgu16_ == null ?
            MsgU1_6.getDefaultInstance() : msgu16_;
      }
    }
    /**
     * <code>.MsgU1_6 msgu1_6 = 6;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        MsgU1_6, MsgU1_6.Builder, MsgU1_6OrBuilder>
        getMsgu16FieldBuilder() {
      if (msgu16Builder_ == null) {
        msgu16Builder_ = new com.google.protobuf.SingleFieldBuilderV3<
            MsgU1_6, MsgU1_6.Builder, MsgU1_6OrBuilder>(
                getMsgu16(),
                getParentForChildren(),
                isClean());
        msgu16_ = null;
      }
      return msgu16Builder_;
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


    // @@protoc_insertion_point(builder_scope:Context)
  }

  // @@protoc_insertion_point(class_scope:Context)
  private static final Context DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new Context();
  }

  public static Context getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Context>
      PARSER = new com.google.protobuf.AbstractParser<Context>() {
    @Override
    public Context parsePartialFrom(
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

  public static com.google.protobuf.Parser<Context> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<Context> getParserForType() {
    return PARSER;
  }

  @Override
  public Context getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
