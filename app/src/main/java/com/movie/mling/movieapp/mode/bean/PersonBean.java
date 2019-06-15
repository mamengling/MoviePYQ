package com.movie.mling.movieapp.mode.bean;

import java.util.List;

/**
 * Created by MLing on 2018/4/17 0017.
 */

public class PersonBean {

    /**
     * code : 0
     * message : success
     * data : [{"id":"175","status":"0","username":"15562679693","nickname":"","avatar":"","gender":"0","lng":"117.072716","lat":"36.636471","zhiye":"","distance":21,"lasttime":"4小时前 10:41"},{"id":"176","status":"1","username":"15554509193","nickname":"gggg","avatar":"http://cdn.23so.cn/upload/201804/17/0f86fe9de1c7cdc80aeb11979e962333a44004971523928948.png!circle","gender":"2","lng":"117.072594","lat":"36.636604","zhiye":"","distance":22,"lasttime":"4小时前 09:55"}]
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
         * id : 175
         * status : 0
         * username : 15562679693
         * nickname :
         * avatar :
         * gender : 0
         * lng : 117.072716
         * lat : 36.636471
         * zhiye :
         * distance : 21
         * lasttime : 4小时前 10:41
         */

        private String id;
        private String status;
        private String username;
        private String nickname;
        private String avatar;
        private String gender;
        private String lng;
        private String lat;
        private String zhiye;
        private double distance;
        private String lasttime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getZhiye() {
            return zhiye;
        }

        public void setZhiye(String zhiye) {
            this.zhiye = zhiye;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getLasttime() {
            return lasttime;
        }

        public void setLasttime(String lasttime) {
            this.lasttime = lasttime;
        }
    }
}
