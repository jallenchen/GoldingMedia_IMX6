// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RequestSession.proto

package com.goldingmedia.goldingcloud;

public final class RequestSessionProtos {
  private RequestSessionProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CRequestSessionOrBuilder extends
      // @@protoc_insertion_point(interface_extends:goldingcloud.CRequestSession)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string dev_id = 1;</code>
     */
    java.lang.String getDevId();
    /**
     * <code>string dev_id = 1;</code>
     */
    com.google.protobuf.ByteString
        getDevIdBytes();

    /**
     * <pre>
     *MASTER = 1, TERMINAL = 2;
     * </pre>
     *
     * <code>int32 dev_type = 2;</code>
     */
    int getDevType();

    /**
     * <pre>
     *request operation code,example:delete,add,insert,move,move next
     * </pre>
     *
     * <code>int32 rqCode = 3;</code>
     */
    int getRqCode();

    /**
     * <pre>
     *"key=value&amp;" list or the other type
     * </pre>
     *
     * <code>string rqParam = 4;</code>
     */
    java.lang.String getRqParam();
    /**
     * <pre>
     *"key=value&amp;" list or the other type
     * </pre>
     *
     * <code>string rqParam = 4;</code>
     */
    com.google.protobuf.ByteString
        getRqParamBytes();
  }
  /**
   * <pre>
   * [START messages]
   * </pre>
   *
   * Protobuf type {@code goldingcloud.CRequestSession}
   */
  public  static final class CRequestSession extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:goldingcloud.CRequestSession)
      CRequestSessionOrBuilder {
    // Use CRequestSession.newBuilder() to construct.
    private CRequestSession(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private CRequestSession() {
      devId_ = "";
      devType_ = 0;
      rqCode_ = 0;
      rqParam_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private CRequestSession(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              devId_ = s;
              break;
            }
            case 16: {

              devType_ = input.readInt32();
              break;
            }
            case 24: {

              rqCode_ = input.readInt32();
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              rqParam_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.goldingmedia.goldingcloud.RequestSessionProtos.internal_static_goldingcloud_CRequestSession_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.goldingmedia.goldingcloud.RequestSessionProtos.internal_static_goldingcloud_CRequestSession_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession.class, com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession.Builder.class);
    }

    public static final int DEV_ID_FIELD_NUMBER = 1;
    private volatile java.lang.Object devId_;
    /**
     * <code>string dev_id = 1;</code>
     */
    public java.lang.String getDevId() {
      java.lang.Object ref = devId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        devId_ = s;
        return s;
      }
    }
    /**
     * <code>string dev_id = 1;</code>
     */
    public com.google.protobuf.ByteString
        getDevIdBytes() {
      java.lang.Object ref = devId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        devId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int DEV_TYPE_FIELD_NUMBER = 2;
    private int devType_;
    /**
     * <pre>
     *MASTER = 1, TERMINAL = 2;
     * </pre>
     *
     * <code>int32 dev_type = 2;</code>
     */
    public int getDevType() {
      return devType_;
    }

    public static final int RQCODE_FIELD_NUMBER = 3;
    private int rqCode_;
    /**
     * <pre>
     *request operation code,example:delete,add,insert,move,move next
     * </pre>
     *
     * <code>int32 rqCode = 3;</code>
     */
    public int getRqCode() {
      return rqCode_;
    }

    public static final int RQPARAM_FIELD_NUMBER = 4;
    private volatile java.lang.Object rqParam_;
    /**
     * <pre>
     *"key=value&amp;" list or the other type
     * </pre>
     *
     * <code>string rqParam = 4;</code>
     */
    public java.lang.String getRqParam() {
      java.lang.Object ref = rqParam_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        rqParam_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *"key=value&amp;" list or the other type
     * </pre>
     *
     * <code>string rqParam = 4;</code>
     */
    public com.google.protobuf.ByteString
        getRqParamBytes() {
      java.lang.Object ref = rqParam_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        rqParam_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getDevIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, devId_);
      }
      if (devType_ != 0) {
        output.writeInt32(2, devType_);
      }
      if (rqCode_ != 0) {
        output.writeInt32(3, rqCode_);
      }
      if (!getRqParamBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, rqParam_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getDevIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, devId_);
      }
      if (devType_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, devType_);
      }
      if (rqCode_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, rqCode_);
      }
      if (!getRqParamBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, rqParam_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession)) {
        return super.equals(obj);
      }
      com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession other = (com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession) obj;

      boolean result = true;
      result = result && getDevId()
          .equals(other.getDevId());
      result = result && (getDevType()
          == other.getDevType());
      result = result && (getRqCode()
          == other.getRqCode());
      result = result && getRqParam()
          .equals(other.getRqParam());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + DEV_ID_FIELD_NUMBER;
      hash = (53 * hash) + getDevId().hashCode();
      hash = (37 * hash) + DEV_TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getDevType();
      hash = (37 * hash) + RQCODE_FIELD_NUMBER;
      hash = (53 * hash) + getRqCode();
      hash = (37 * hash) + RQPARAM_FIELD_NUMBER;
      hash = (53 * hash) + getRqParam().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * [START messages]
     * </pre>
     *
     * Protobuf type {@code goldingcloud.CRequestSession}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:goldingcloud.CRequestSession)
        com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSessionOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.goldingmedia.goldingcloud.RequestSessionProtos.internal_static_goldingcloud_CRequestSession_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.goldingmedia.goldingcloud.RequestSessionProtos.internal_static_goldingcloud_CRequestSession_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession.class, com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession.Builder.class);
      }

      // Construct using com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        devId_ = "";

        devType_ = 0;

        rqCode_ = 0;

        rqParam_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.goldingmedia.goldingcloud.RequestSessionProtos.internal_static_goldingcloud_CRequestSession_descriptor;
      }

      public com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession getDefaultInstanceForType() {
        return com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession.getDefaultInstance();
      }

      public com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession build() {
        com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession buildPartial() {
        com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession result = new com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession(this);
        result.devId_ = devId_;
        result.devType_ = devType_;
        result.rqCode_ = rqCode_;
        result.rqParam_ = rqParam_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession) {
          return mergeFrom((com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession other) {
        if (other == com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession.getDefaultInstance()) return this;
        if (!other.getDevId().isEmpty()) {
          devId_ = other.devId_;
          onChanged();
        }
        if (other.getDevType() != 0) {
          setDevType(other.getDevType());
        }
        if (other.getRqCode() != 0) {
          setRqCode(other.getRqCode());
        }
        if (!other.getRqParam().isEmpty()) {
          rqParam_ = other.rqParam_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object devId_ = "";
      /**
       * <code>string dev_id = 1;</code>
       */
      public java.lang.String getDevId() {
        java.lang.Object ref = devId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          devId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string dev_id = 1;</code>
       */
      public com.google.protobuf.ByteString
          getDevIdBytes() {
        java.lang.Object ref = devId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          devId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string dev_id = 1;</code>
       */
      public Builder setDevId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        devId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string dev_id = 1;</code>
       */
      public Builder clearDevId() {
        
        devId_ = getDefaultInstance().getDevId();
        onChanged();
        return this;
      }
      /**
       * <code>string dev_id = 1;</code>
       */
      public Builder setDevIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        devId_ = value;
        onChanged();
        return this;
      }

      private int devType_ ;
      /**
       * <pre>
       *MASTER = 1, TERMINAL = 2;
       * </pre>
       *
       * <code>int32 dev_type = 2;</code>
       */
      public int getDevType() {
        return devType_;
      }
      /**
       * <pre>
       *MASTER = 1, TERMINAL = 2;
       * </pre>
       *
       * <code>int32 dev_type = 2;</code>
       */
      public Builder setDevType(int value) {
        
        devType_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *MASTER = 1, TERMINAL = 2;
       * </pre>
       *
       * <code>int32 dev_type = 2;</code>
       */
      public Builder clearDevType() {
        
        devType_ = 0;
        onChanged();
        return this;
      }

      private int rqCode_ ;
      /**
       * <pre>
       *request operation code,example:delete,add,insert,move,move next
       * </pre>
       *
       * <code>int32 rqCode = 3;</code>
       */
      public int getRqCode() {
        return rqCode_;
      }
      /**
       * <pre>
       *request operation code,example:delete,add,insert,move,move next
       * </pre>
       *
       * <code>int32 rqCode = 3;</code>
       */
      public Builder setRqCode(int value) {
        
        rqCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *request operation code,example:delete,add,insert,move,move next
       * </pre>
       *
       * <code>int32 rqCode = 3;</code>
       */
      public Builder clearRqCode() {
        
        rqCode_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object rqParam_ = "";
      /**
       * <pre>
       *"key=value&amp;" list or the other type
       * </pre>
       *
       * <code>string rqParam = 4;</code>
       */
      public java.lang.String getRqParam() {
        java.lang.Object ref = rqParam_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          rqParam_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *"key=value&amp;" list or the other type
       * </pre>
       *
       * <code>string rqParam = 4;</code>
       */
      public com.google.protobuf.ByteString
          getRqParamBytes() {
        java.lang.Object ref = rqParam_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          rqParam_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *"key=value&amp;" list or the other type
       * </pre>
       *
       * <code>string rqParam = 4;</code>
       */
      public Builder setRqParam(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        rqParam_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *"key=value&amp;" list or the other type
       * </pre>
       *
       * <code>string rqParam = 4;</code>
       */
      public Builder clearRqParam() {
        
        rqParam_ = getDefaultInstance().getRqParam();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *"key=value&amp;" list or the other type
       * </pre>
       *
       * <code>string rqParam = 4;</code>
       */
      public Builder setRqParamBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        rqParam_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:goldingcloud.CRequestSession)
    }

    // @@protoc_insertion_point(class_scope:goldingcloud.CRequestSession)
    private static final com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession();
    }

    public static com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CRequestSession>
        PARSER = new com.google.protobuf.AbstractParser<CRequestSession>() {
      public CRequestSession parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new CRequestSession(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<CRequestSession> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CRequestSession> getParserForType() {
      return PARSER;
    }

    public com.goldingmedia.goldingcloud.RequestSessionProtos.CRequestSession getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_goldingcloud_CRequestSession_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_goldingcloud_CRequestSession_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024RequestSession.proto\022\014goldingcloud\"T\n\017" +
      "CRequestSession\022\016\n\006dev_id\030\001 \001(\t\022\020\n\010dev_t" +
      "ype\030\002 \001(\005\022\016\n\006rqCode\030\003 \001(\005\022\017\n\007rqParam\030\004 \001" +
      "(\tBj\n\035com.goldingmedia.goldingcloudB\024Req" +
      "uestSessionProtos\252\0022com.goldingmedia.gol" +
      "dingcloud.RequestSessionProtosb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_goldingcloud_CRequestSession_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_goldingcloud_CRequestSession_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_goldingcloud_CRequestSession_descriptor,
        new java.lang.String[] { "DevId", "DevType", "RqCode", "RqParam", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
