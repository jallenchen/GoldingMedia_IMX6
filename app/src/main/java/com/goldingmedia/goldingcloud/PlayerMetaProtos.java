// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PlayerMeta.proto

package com.goldingmedia.goldingcloud;

public final class PlayerMetaProtos {
  private PlayerMetaProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CPlayerMetaOrBuilder extends
      // @@protoc_insertion_point(interface_extends:goldingcloud.CPlayerMeta)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * play total time
     * </pre>
     *
     * <code>int32 total_time = 1;</code>
     */
    int getTotalTime();

    /**
     * <pre>
     * play current time position
     * </pre>
     *
     * <code>int32 current_time = 2;</code>
     */
    int getCurrentTime();

    /**
     * <pre>
     * play count in one day
     * </pre>
     *
     * <code>int32 play_count = 3;</code>
     */
    int getPlayCount();

    /**
     * <pre>
     * delay specify time play
     * </pre>
     *
     * <code>int32 play_delay = 4;</code>
     */
    int getPlayDelay();

    /**
     * <pre>
     * play interval time gap
     * </pre>
     *
     * <code>int32 play_interval = 5;</code>
     */
    int getPlayInterval();

    /**
     * <pre>
     * priority:1,2,3,4
     * </pre>
     *
     * <code>int32 play_priority = 6;</code>
     */
    int getPlayPriority();

