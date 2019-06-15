package com.movie.mling.movieapp.mode.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 15:39
 * $DESE$
 */
public class ImageInfoBean {

    /**
     * data : [{"id":"145","titlepic":"http://cdn.23so.cn/upload/201802/28/08c1ed0859fb42c635f3818305747467c77d8ab91519792461.jpg","aid":"21"},{"id":"156","titlepic":"http://cdn.23so.cn/upload/201802/28/3d1f07d3a6f7f5ccdae05967e1370c52446096881519801540.jpg","aid":"21"},{"id":"155","titlepic":"http://cdn.23so.cn/upload/201802/28/36f05b499e68d83ea185a79bcb16db369fb239b11519801539.jpg","aid":"21"},{"id":"154","titlepic":"http://cdn.23so.cn/upload/201802/28/ce9de11bcd160dc7159dd64f27a1865c774324b21519801539.jpg","aid":"21"},{"id":"153","titlepic":"http://cdn.23so.cn/upload/201802/28/60254924621d0d6746ba150aa7eab98adaf07bb81519801538.jpg","aid":"21"},{"id":"152","titlepic":"http://cdn.23so.cn/upload/201802/28/d2a2e730d0cbe8d2e9e5e60157e21abe99ef26d91519801521.jpg","aid":"21"},{"id":"151","titlepic":"http://cdn.23so.cn/upload/201802/28/87adf6c507cbd627873608f87959c4456fd82ed31519801521.jpg","aid":"21"},{"id":"150","titlepic":"http://cdn.23so.cn/upload/201802/28/fb73f0825180a0dc5eafbfefe8e11f3319a0db9c1519801520.jpg","aid":"21"},{"id":"149","titlepic":"http://cdn.23so.cn/upload/201802/28/5f49d0ac8c8b0d7f23bd2807d5e43d3b23dd9bff1519801520.jpg","aid":"21"},{"id":"146","titlepic":"http://cdn.23so.cn/upload/201802/28/255bcade9327e75e78e4848714ee5339acdab1f21519792461.jpg","aid":"21"},{"id":"157","titlepic":"http://cdn.23so.cn/upload/201802/28/bd38779850f559d60f3b84ed2f8234b48dd8dd901519801540.jpg","aid":"21"}]
     * message : success
     * code : 0
     */

    private String message;
    private int code;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 145
         * titlepic : http://cdn.23so.cn/upload/201802/28/08c1ed0859fb42c635f3818305747467c77d8ab91519792461.jpg
         * aid : 21
         */

        private String id;
        private String titlepic;
        private String aid;
        private String fm = "0";
        private boolean show_view;

        public boolean isShow_view() {
            return show_view;
        }

        public void setShow_view(boolean show_view) {
            this.show_view = show_view;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitlepic() {
            return titlepic;
        }

        public String getFm() {
            return fm;
        }

        public void setFm(String fm) {
            this.fm = fm;
        }

        public void setTitlepic(String titlepic) {
            this.titlepic = titlepic;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.titlepic);
            dest.writeString(this.aid);
            dest.writeString(this.fm);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.titlepic = in.readString();
            this.aid = in.readString();
            this.fm = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
