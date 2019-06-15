package com.movie.mling.movieapp.mode.bean;

import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/15 14:08
 * $DESE$
 */
public class WorkBean {

    /**
     * code : 0
     * message : success
     * data : [{"id":"1","cname":"制片人","px":"0"},{"id":"2","cname":"出品人","px":"0"},{"id":"3","cname":"导演","px":"0"},{"id":"4","cname":"监制","px":"0"},{"id":"5","cname":"编剧","px":"0"},{"id":"6","cname":"选角导演","px":"0"},{"id":"7","cname":"副导演","px":"0"},{"id":"8","cname":"现场导演","px":"0"},{"id":"9","cname":"演员","px":"0"},{"id":"10","cname":"模特","px":"0"},{"id":"11","cname":"特行演员","px":"0"},{"id":"12","cname":"武术演员","px":"0"},{"id":"13","cname":"外籍演员","px":"0"},{"id":"14","cname":"舞蹈演员","px":"0"},{"id":"15","cname":"歌手","px":"0"},{"id":"16","cname":"主持人","px":"0"},{"id":"17","cname":"群演","px":"0"},{"id":"18","cname":"群头","px":"0"},{"id":"19","cname":"艺人总监","px":"0"},{"id":"20","cname":"经纪人","px":"0"},{"id":"21","cname":"艺人助理","px":"0"},{"id":"22","cname":"影视公司高管","px":"0"},{"id":"23","cname":"幕后","px":"0"},{"id":"24","cname":"拍摄现场","px":"0"},{"id":"25","cname":"其他","px":"0"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * cname : 制片人
         * px : 0
         */

        private String id;
        private String cname;
        private String px;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getPx() {
            return px;
        }

        public void setPx(String px) {
            this.px = px;
        }
    }
}