    /**
     * <pre>
     * current play state
     * </pre>
     *
     * <code>.goldingcloud.CPlayerMeta.PlayState play_state = 7;</code>
     */
    int getPlayStateValue();
    /**
     * <pre>
     * current play state
     * </pre>
     *
     * <code>.goldingcloud.CPlayerMeta.PlayState play_state = 7;</code>
     */
    com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState getPlayState();
  }
  /**
   * <pre>
   * [START messages]
   * </pre>
   *
   * Protobuf type {@code goldingcloud.CPlayerMeta}
   */
  public  static final class CPlayerMeta extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:goldingcloud.CPlayerMeta)
      CPlayerMetaOrBuilder {
    // Use CPlayerMeta.newBuilder() to construct.
    private CPlayerMeta(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private CPlayerMeta() {
      totalTime_ = 0;
      currentTime_ = 0;
      playCount_ = 0;
      playDelay_ = 0;
      playInterval_ = 0;
      playPriority_ = 0;
      playState_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private CPlayerMeta(
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
            case 8: {

              totalTime_ = input.readInt32();
              break;
            }
            case 16: {

              currentTime_ = input.readInt32();
              break;
            }
            case 24: {

              playCount_ = input.readInt32();
              break;
            }
            case 32: {

              playDelay_ = input.readInt32();
              break;
            }
            case 40: {

              playInterval_ = input.readInt32();
              break;
            }
            case 48: {

              playPriority_ = input.readInt32();
              break;
            }
            case 56: {
              int rawValue = input.readEnum();

              playState_ = rawValue;
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
      return com.goldingmedia.goldingcloud.PlayerMetaProtos.internal_static_goldingcloud_CPlayerMeta_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.goldingmedia.goldingcloud.PlayerMetaProtos.internal_static_goldingcloud_CPlayerMeta_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.class, com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.Builder.class);
    }

    /**
     * Protobuf enum {@code goldingcloud.CPlayerMeta.PlayState}
     */
    public enum PlayState
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>PLAY_NORMAL = 0;</code>
       */
      PLAY_NORMAL(0),
      /**
       * <code>PLAY_FAST_FORWARD = 1;</code>
       */
      PLAY_FAST_FORWARD(1),
      /**
       * <code>PLAY_FAST_BACKWARD = 2;</code>
       */
      PLAY_FAST_BACKWARD(2),
      /**
       * <code>PLAY_REWIND = 3;</code>
       */
      PLAY_REWIND(3),
      /**
       * <code>PLAY_EXIT = 4;</code>
       */
      PLAY_EXIT(4),
      /**
       * <code>PLAY_START = 5;</code>
       */
      PLAY_START(5),
      /**
       * <code>PLAY_PAUSE = 6;</code>
       */
      PLAY_PAUSE(6),
      /**
       * <code>PLAY_STOP = 7;</code>
       */
      PLAY_STOP(7),
      /**
       * <code>PLAY_EXCEPTION = 8;</code>
       */
      PLAY_EXCEPTION(8),
      UNRECOGNIZED(-1),
      ;

      /**
       * <code>PLAY_NORMAL = 0;</code>
       */
      public static final int PLAY_NORMAL_VALUE = 0;
      /**
       * <code>PLAY_FAST_FORWARD = 1;</code>
       */
      public static final int PLAY_FAST_FORWARD_VALUE = 1;
      /**
       * <code>PLAY_FAST_BACKWARD = 2;</code>
       */
      public static final int PLAY_FAST_BACKWARD_VALUE = 2;
      /**
       * <code>PLAY_REWIND = 3;</code>
       */
      public static final int PLAY_REWIND_VALUE = 3;
      /**
       * <code>PLAY_EXIT = 4;</code>
       */
      public static final int PLAY_EXIT_VALUE = 4;
      /**
       * <code>PLAY_START = 5;</code>
       */
      public static final int PLAY_START_VALUE = 5;
      /**
       * <code>PLAY_PAUSE = 6;</code>
       */
      public static final int PLAY_PAUSE_VALUE = 6;
      /**
       * <code>PLAY_STOP = 7;</code>
       */
      public static final int PLAY_STOP_VALUE = 7;
      /**
       * <code>PLAY_EXCEPTION = 8;</code>
       */
      public static final int PLAY_EXCEPTION_VALUE = 8;


      public final int getNumber() {
        if (this == UNRECOGNIZED) {
          throw new java.lang.IllegalArgumentException(
              "Can't get the number of an unknown enum value.");
        }
        return value;
      }

      /**
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static PlayState valueOf(int value) {
        return forNumber(value);
      }

      public static PlayState forNumber(int value) {
        switch (value) {
          case 0: return PLAY_NORMAL;
          case 1: return PLAY_FAST_FORWARD;
          case 2: return PLAY_FAST_BACKWARD;
          case 3: return PLAY_REWIND;
          case 4: return PLAY_EXIT;
          case 5: return PLAY_START;
          case 6: return PLAY_PAUSE;
          case 7: return PLAY_STOP;
          case 8: return PLAY_EXCEPTION;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<PlayState>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          PlayState> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<PlayState>() {
              public PlayState findValueByNumber(int number) {
                return PlayState.forNumber(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(ordinal());
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.getDescriptor().getEnumTypes().get(0);
      }

      private static final PlayState[] VALUES = values();

      public static PlayState valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        if (desc.getIndex() == -1) {
          return UNRECOGNIZED;
        }
        return VALUES[desc.getIndex()];
      }

      private final int value;

      private PlayState(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:goldingcloud.CPlayerMeta.PlayState)
    }

    public static final int TOTAL_TIME_FIELD_NUMBER = 1;
    private int totalTime_;
    /**
     * <pre>
     * play total time
     * </pre>
     *
     * <code>int32 total_time = 1;</code>
     */
    public int getTotalTime() {
      return totalTime_;
    }

    public static final int CURRENT_TIME_FIELD_NUMBER = 2;
    private int currentTime_;
    /**
     * <pre>
     * play current time position
     * </pre>
     *
     * <code>int32 current_time = 2;</code>
     */
    public int getCurrentTime() {
      return currentTime_;
    }

    public static final int PLAY_COUNT_FIELD_NUMBER = 3;
    private int playCount_;
    /**
     * <pre>
     * play count in one day
     * </pre>
     *
     * <code>int32 play_count = 3;</code>
     */
    public int getPlayCount() {
      return playCount_;
    }

    public static final int PLAY_DELAY_FIELD_NUMBER = 4;
    private int playDelay_;
    /**
     * <pre>
     * delay specify time play
     * </pre>
     *
     * <code>int32 play_delay = 4;</code>
     */
    public int getPlayDelay() {
      return playDelay_;
    }

    public static final int PLAY_INTERVAL_FIELD_NUMBER = 5;
    private int playInterval_;
    /**
     * <pre>
     * play interval time gap
     * </pre>
     *
     * <code>int32 play_interval = 5;</code>
     */
    public int getPlayInterval() {
      return playInterval_;
    }

    public static final int PLAY_PRIORITY_FIELD_NUMBER = 6;
    private int playPriority_;
    /**
     * <pre>
     * priority:1,2,3,4
     * </pre>
     *
     * <code>int32 play_priority = 6;</code>
     */
    public int getPlayPriority() {
      return playPriority_;
    }

    public static final int PLAY_STATE_FIELD_NUMBER = 7;
    private int playState_;
    /**
     * <pre>
     * current play state
     * </pre>
     *
     * <code>.goldingcloud.CPlayerMeta.PlayState play_state = 7;</code>
     */
    public int getPlayStateValue() {
      return playState_;
    }
    /**
     * <pre>
     * current play state
     * </pre>
     *
     * <code>.goldingcloud.CPlayerMeta.PlayState play_state = 7;</code>
     */
    public com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState getPlayState() {
      com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState result = com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState.valueOf(playState_);
      return result == null ? com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState.UNRECOGNIZED : result;
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
      if (totalTime_ != 0) {
        output.writeInt32(1, totalTime_);
      }
      if (currentTime_ != 0) {
        output.writeInt32(2, currentTime_);
      }
      if (playCount_ != 0) {
        output.writeInt32(3, playCount_);
      }
      if (playDelay_ != 0) {
        output.writeInt32(4, playDelay_);
      }
      if (playInterval_ != 0) {
        output.writeInt32(5, playInterval_);
      }
      if (playPriority_ != 0) {
        output.writeInt32(6, playPriority_);
      }
      if (playState_ != com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState.PLAY_NORMAL.getNumber()) {
        output.writeEnum(7, playState_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (totalTime_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, totalTime_);
      }
      if (currentTime_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, currentTime_);
      }
      if (playCount_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, playCount_);
      }
      if (playDelay_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, playDelay_);
      }
      if (playInterval_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, playInterval_);
      }
      if (playPriority_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(6, playPriority_);
      }
      if (playState_ != com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState.PLAY_NORMAL.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(7, playState_);
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
      if (!(obj instanceof com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta)) {
        return super.equals(obj);
      }
      com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta other = (com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta) obj;

      boolean result = true;
      result = result && (getTotalTime()
          == other.getTotalTime());
      result = result && (getCurrentTime()
          == other.getCurrentTime());
      result = result && (getPlayCount()
          == other.getPlayCount());
      result = result && (getPlayDelay()
          == other.getPlayDelay());
      result = result && (getPlayInterval()
          == other.getPlayInterval());
      result = result && (getPlayPriority()
          == other.getPlayPriority());
      result = result && playState_ == other.playState_;
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TOTAL_TIME_FIELD_NUMBER;
      hash = (53 * hash) + getTotalTime();
      hash = (37 * hash) + CURRENT_TIME_FIELD_NUMBER;
      hash = (53 * hash) + getCurrentTime();
      hash = (37 * hash) + PLAY_COUNT_FIELD_NUMBER;
      hash = (53 * hash) + getPlayCount();
      hash = (37 * hash) + PLAY_DELAY_FIELD_NUMBER;
      hash = (53 * hash) + getPlayDelay();
      hash = (37 * hash) + PLAY_INTERVAL_FIELD_NUMBER;
      hash = (53 * hash) + getPlayInterval();
      hash = (37 * hash) + PLAY_PRIORITY_FIELD_NUMBER;
      hash = (53 * hash) + getPlayPriority();
      hash = (37 * hash) + PLAY_STATE_FIELD_NUMBER;
      hash = (53 * hash) + playState_;
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parseFrom(
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
    public static Builder newBuilder(com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta prototype) {
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
     * Protobuf type {@code goldingcloud.CPlayerMeta}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:goldingcloud.CPlayerMeta)
        com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMetaOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.goldingmedia.goldingcloud.PlayerMetaProtos.internal_static_goldingcloud_CPlayerMeta_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.goldingmedia.goldingcloud.PlayerMetaProtos.internal_static_goldingcloud_CPlayerMeta_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.class, com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.Builder.class);
      }

      // Construct using com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.newBuilder()
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
        totalTime_ = 0;

        currentTime_ = 0;

        playCount_ = 0;

        playDelay_ = 0;

        playInterval_ = 0;

        playPriority_ = 0;

        playState_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.goldingmedia.goldingcloud.PlayerMetaProtos.internal_static_goldingcloud_CPlayerMeta_descriptor;
      }

      public com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta getDefaultInstanceForType() {
        return com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.getDefaultInstance();
      }

      public com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta build() {
        com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta buildPartial() {
        com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta result = new com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta(this);
        result.totalTime_ = totalTime_;
        result.currentTime_ = currentTime_;
        result.playCount_ = playCount_;
        result.playDelay_ = playDelay_;
        result.playInterval_ = playInterval_;
        result.playPriority_ = playPriority_;
        result.playState_ = playState_;
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
        if (other instanceof com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta) {
          return mergeFrom((com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta other) {
        if (other == com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.getDefaultInstance()) return this;
        if (other.getTotalTime() != 0) {
          setTotalTime(other.getTotalTime());
        }
        if (other.getCurrentTime() != 0) {
          setCurrentTime(other.getCurrentTime());
        }
        if (other.getPlayCount() != 0) {
          setPlayCount(other.getPlayCount());
        }
        if (other.getPlayDelay() != 0) {
          setPlayDelay(other.getPlayDelay());
        }
        if (other.getPlayInterval() != 0) {
          setPlayInterval(other.getPlayInterval());
        }
        if (other.getPlayPriority() != 0) {
          setPlayPriority(other.getPlayPriority());
        }
        if (other.playState_ != 0) {
          setPlayStateValue(other.getPlayStateValue());
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
        com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int totalTime_ ;
      /**
       * <pre>
       * play total time
       * </pre>
       *
       * <code>int32 total_time = 1;</code>
       */
      public int getTotalTime() {
        return totalTime_;
      }
      /**
       * <pre>
       * play total time
       * </pre>
       *
       * <code>int32 total_time = 1;</code>
       */
      public Builder setTotalTime(int value) {
        
        totalTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * play total time
       * </pre>
       *
       * <code>int32 total_time = 1;</code>
       */
      public Builder clearTotalTime() {
        
        totalTime_ = 0;
        onChanged();
        return this;
      }

      private int currentTime_ ;
      /**
       * <pre>
       * play current time position
       * </pre>
       *
       * <code>int32 current_time = 2;</code>
       */
      public int getCurrentTime() {
        return currentTime_;
      }
      /**
       * <pre>
       * play current time position
       * </pre>
       *
       * <code>int32 current_time = 2;</code>
       */
      public Builder setCurrentTime(int value) {
        
        currentTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * play current time position
       * </pre>
       *
       * <code>int32 current_time = 2;</code>
       */
      public Builder clearCurrentTime() {
        
        currentTime_ = 0;
        onChanged();
        return this;
      }

      private int playCount_ ;
      /**
       * <pre>
       * play count in one day
       * </pre>
       *
       * <code>int32 play_count = 3;</code>
       */
      public int getPlayCount() {
        return playCount_;
      }
      /**
       * <pre>
       * play count in one day
       * </pre>
       *
       * <code>int32 play_count = 3;</code>
       */
      public Builder setPlayCount(int value) {
        
        playCount_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * play count in one day
       * </pre>
       *
       * <code>int32 play_count = 3;</code>
       */
      public Builder clearPlayCount() {
        
        playCount_ = 0;
        onChanged();
        return this;
      }

      private int playDelay_ ;
      /**
       * <pre>
       * delay specify time play
       * </pre>
       *
       * <code>int32 play_delay = 4;</code>
       */
      public int getPlayDelay() {
        return playDelay_;
      }
      /**
       * <pre>
       * delay specify time play
       * </pre>
       *
       * <code>int32 play_delay = 4;</code>
       */
      public Builder setPlayDelay(int value) {
        
        playDelay_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * delay specify time play
       * </pre>
       *
       * <code>int32 play_delay = 4;</code>
       */
      public Builder clearPlayDelay() {
        
        playDelay_ = 0;
        onChanged();
        return this;
      }

      private int playInterval_ ;
      /**
       * <pre>
       * play interval time gap
       * </pre>
       *
       * <code>int32 play_interval = 5;</code>
       */
      public int getPlayInterval() {
        return playInterval_;
      }
      /**
       * <pre>
       * play interval time gap
       * </pre>
       *
       * <code>int32 play_interval = 5;</code>
       */
      public Builder setPlayInterval(int value) {
        
        playInterval_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * play interval time gap
       * </pre>
       *
       * <code>int32 play_interval = 5;</code>
       */
      public Builder clearPlayInterval() {
        
        playInterval_ = 0;
        onChanged();
        return this;
      }

      private int playPriority_ ;
      /**
       * <pre>
       * priority:1,2,3,4
       * </pre>
       *
       * <code>int32 play_priority = 6;</code>
       */
      public int getPlayPriority() {
        return playPriority_;
      }
      /**
       * <pre>
       * priority:1,2,3,4
       * </pre>
       *
       * <code>int32 play_priority = 6;</code>
       */
      public Builder setPlayPriority(int value) {
        
        playPriority_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * priority:1,2,3,4
       * </pre>
       *
       * <code>int32 play_priority = 6;</code>
       */
      public Builder clearPlayPriority() {
        
        playPriority_ = 0;
        onChanged();
        return this;
      }

      private int playState_ = 0;
      /**
       * <pre>
       * current play state
       * </pre>
       *
       * <code>.goldingcloud.CPlayerMeta.PlayState play_state = 7;</code>
       */
      public int getPlayStateValue() {
        return playState_;
      }
      /**
       * <pre>
       * current play state
       * </pre>
       *
       * <code>.goldingcloud.CPlayerMeta.PlayState play_state = 7;</code>
       */
      public Builder setPlayStateValue(int value) {
        playState_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * current play state
       * </pre>
       *
       * <code>.goldingcloud.CPlayerMeta.PlayState play_state = 7;</code>
       */
      public com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState getPlayState() {
        com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState result = com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState.valueOf(playState_);
        return result == null ? com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState.UNRECOGNIZED : result;
      }
      /**
       * <pre>
       * current play state
       * </pre>
       *
       * <code>.goldingcloud.CPlayerMeta.PlayState play_state = 7;</code>
       */
      public Builder setPlayState(com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta.PlayState value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        playState_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * current play state
       * </pre>
       *
       * <code>.goldingcloud.CPlayerMeta.PlayState play_state = 7;</code>
       */
      public Builder clearPlayState() {
        
        playState_ = 0;
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


      // @@protoc_insertion_point(builder_scope:goldingcloud.CPlayerMeta)
    }

    // @@protoc_insertion_point(class_scope:goldingcloud.CPlayerMeta)
    private static final com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta();
    }

    public static com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CPlayerMeta>
        PARSER = new com.google.protobuf.AbstractParser<CPlayerMeta>() {
      public CPlayerMeta parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new CPlayerMeta(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<CPlayerMeta> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CPlayerMeta> getParserForType() {
      return PARSER;
    }

    public com.goldingmedia.goldingcloud.PlayerMetaProtos.CPlayerMeta getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_goldingcloud_CPlayerMeta_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_goldingcloud_CPlayerMeta_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020PlayerMeta.proto\022\014goldingcloud\"\367\002\n\013CPl" +
      "ayerMeta\022\022\n\ntotal_time\030\001 \001(\005\022\024\n\014current_" +
      "time\030\002 \001(\005\022\022\n\nplay_count\030\003 \001(\005\022\022\n\nplay_d" +
      "elay\030\004 \001(\005\022\025\n\rplay_interval\030\005 \001(\005\022\025\n\rpla" +
      "y_priority\030\006 \001(\005\0227\n\nplay_state\030\007 \001(\0162#.g" +
      "oldingcloud.CPlayerMeta.PlayState\"\256\001\n\tPl" +
      "ayState\022\017\n\013PLAY_NORMAL\020\000\022\025\n\021PLAY_FAST_FO" +
      "RWARD\020\001\022\026\n\022PLAY_FAST_BACKWARD\020\002\022\017\n\013PLAY_" +
      "REWIND\020\003\022\r\n\tPLAY_EXIT\020\004\022\016\n\nPLAY_START\020\005\022" +
      "\016\n\nPLAY_PAUSE\020\006\022\r\n\tPLAY_STOP\020\007\022\022\n\016PLAY_E",
      "XCEPTION\020\010Bb\n\035com.goldingmedia.goldingcl" +
      "oudB\020PlayerMetaProtos\252\002.com.goldingmedia" +
      ".goldingcloud.PlayerMetaProtosb\006proto3"
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
    internal_static_goldingcloud_CPlayerMeta_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_goldingcloud_CPlayerMeta_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_goldingcloud_CPlayerMeta_descriptor,
        new java.lang.String[] { "TotalTime", "CurrentTime", "PlayCount", "PlayDelay", "PlayInterval", "PlayPriority", "PlayState", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}