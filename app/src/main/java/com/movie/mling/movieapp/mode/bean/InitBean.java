package com.movie.mling.movieapp.mode.bean;

import java.io.Serializable;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/4/2 15:01
 * $DESE$
 */
public class InitBean {

    /**
     * code : 0
     * message : success
     * data : {"adshow":1,"ad":{"adpic":"http://cdn.23so.cn/web/upload/201805/11/7932c53944a9238def1f749d1edc77acbb446bb2.jpg","adid":"316","adtype":"user","adtime":"5"},"android_last_version":"1.0.0","android_must_update":"0","android_update_url":"http://xxx.com/1.2.apk","ios_last_version":"1.0.0","ios_must_update":"0","ios_update_url":"http://ios.com/","phone":"15562679693"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * adshow : 1
         * ad : {"adpic":"http://cdn.23so.cn/web/upload/201805/11/7932c53944a9238def1f749d1edc77acbb446bb2.jpg","adid":"316","adtype":"user","adtime":"5"}
         * android_last_version : 1.0.0
         * android_must_update : 0
         * android_update_url : http://xxx.com/1.2.apk
         * ios_last_version : 1.0.0
         * ios_must_update : 0
         * ios_update_url : http://ios.com/
         * phone : 15562679693
         */

        private int adshow;
        private AdBean ad;
        private String android_last_version;
        private String android_must_update;
        private String android_update_url;
        private String ios_last_version;
        private String ios_must_update;
        private String ios_update_url;
        private String phone;

        public int getAdshow() {
            return adshow;
        }

        public void setAdshow(int adshow) {
            this.adshow = adshow;
        }

        public AdBean getAd() {
            return ad;
        }

        public void setAd(AdBean ad) {
            this.ad = ad;
        }

        public String getAndroid_last_version() {
            return android_last_version;
        }

        public void setAndroid_last_version(String android_last_version) {
            this.android_last_version = android_last_version;
        }

        public String getAndroid_must_update() {
            return android_must_update;
        }

        public void setAndroid_must_update(String android_must_update) {
            this.android_must_update = android_must_update;
        }

        public String getAndroid_update_url() {
            return android_update_url;
        }

        public void setAndroid_update_url(String android_update_url) {
            this.android_update_url = android_update_url;
        }

        public String getIos_last_version() {
            return ios_last_version;
        }

        public void setIos_last_version(String ios_last_version) {
            this.ios_last_version = ios_last_version;
        }

        public String getIos_must_update() {
            return ios_must_update;
        }

        public void setIos_must_update(String ios_must_update) {
            this.ios_must_update = ios_must_update;
        }

        public String getIos_update_url() {
            return ios_update_url;
        }

        public void setIos_update_url(String ios_update_url) {
            this.ios_update_url = ios_update_url;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public static class AdBean  implements Serializable {
            /**
             * adpic : http://cdn.23so.cn/web/upload/201805/11/7932c53944a9238def1f749d1edc77acbb446bb2.jpg
             * adid : 316
             * adtype : user
             * adtime : 5
             */

            private String adpic;
            private String adid;
            private String adtype;
            private int adtime;

            public String getAdpic() {
                return adpic;
            }

            public void setAdpic(String adpic) {
                this.adpic = adpic;
            }

            public String getAdid() {
                return adid;
            }

            public void setAdid(String adid) {
                this.adid = adid;
            }

            public String getAdtype() {
                return adtype;
            }

            public void setAdtype(String adtype) {
                this.adtype = adtype;
            }

            public int getAdtime() {
                return adtime;
            }

            public void setAdtime(int adtime) {
                this.adtime = adtime;
            }
        }
    }
}
