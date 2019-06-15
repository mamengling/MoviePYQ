package com.movie.mling.movieapp.mode.bean;

import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 16:22
 * $DESE$
 */
public class UserVo {


    /**
     * code : 0
     * message : success
     * data : {"id":"28","username":"15554509193","status":"0","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IjE1NTU0NTA5MTkzIiwiaWF0IjoxNTE5NTc0NDAwLCJuYmYiOjE1MTk1NzQ0MDB9.Avxccpj25XQbWglmkpLCUDPgZspeMszzoHiamjmuA-c","leixing":"2","nickname":"","avatar":"","gender":"0","onclick":"0","lng":"0.000000","lat":"0.000000","province":"","city":"","area":"","address":"","devicetype":"2","lastversion":"1.0.0","loginnum":"13","lasttime":"1519628700","channelid":""}
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
         * id : 28
         * username : 15554509193
         * status : 0
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IjE1NTU0NTA5MTkzIiwiaWF0IjoxNTE5NTc0NDAwLCJuYmYiOjE1MTk1NzQ0MDB9.Avxccpj25XQbWglmkpLCUDPgZspeMszzoHiamjmuA-c
         * leixing : 2
         * nickname :
         * avatar :
         * gender : 0
         * onclick : 0
         * lng : 0.000000
         * lat : 0.000000
         * province :
         * city :
         * area :
         * address :
         * devicetype : 2
         * lastversion : 1.0.0
         * loginnum : 13
         * lasttime : 1519628700
         * channelid :
         */

        private String username;
        private String status;
        private String token;
        private String leixing;
        private String nickname;
        private String tel;
        private String title;
        private String xuexiao;
        private String bieming;
        private String tizhong;
        private List<PhotoBean> album;
        private String intro;
        private String ext_weixin;
        private String ext_mobile;
        private String id;
        private String uid;
        private String shengri;
        private String shengri_age;
        private String avatar;
        private String gender;
        private String onclick;
        private String lng;
        private String lat;
        private String province;
        private String city;
        private String area;
        private String address;
        private String devicetype;
        private String lastversion;
        private String loginnum;
        private String lasttime;
        private String channelid;
        private String shengao;
        private String zuopin;
        private String zhiye;
        private String minzu;
        private String auth_realname;
        private String auth_text;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<PhotoBean> getAlbum() {
            return album;
        }

        public String getShengri_age() {
            return shengri_age;
        }

        public void setShengri_age(String shengri_age) {
            this.shengri_age = shengri_age;
        }

        public void setAlbum(List<PhotoBean> album) {
            this.album = album;
        }

        public String getTizhong() {
            return tizhong;
        }

        public String getExt_mobile() {
            return ext_mobile;
        }

        public void setExt_mobile(String ext_mobile) {
            this.ext_mobile = ext_mobile;
        }

        public void setTizhong(String tizhong) {
            this.tizhong = tizhong;
        }


        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getExt_weixin() {
            return ext_weixin;
        }

        public void setExt_weixin(String ext_weixin) {
            this.ext_weixin = ext_weixin;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getShengri() {
            return shengri;
        }

        public void setShengri(String shengri) {
            this.shengri = shengri;
        }

        public String getShengao() {
            return shengao;
        }

        public void setShengao(String shengao) {
            this.shengao = shengao;
        }

        public String getZuopin() {
            return zuopin;
        }

        public void setZuopin(String zuopin) {
            this.zuopin = zuopin;
        }

        public String getZhiye() {
            return zhiye;
        }

        public void setZhiye(String zhiye) {
            this.zhiye = zhiye;
        }

        public String getMinzu() {
            return minzu;
        }

        public void setMinzu(String minzu) {
            this.minzu = minzu;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getLeixing() {
            return leixing;
        }

        public void setLeixing(String leixing) {
            this.leixing = leixing;
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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getXuexiao() {
            return xuexiao;
        }

        public void setXuexiao(String xuexiao) {
            this.xuexiao = xuexiao;
        }

        public String getBieming() {
            return bieming;
        }

        public void setBieming(String bieming) {
            this.bieming = bieming;
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

        public String getOnclick() {
            return onclick;
        }

        public void setOnclick(String onclick) {
            this.onclick = onclick;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDevicetype() {
            return devicetype;
        }

        public void setDevicetype(String devicetype) {
            this.devicetype = devicetype;
        }

        public String getLastversion() {
            return lastversion;
        }

        public void setLastversion(String lastversion) {
            this.lastversion = lastversion;
        }

        public List<PhotoBean> getPhoto() {
            return album;
        }

        public void setPhoto(List<PhotoBean> album) {
            this.album = album;
        }

        public String getLoginnum() {
            return loginnum;
        }

        public void setLoginnum(String loginnum) {
            this.loginnum = loginnum;
        }

        public String getLasttime() {
            return lasttime;
        }

        public void setLasttime(String lasttime) {
            this.lasttime = lasttime;
        }

        public String getChannelid() {
            return channelid;
        }

        public void setChannelid(String channelid) {
            this.channelid = channelid;
        }

        public String getAuth_realname() {
            return auth_realname;
        }

        public void setAuth_realname(String auth_realname) {
            this.auth_realname = auth_realname;
        }

        public String getAuth_text() {
            return auth_text;
        }

        public void setAuth_text(String auth_text) {
            this.auth_text = auth_text;
        }
    }

    public static class PhotoBean{
        private String id;
        private String title;
        private String uid;
        private String pid;
        private String titlepic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getTitlepic() {
            return titlepic;
        }

        public void setTitlepic(String titlepic) {
            this.titlepic = titlepic;
        }


    }
}
