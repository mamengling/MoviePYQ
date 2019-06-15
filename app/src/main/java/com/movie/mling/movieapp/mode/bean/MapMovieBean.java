package com.movie.mling.movieapp.mode.bean;

import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/13 13:34
 * $DESE$
 */
public class MapMovieBean {

    /**
     * code : 0
     * message : success
     * data : [{"id":"459","uid":"0","lid":"228","title":"《回到明天》","titlepic":"http://cdn.23so.cn/web/20180312/ef2e6771ca40c304a593a2b85298ed24.jpg","isgood":"0","newstime":"17小时前 19:40","onclick":"0","leixing":"3","leixing_text":"网剧"}]
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
         * id : 459
         * uid : 0
         * lid : 228
         * title : 《回到明天》
         * titlepic : http://cdn.23so.cn/web/20180312/ef2e6771ca40c304a593a2b85298ed24.jpg
         * isgood : 0
         * newstime : 17小时前 19:40
         * onclick : 0
         * leixing : 3
         * leixing_text : 网剧
         */

        private String id;
        private String uid;
        private String lid;
        private String title;
        private String titlepic;
        private String isgood;
        private String newstime;
        private String onclick;
        private String leixing;
        private String leixing_text;
        private String fav;
        private String pao;

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

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitlepic() {
            return titlepic;
        }

        public void setTitlepic(String titlepic) {
            this.titlepic = titlepic;
        }

        public String getIsgood() {
            return isgood;
        }

        public void setIsgood(String isgood) {
            this.isgood = isgood;
        }

        public String getNewstime() {
            return newstime;
        }

        public void setNewstime(String newstime) {
            this.newstime = newstime;
        }

        public String getFav() {
            return fav;
        }

        public void setFav(String fav) {
            this.fav = fav;
        }

        public String getPao() {
            return pao;
        }

        public void setPao(String pao) {
            this.pao = pao;
        }

        public String getOnclick() {
            return onclick;
        }

        public void setOnclick(String onclick) {
            this.onclick = onclick;
        }

        public String getLeixing() {
            return leixing;
        }

        public void setLeixing(String leixing) {
            this.leixing = leixing;
        }

        public String getLeixing_text() {
            return leixing_text;
        }

        public void setLeixing_text(String leixing_text) {
            this.leixing_text = leixing_text;
        }
    }
}
