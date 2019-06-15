package com.movie.mling.movieapp.base;

import java.util.List;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/2/15 22:57
 */

public class MovieBannerBean {

    /**
     * code : 0
     * message : success
     * data : {"total":2,"list":[{"pic":"http://cdn.yingq.cc/web/upload/201902/15/d142d5cb450dac7d1127529b1515b9762f60613e.jpg","links":"https://www.huyahaha.com/"},{"pic":"http://cdn.yingq.cc/web/upload/201902/15/fdaed762b7d0c93ba4f4830103ec8962e538e373.jpg","links":"https://www.huyahaha.com/"}]}
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
         * total : 2
         * list : [{"pic":"http://cdn.yingq.cc/web/upload/201902/15/d142d5cb450dac7d1127529b1515b9762f60613e.jpg","links":"https://www.huyahaha.com/"},{"pic":"http://cdn.yingq.cc/web/upload/201902/15/fdaed762b7d0c93ba4f4830103ec8962e538e373.jpg","links":"https://www.huyahaha.com/"}]
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
             * pic : http://cdn.yingq.cc/web/upload/201902/15/d142d5cb450dac7d1127529b1515b9762f60613e.jpg
             * links : https://www.huyahaha.com/
             */

            private String pic;
            private String links;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLinks() {
                return links;
            }

            public void setLinks(String links) {
                this.links = links;
            }
        }
    }
}
