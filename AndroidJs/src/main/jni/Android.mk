LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := jsc
LOCAL_SRC_FILES := libs/$(TARGET_ARCH_ABI)/libjsc.so
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := core
LOCAL_SRC_FILES := main.cpp
LOCAL_SHARED_LIBRARIES := jsc
include $(BUILD_SHARED_LIBRARY)
