package com.movie.mling.movieapp.mode.bean;

import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 14:26
 * $DESE$
 */
public class ImageBean {

    /**
     * data : [{"uid":"28","id":"21","pid":"145","title":"高科技","count":2,"titlepic":"http://cdn.23so.cn/upload/201802/28/08c1ed0859fb42c635f3818305747467c77d8ab91519792461.jpg!face"},{"uid":"28","id":"22","pid":"147","title":"李李李","count":2,"titlepic":"http://cdn.23so.cn/upload/201802/28/43a47d7e97ef8cac8db8356f417a393110f36a941519792679.jpg!face"},{"uid":"28","id":"23","pid":"0","title":"李李李李","count":0,"titlepic":null},{"uid":"28","id":"24","pid":"0","title":"您规模","count":0,"titlepic":null},{"uid":"28","id":"25","pid":"0","title":"晕咯咯咯","count":0,"titlepic":null}]
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

    public static class DataBean {
        /**
         * uid : 28
         * id : 21
         * pid : 145
         * title : 高科技
         * count : 2
         * titlepic : http://cdn.23so.cn/upload/201802/28/08c1ed0859fb42c635f3818305747467c77d8ab91519792461.jpg!face
         */

        private String uid;
        private String id;
        private String pid;
        private String title;
        private int count;
        private String titlepic;
        private String is_set="0";

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIs_set() {
            return is_set;
        }

        public void setIs_set(String is_set) {
            this.is_set = is_set;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getTitlepic() {
            return titlepic;
        }

        public void setTitlepic(String titlepic) {
            this.titlepic = titlepic;
        }
    }
}
