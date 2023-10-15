// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: VideoComments.proto

package icu.freedomintrovert.YTSendCommAntiFraud.grpcApi;

/**
 * Protobuf type {@code ContinuationItem}
 */
public final class ContinuationItem extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ContinuationItem)
    ContinuationItemOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ContinuationItem.newBuilder() to construct.
  private ContinuationItem(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ContinuationItem() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new ContinuationItem();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return YTVideoComments.internal_static_ContinuationItem_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return YTVideoComments.internal_static_ContinuationItem_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ContinuationItem.class, Builder.class);
  }

  public static final int UNK3_FIELD_NUMBER = 3;
  private UNK3 unk3_;
  /**
   * <code>.UNK3 unk3 = 3;</code>
   * @return Whether the unk3 field is set.
   */
  @Override
  public boolean hasUnk3() {
    return unk3_ != null;
  }
  /**
   * <code>.UNK3 unk3 = 3;</code>
   * @return The unk3.
   */
  @Override
  public UNK3 getUnk3() {
    return unk3_ == null ? UNK3.getDefaultInstance() : unk3_;
  }
  /**
   * <code>.UNK3 unk3 = 3;</code>
   */
  @Override
  public UNK3OrBuilder getUnk3OrBuilder() {
    return unk3_ == null ? UNK3.getDefaultInstance() : unk3_;
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
    if (unk3_ != null) {
      output.writeMessage(3, getUnk3());
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (unk3_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getUnk3());
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
    if (!(obj instanceof ContinuationItem)) {
      return super.equals(obj);
    }
    ContinuationItem other = (ContinuationItem) obj;

    if (hasUnk3() != other.hasUnk3()) return false;
    if (hasUnk3()) {
      if (!getUnk3()
          .equals(other.getUnk3())) return false;
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
    if (hasUnk3()) {
      hash = (37 * hash) + UNK3_FIELD_NUMBER;
      hash = (53 * hash) + getUnk3().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ContinuationItem parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ContinuationItem parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ContinuationItem parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ContinuationItem parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ContinuationItem parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ContinuationItem parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ContinuationItem parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ContinuationItem parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ContinuationItem parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ContinuationItem parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ContinuationItem parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ContinuationItem parseFrom(
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
  public static Builder newBuilder(ContinuationItem prototype) {
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
   * Protobuf type {@code ContinuationItem}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ContinuationItem)
      ContinuationItemOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return YTVideoComments.internal_static_ContinuationItem_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return YTVideoComments.internal_static_ContinuationItem_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ContinuationItem.class, Builder.class);
    }

    // Construct using icu.freedomintrovert.YTSendCommAntiFraud.grpcApi.ContinuationItem.newBuilder()
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
      unk3_ = null;
      if (unk3Builder_ != null) {
        unk3Builder_.dispose();
        unk3Builder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return YTVideoComments.internal_static_ContinuationItem_descriptor;
    }

    @Override
    public ContinuationItem getDefaultInstanceForType() {
      return ContinuationItem.getDefaultInstance();
    }

    @Override
    public ContinuationItem build() {
      ContinuationItem result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public ContinuationItem buildPartial() {
      ContinuationItem result = new ContinuationItem(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(ContinuationItem result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.unk3_ = unk3Builder_ == null
            ? unk3_
            : unk3Builder_.build();
      }
    }

    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof ContinuationItem) {
        return mergeFrom((ContinuationItem)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ContinuationItem other) {
      if (other == ContinuationItem.getDefaultInstance()) return this;
      if (other.hasUnk3()) {
        mergeUnk3(other.getUnk3());
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
            case 26: {
              input.readMessage(
                  getUnk3FieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
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

    private UNK3 unk3_;
    private com.google.protobuf.SingleFieldBuilderV3<
        UNK3, UNK3.Builder, UNK3OrBuilder> unk3Builder_;
    /**
     * <code>.UNK3 unk3 = 3;</code>
     * @return Whether the unk3 field is set.
     */
    public boolean hasUnk3() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.UNK3 unk3 = 3;</code>
     * @return The unk3.
     */
    public UNK3 getUnk3() {
      if (unk3Builder_ == null) {
        return unk3_ == null ? UNK3.getDefaultInstance() : unk3_;
      } else {
        return unk3Builder_.getMessage();
      }
    }
    /**
     * <code>.UNK3 unk3 = 3;</code>
     */
    public Builder setUnk3(UNK3 value) {
      if (unk3Builder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        unk3_ = value;
      } else {
        unk3Builder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.UNK3 unk3 = 3;</code>
     */
    public Builder setUnk3(
        UNK3.Builder builderForValue) {
      if (unk3Builder_ == null) {
        unk3_ = builderForValue.build();
      } else {
        unk3Builder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.UNK3 unk3 = 3;</code>
     */
    public Builder mergeUnk3(UNK3 value) {
      if (unk3Builder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          unk3_ != null &&
          unk3_ != UNK3.getDefaultInstance()) {
          getUnk3Builder().mergeFrom(value);
        } else {
          unk3_ = value;
        }
      } else {
        unk3Builder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.UNK3 unk3 = 3;</code>
     */
    public Builder clearUnk3() {
      bitField0_ = (bitField0_ & ~0x00000001);
      unk3_ = null;
      if (unk3Builder_ != null) {
        unk3Builder_.dispose();
        unk3Builder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.UNK3 unk3 = 3;</code>
     */
    public UNK3.Builder getUnk3Builder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getUnk3FieldBuilder().getBuilder();
    }
    /**
     * <code>.UNK3 unk3 = 3;</code>
     */
    public UNK3OrBuilder getUnk3OrBuilder() {
      if (unk3Builder_ != null) {
        return unk3Builder_.getMessageOrBuilder();
      } else {
        return unk3_ == null ?
            UNK3.getDefaultInstance() : unk3_;
      }
    }
    /**
     * <code>.UNK3 unk3 = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        UNK3, UNK3.Builder, UNK3OrBuilder>
        getUnk3FieldBuilder() {
      if (unk3Builder_ == null) {
        unk3Builder_ = new com.google.protobuf.SingleFieldBuilderV3<
            UNK3, UNK3.Builder, UNK3OrBuilder>(
                getUnk3(),
                getParentForChildren(),
                isClean());
        unk3_ = null;
      }
      return unk3Builder_;
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


    // @@protoc_insertion_point(builder_scope:ContinuationItem)
  }

  // @@protoc_insertion_point(class_scope:ContinuationItem)
  private static final ContinuationItem DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ContinuationItem();
  }

  public static ContinuationItem getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ContinuationItem>
      PARSER = new com.google.protobuf.AbstractParser<ContinuationItem>() {
    @Override
    public ContinuationItem parsePartialFrom(
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

  public static com.google.protobuf.Parser<ContinuationItem> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<ContinuationItem> getParserForType() {
    return PARSER;
  }

  @Override
  public ContinuationItem getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

