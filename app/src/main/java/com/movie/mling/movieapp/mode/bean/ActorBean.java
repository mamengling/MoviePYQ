package com.movie.mling.movieapp.mode.bean;

import java.util.List;

/**
 * Created by MLing on 2018/4/27 0027.
 */

public class ActorBean {

    /**
     * code : 0
     * message : success
     * data : {"total":13,"list":[{"id":"121","xingming":"杨欣","shengao":"168","tizhong":"50","video":"http://player.youku.com/embed/XMjgyMzYwNDQw'","picurl":"http://cdn.23so.cn/web/upload/201804/24/77c6bbaabf94ccf012b2571af2024c7ab5eda568.jpg","isfav":0},{"id":"99","xingming":"秦焰","shengao":"180","tizhong":"75","video":"http://player.youku.com/embed/XMjk5NjY1MzY0OA=='","picurl":"http://cdn.23so.cn/web/upload/201804/12/9eb466c873a72beed84b686665c2e60e9bccff7a.jpg","isfav":0},{"id":"125","xingming":"台亚雯 ","shengao":"167","tizhong":"48","video":"","picurl":"http://cdn.23so.cn/web/upload/201804/24/a2f050960ea411d28f5e6fb91d6bdf2f37f39e4d.jpg","isfav":0},{"id":"128","xingming":"王圻文宣","shengao":"168","tizhong":"48","video":"http://player.youku.com/embed/XMzU2NTk3ODQwNA=='","picurl":"http://cdn.23so.cn/web/upload/201804/26/e52885425f169d2c553f5f2434a22b247813c0d8.jpg","isfav":0},{"id":"5","xingming":"孙茜","shengao":"168","tizhong":"48","video":"http://player.youku.com/embed/XMzU0MTc3NjExMg=='","picurl":"http://cdn.23so.cn/web/upload/201804/12/aabf0bd631e933eda1d2950a2c616a6dc64259fc.jpg","isfav":0},{"id":"78","xingming":"杨子骅","shengao":"177","tizhong":"75","video":"","picurl":"http://cdn.23so.cn/web/upload/201804/12/bea6a85569fe16266ec2613fb640e2f29b41fce5.jpg","isfav":0},{"id":"115","xingming":"戴开炜","shengao":"185","tizhong":"70","video":"","picurl":"http://cdn.23so.cn/web/upload/201804/13/ab6af3c833d7e40aa673f7fd03b8bb1122283814.jpg","isfav":0},{"id":"126","xingming":"袁满","shengao":"183","tizhong":"75","video":"","picurl":"http://cdn.23so.cn/web/upload/201804/24/0643535140292baf987ea0ecda0d8f007b076aad.jpg","isfav":0},{"id":"129","xingming":"刘金","shengao":"165","tizhong":"95","video":"http://player.youku.com/embed/XMzU2NjEyNjA3Ng=='","picurl":"http://cdn.23so.cn/web/upload/201804/26/c79315ec0648ae593f49928f9d8e0506785732d7.jpg","isfav":0},{"id":"52","xingming":"郝柏杰","shengao":"180","tizhong":"70","video":"","picurl":"http://cdn.23so.cn/web/upload/201804/12/8e1aab9362207e1316cda57f3849bb22a8b670ed.jpg","isfav":0}]}
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
         * total : 13
         * list : [{"id":"121","xingming":"杨欣","shengao":"168","tizhong":"50","video":"http://player.youku.com/embed/XMjgyMzYwNDQw'","picurl":"http://cdn.23so.cn/web/upload/201804/24/77c6bbaabf94ccf012b2571af2024c7ab5eda568.jpg","isfav":0},{"id":"99","xingming":"秦焰","shengao":"180","tizhong":"75","video":"http://player.youku.com/embed/XMjk5NjY1MzY0OA=='","picurl":"http://cdn.23so.cn/web/upload/201804/12/9eb466c873a72beed84b686665c2e60e9bccff7a.jpg","isfav":0},{"id":"125","xingming":"台亚雯 ","shengao":"167","tizhong":"48","video":"","picurl":"http://cdn.23so.cn/web/upload/201804/24/a2f050960ea411d28f5e6fb91d6bdf2f37f39e4d.jpg","isfav":0},{"id":"128","xingming":"王圻文宣","shengao":"168","tizhong":"48","video":"http://player.youku.com/embed/XMzU2NTk3ODQwNA=='","picurl":"http://cdn.23so.cn/web/upload/201804/26/e52885425f169d2c553f5f2434a22b247813c0d8.jpg","isfav":0},{"id":"5","xingming":"孙茜","shengao":"168","tizhong":"48","video":"http://player.youku.com/embed/XMzU0MTc3NjExMg=='","picurl":"http://cdn.23so.cn/web/upload/201804/12/aabf0bd631e933eda1d2950a2c616a6dc64259fc.jpg","isfav":0},{"id":"78","xingming":"杨子骅","shengao":"177","tizhong":"75","video":"","picurl":"http://cdn.23so.cn/web/upload/201804/12/bea6a85569fe16266ec2613fb640e2f29b41fce5.jpg","isfav":0},{"id":"115","xingming":"戴开炜","shengao":"185","tizhong":"70","video":"","picurl":"http://cdn.23so.cn/web/upload/201804/13/ab6af3c833d7e40aa673f7fd03b8bb1122283814.jpg","isfav":0},{"id":"126","xingming":"袁满","shengao":"183","tizhong":"75","video":"","picurl":"http://cdn.23so.cn/web/upload/201804/24/0643535140292baf987ea0ecda0d8f007b076aad.jpg","isfav":0},{"id":"129","xingming":"刘金","shengao":"165","tizhong":"95","video":"http://player.youku.com/embed/XMzU2NjEyNjA3Ng=='","picurl":"http://cdn.23so.cn/web/upload/201804/26/c79315ec0648ae593f49928f9d8e0506785732d7.jpg","isfav":0},{"id":"52","xingming":"郝柏杰","shengao":"180","tizhong":"70","video":"","picurl":"http://cdn.23so.cn/web/upload/201804/12/8e1aab9362207e1316cda57f3849bb22a8b670ed.jpg","isfav":0}]
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
             * id : 121
             * xingming : 杨欣
             * shengao : 168
             * tizhong : 50
             * video : http://player.youku.com/embed/XMjgyMzYwNDQw'
             * picurl : http://cdn.23so.cn/web/upload/201804/24/77c6bbaabf94ccf012b2571af2024c7ab5eda568.jpg
             * isfav : 0
             */
            private int id;
            private int uid;
            private int pid;
            private String xingming;
            private String shengao;
            private String tizhong;
            private String video;
            private String regdate;
            private String picurl;
            private String avatar;
            private int isfav;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getRegdate() {
                return regdate;
            }

            public void setRegdate(String regdate) {
                this.regdate = regdate;
            }

            public String getXingming() {
                return xingming;
            }

            public void setXingming(String xingming) {
                this.xingming = xingming;
            }

            public String getShengao() {
                return shengao;
            }

            public void setShengao(String shengao) {
                this.shengao = shengao;
            }

            public String getTizhong() {
                return tizhong;
            }

            public void setTizhong(String tizhong) {
                this.tizhong = tizhong;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public int getIsfav() {
                return isfav;
            }

            public void setIsfav(int isfav) {
                this.isfav = isfav;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
