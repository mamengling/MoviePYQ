package com.movie.mling.movieapp.mode.bean;

import java.util.List;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2018/3/20 23:05
 */

public class MsgNoticeBean {

    /**
     * code : 0
     * message : success
     * data : {"total":53,"list":[{"id":"324","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"中国首部数亿投资，融合商战，谍战，军事类型元素，电视剧《守望者》开始筹备","status":"0","lasttime":"2018-03-20 21:02:16","isread":"1"},{"id":"321","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"看看金鱼能收到么\n","status":"0","lasttime":"2018-03-20 17:57:35","isread":"1"},{"id":"320","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"就金鱼收不到信息\n","status":"0","lasttime":"2018-03-20 17:54:28","isread":"1"},{"id":"317","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"金鱼测试","status":"0","lasttime":"2018-03-20 17:32:03","isread":"1"},{"id":"316","isadmin":"1","ext_type":"film","ext_id":"555","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"66666666","status":"0","lasttime":"2018-03-20 17:23:36","isread":"1"},{"id":"315","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"1010","status":"0","lasttime":"2018-03-20 17:21:54","isread":"1"},{"id":"314","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"999","status":"0","lasttime":"2018-03-20 17:21:35","isread":"1"},{"id":"313","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"77777","status":"0","lasttime":"2018-03-20 17:21:09","isread":"1"},{"id":"312","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"66666","status":"0","lasttime":"2018-03-20 17:20:35","isread":"1"},{"id":"311","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"群发","status":"0","lasttime":"2018-03-20 17:18:55","isread":"1"}]}
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
         * total : 53
         * list : [{"id":"324","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"中国首部数亿投资，融合商战，谍战，军事类型元素，电视剧《守望者》开始筹备","status":"0","lasttime":"2018-03-20 21:02:16","isread":"1"},{"id":"321","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"看看金鱼能收到么\n","status":"0","lasttime":"2018-03-20 17:57:35","isread":"1"},{"id":"320","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"就金鱼收不到信息\n","status":"0","lasttime":"2018-03-20 17:54:28","isread":"1"},{"id":"317","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"金鱼测试","status":"0","lasttime":"2018-03-20 17:32:03","isread":"1"},{"id":"316","isadmin":"1","ext_type":"film","ext_id":"555","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"66666666","status":"0","lasttime":"2018-03-20 17:23:36","isread":"1"},{"id":"315","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"1010","status":"0","lasttime":"2018-03-20 17:21:54","isread":"1"},{"id":"314","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"999","status":"0","lasttime":"2018-03-20 17:21:35","isread":"1"},{"id":"313","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"77777","status":"0","lasttime":"2018-03-20 17:21:09","isread":"1"},{"id":"312","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"66666","status":"0","lasttime":"2018-03-20 17:20:35","isread":"1"},{"id":"311","isadmin":"1","ext_type":"","ext_id":"0","uid":"0","from_uid":"0","tags":"0","keytype":"5","keytext":"群发","status":"0","lasttime":"2018-03-20 17:18:55","isread":"1"}]
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
             * id : 324
             * isadmin : 1
             * ext_type :
             * ext_id : 0
             * uid : 0
             * from_uid : 0
             * tags : 0
             * keytype : 5
             * keytext : 中国首部数亿投资，融合商战，谍战，军事类型元素，电视剧《守望者》开始筹备
             * status : 0
             * lasttime : 2018-03-20 21:02:16
             * isread : 1
             */

            private String id;
            private String isadmin;
            private String ext_type;
            private String from_avatar;
            private String ext_id;
            private String uid;
            private String from_uid;
            private String tags;
            private String keytype;
            private String keytext;
            private String from_nickname;
            private String status;
            private String lasttime;
            private String isread;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIsadmin() {
                return isadmin;
            }

            public void setIsadmin(String isadmin) {
                this.isadmin = isadmin;
            }

            public String getFrom_nickname() {
                return from_nickname;
            }

            public void setFrom_nickname(String from_nickname) {
                this.from_nickname = from_nickname;
            }

            public String getExt_type() {
                return ext_type;
            }

            public String getFrom_avatar() {
                return from_avatar;
            }

            public void setFrom_avatar(String from_avatar) {
                this.from_avatar = from_avatar;
            }

            public void setExt_type(String ext_type) {
                this.ext_type = ext_type;
            }

            public String getExt_id() {
                return ext_id;
            }

            public void setExt_id(String ext_id) {
                this.ext_id = ext_id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getFrom_uid() {
                return from_uid;
            }

            public void setFrom_uid(String from_uid) {
                this.from_uid = from_uid;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getKeytype() {
                return keytype;
            }

            public void setKeytype(String keytype) {
                this.keytype = keytype;
            }

            public String getKeytext() {
                return keytext;
            }

            public void setKeytext(String keytext) {
                this.keytext = keytext;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getLasttime() {
                return lasttime;
            }

            public void setLasttime(String lasttime) {
                this.lasttime = lasttime;
            }

            public String getIsread() {
                return isread;
            }

            public void setIsread(String isread) {
                this.isread = isread;
            }
        }
    }
}
