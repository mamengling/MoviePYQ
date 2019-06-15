package com.movie.mling.movieapp.mode.bean;

import java.util.List;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/2/16 14:54
 */

public class UserMarkBean {


    /**
     * code : 0
     * message : success
     * data : {"total":1,"list":[{"id":"19","uid":"176","newstext":"KKK咯哦哦","lasttime":"1970-01-01 08:00"}]}
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

    public static class DataBean {
        /**
         * total : 1
         * list : [{"id":"19","uid":"176","newstext":"KKK咯哦哦","lasttime":"1970-01-01 08:00"}]
         */

        private int total;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 19
             * uid : 176
             * newstext : KKK咯哦哦
             * lasttime : 1970-01-01 08:00
             */

            private String id;
            private String uid;
            private String newstext;
            private String lasttime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNewstext() {
                return newstext;
            }

            public void setNewstext(String newstext) {
                this.newstext = newstext;
            }

            public String getLasttime() {
                return lasttime;
            }

            public void setLasttime(String lasttime) {
                this.lasttime = lasttime;
            }
        }
    }
}
