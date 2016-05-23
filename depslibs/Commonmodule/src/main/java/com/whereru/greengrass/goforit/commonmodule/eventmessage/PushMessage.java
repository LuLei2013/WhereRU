package com.whereru.greengrass.goforit.commonmodule.eventmessage;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Push 消息对应的实体类
 * <p/>
 * status 值为1时,标识用户进入商圈,data字段为该商圈的相关信息
 * status 值为2时,标识用户离开当前并进入新的商圈,data字段为新的商圈的相关信息
 * status 值为3时,标识用户离开当前商圈,data字段为空
 * Created by lulei on 16/5/10.
 */
public class PushMessage extends Message {
    @SerializedName("status")
    private int mStatus;
    @SerializedName("data")
    private Data data;

    public PushMessage() {
        super(MessageType.PUSH_MESSAGE_TYPE);
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public int getStatus() {
        return mStatus;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("name")
        protected String mName;//名字
        @SerializedName("abstract")
        protected String mAbstract;// 摘要
        @SerializedName("user_name")
        protected String mUserName;//  创建者名字
        @SerializedName("create_time")
        protected String mCreateTime;// 创建日期
        @SerializedName("biz_area_id")
        protected long mBusinessId;// id标识

        public Data() {

        }

        public long getBusinessId() {
            return mBusinessId;
        }

        public String getAbstract() {
            return mAbstract;
        }

        public String getCreateTime() {
            return mCreateTime;
        }

        public String getName() {
            return mName;
        }

        public String getUserName() {
            return mUserName;
        }

        public void setAbstract(String abt) {
            this.mAbstract = abt;
        }

        public void setBusinessId(long businessId) {
            this.mBusinessId = businessId;
        }

        public void setName(String mName) {
            this.mName = mName;
        }

        public void setCreateTime(String mCreateTime) {
            this.mCreateTime = mCreateTime;
        }

        public void setUserName(String mUserName) {
            this.mUserName = mUserName;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "mName='" + mName + '\'' +
                    ", mAbstract='" + mAbstract + '\'' +
                    ", mUserName='" + mUserName + '\'' +
                    ", mCreateTime='" + mCreateTime + '\'' +
                    ", mBusinessId=" + mBusinessId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PushMessage{" +
                "mStatus=" + mStatus +
                ", data=" + data +
                '}';
    }
}
