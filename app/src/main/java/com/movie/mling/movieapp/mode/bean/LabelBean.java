package com.movie.mling.movieapp.mode.bean;

import java.util.List;

/**
 * Created by MLing on 2018/5/15 0015.
 */

public class LabelBean {

    private List<MsgdateBean> msgdate;

    public List<MsgdateBean> getMsgdate() {
        return msgdate;
    }

    public void setMsgdate(List<MsgdateBean> msgdate) {
        this.msgdate = msgdate;
    }

    public static class MsgdateBean {
        /**
         * title : 基本资料
         * list : [{"type":"0","ltitle":"性别","content":"性别","isChenge":"0"},{"type":"1","ltitle":"男","content":"男","isChenge":"0"},{"type":"1","ltitle":"女","content":"女","isChenge":"0"},{"type":"101","ltitle":"","content":"","isChenge":""},{"type":"0","ltitle":"年龄","content":"年龄","isChenge":"0"},{"type":"2","ltitle":"最小年龄","content":"","isChenge":"0"},{"type":"3","ltitle":"岁","content":"岁","isChenge":"0"},{"type":"2","ltitle":"最大年龄","content":"","isChenge":""},{"type":"0","ltitle":"身高","content":"身高","isChenge":"0"},{"type":"2","ltitle":"最低身高","content":"","isChenge":"0"},{"type":"3","ltitle":"cm","content":"cm","isChenge":"0"},{"type":"2","ltitle":"最高身高","content":"","isChenge":""},{"type":"0","ltitle":"体重","content":"体重","isChenge":"0"},{"type":"2","ltitle":"最低体重","content":"","isChenge":"0"},{"type":"3","ltitle":"kg","content":"kg","isChenge":"0"},{"type":"2","ltitle":"最高体重","content":"","isChenge":""},{"type":"0","ltitle":"国籍","content":"国籍","isChenge":"0"},{"type":"1","ltitle":"国内","content":"国内","isChenge":"0"},{"type":"1","ltitle":"港澳台","content":"港澳台","isChenge":"0"},{"type":"1","ltitle":"海外","content":"海外","isChenge":"0"},{"type":"0","ltitle":"公司","content":"公司","isChenge":"0"},{"type":"1","ltitle":"有","content":"有","isChenge":"0"},{"type":"1","ltitle":"无","content":"无","isChenge":"0"},{"type":"101","ltitle":"","content":"","isChenge":""}]
         */

        private String title;
        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * type : 0
             * ltitle : 性别
             * content : 性别
             * isChenge : 0
             */

            private String type;
            private String ltitle;
            private String content;
            private String isChenge;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLtitle() {
                return ltitle;
            }

            public void setLtitle(String ltitle) {
                this.ltitle = ltitle;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIsChenge() {
                return isChenge;
            }

            public void setIsChenge(String isChenge) {
                this.isChenge = isChenge;
            }
        }
    }
}
