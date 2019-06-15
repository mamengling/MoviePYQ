package com.movie.mling.movieapp.mode.bean;

import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/9 12:41
 * $DESE$
 */
public class MovieInfoBean {

    /**
     * code : 0
     * message : success
     * data : {"id":"449","uid":"0","lid":"222","lng":"116.538571","lat":"39.905569","title":"《破冰行动》","titlepic":"http://cdn.23so.cn/web/20180201/711164ab7a191a9b9bcb7f3d87407163.jpg","isgood":"1","fav":0,"newstime":"2018/02/01 22:26","onclick":"0","leixing":"1","leixing_text":"电视剧","ext":[["题　　材","精品公安国情大剧\r\n播出平台：央视一套"],["制作公司","公安部宣传局、广东省公安厅、北京市新闻出版广电局、\r\n北京爱奇艺科技有限公司、\r\n北京欢乐源泉影视传媒有限公司、\r\n北京京默影视传媒文化有限公司"],["出 品 人","龚宇"],["制 片 人","戴莹 刘燕军：总制片人\r\n徐蜜 傅敏 孙立敏 王云：制片人\r\n周笑 刘燕兵：执行制片人"],["拍摄地点","广东、香港、澳门、法国"],["拍摄周期","180天"],["选角团队","聚舟影视"],["演员统筹","周纪成：18857990040 （微信同步）"],["邮 　 箱","41387571@qq.com"],["筹备地址","北京市朝阳区高碑店东区B7-1 欢乐传媒"]],"photo":[{"id":"266","fid":"449","titlepic":"http://cdn.23so.cn/web/20180201/711164ab7a191a9b9bcb7f3d87407163.jpg","titlepicthumb":"","px":"0"}],"users":[{"title":"已定演员","remark":"保密"},{"title":"苏建国","remark":"苏建国：男，50岁左右，公安部禁毒局局长。"},{"title":"蔡永强","remark":"蔡永强：男，50岁左右，陆丰市公安局禁毒大队大队长。"},{"title":"左兰","remark":"左兰：女，40岁左右，公安部禁毒局处长。\u201c雷霆扫毒行动\u201d前线侦查组成员。"},{"title":"宋杨","remark":"宋杨：男，26岁左右，陆丰市公安局禁毒大队警员，李飞警院的同班同学。"},{"title":"林耀东","remark":"林耀东：男，50岁左右，陆丰市丰林镇塔寨村村支书。制毒团伙首领。"},{"title":"林耀华","remark":"林耀华：男，48岁左右，丰林镇塔寨村副支书，林耀东的弟弟。制毒团伙首领。"},{"title":"林灿","remark":"林灿：男，28岁左右，陆丰市丰林镇塔寨村村民，林耀华的儿子。制毒骨干。"},{"title":"林景文","remark":"林景文：男，30岁左右，景文物流公司董事长，林耀东的儿子。贩毒团伙成员。"},{"title":"林宗辉","remark":"林宗辉：男，47岁左右，陆丰市丰林镇房头之一，制毒骨干。"},{"title":"林胜武","remark":"林胜武：男，28岁左右，陆丰市丰林镇塔寨村村民，制毒骨干。"},{"title":"林伟华","remark":"林伟华：男，24岁左右，林胜武的弟弟，陆丰市丰林镇塔寨村村民，制毒成员。"},{"title":"包星","remark":"包星：男，30岁左右，广东河源人，原陈珂的男朋友，小毒贩。"},{"title":"蔡军","remark":"蔡军：男，26岁左右，公安局丰林镇派出所干警。李飞同学。制毒团伙保护伞。"},{"title":"陈光荣","remark":"陈光荣：男，45岁左右，公安局丰西镇派出所所长。林耀东制毒团伙的保护伞。"},{"title":"蔡启荣","remark":"蔡启荣：男，66岁左右，汕尾市原政协副主席。林耀东制毒团伙的保护伞。"},{"title":"林可欣","remark":"林可欣：女，25岁左右，林宗辉的女儿，蔡军的女朋友。"},{"title":"丁力伟","remark":"丁力伟：男，35岁左右，公安局交警大队副大队长，林耀东制毒团伙的保护伞。"},{"title":"林凯","remark":"林凯：男，30岁左右，林宗辉的儿子，陆丰市丰林镇塔寨村人，制毒团伙成员。"},{"title":"刘浩宇","remark":"刘浩宇：男，60岁左右，香港浩宇集团董事长，国际贩毒组织首领。"},{"title":"何瑞龙","remark":"何瑞龙：男，50岁左右，某矿业公司总栽，张敏慧前男友，国际贩毒组织成员。"},{"title":"张敏慧","remark":"张敏慧：女，48岁左右，香港浩宇集团账务总监，国际贩毒组织成员。"},{"title":"其他角色要求","remark":"其他角色要求，30岁\u201460岁男性角色居多，表演经验丰富，有良好合作态度，欢迎各大经纪公司，经纪人及个体演员来电质询踊跃推荐。"}]}
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
         * id : 449
         * uid : 0
         * lid : 222
         * lng : 116.538571
         * lat : 39.905569
         * title : 《破冰行动》
         * titlepic : http://cdn.23so.cn/web/20180201/711164ab7a191a9b9bcb7f3d87407163.jpg
         * isgood : 1
         * fav : 0
         * newstime : 2018/02/01 22:26
         * onclick : 0
         * leixing : 1
         * leixing_text : 电视剧
         * ext : [["题　　材","精品公安国情大剧\r\n播出平台：央视一套"],["制作公司","公安部宣传局、广东省公安厅、北京市新闻出版广电局、\r\n北京爱奇艺科技有限公司、\r\n北京欢乐源泉影视传媒有限公司、\r\n北京京默影视传媒文化有限公司"],["出 品 人","龚宇"],["制 片 人","戴莹 刘燕军：总制片人\r\n徐蜜 傅敏 孙立敏 王云：制片人\r\n周笑 刘燕兵：执行制片人"],["拍摄地点","广东、香港、澳门、法国"],["拍摄周期","180天"],["选角团队","聚舟影视"],["演员统筹","周纪成：18857990040 （微信同步）"],["邮 　 箱","41387571@qq.com"],["筹备地址","北京市朝阳区高碑店东区B7-1 欢乐传媒"]]
         * photo : [{"id":"266","fid":"449","titlepic":"http://cdn.23so.cn/web/20180201/711164ab7a191a9b9bcb7f3d87407163.jpg","titlepicthumb":"","px":"0"}]
         * users : [{"title":"已定演员","remark":"保密"},{"title":"苏建国","remark":"苏建国：男，50岁左右，公安部禁毒局局长。"},{"title":"蔡永强","remark":"蔡永强：男，50岁左右，陆丰市公安局禁毒大队大队长。"},{"title":"左兰","remark":"左兰：女，40岁左右，公安部禁毒局处长。\u201c雷霆扫毒行动\u201d前线侦查组成员。"},{"title":"宋杨","remark":"宋杨：男，26岁左右，陆丰市公安局禁毒大队警员，李飞警院的同班同学。"},{"title":"林耀东","remark":"林耀东：男，50岁左右，陆丰市丰林镇塔寨村村支书。制毒团伙首领。"},{"title":"林耀华","remark":"林耀华：男，48岁左右，丰林镇塔寨村副支书，林耀东的弟弟。制毒团伙首领。"},{"title":"林灿","remark":"林灿：男，28岁左右，陆丰市丰林镇塔寨村村民，林耀华的儿子。制毒骨干。"},{"title":"林景文","remark":"林景文：男，30岁左右，景文物流公司董事长，林耀东的儿子。贩毒团伙成员。"},{"title":"林宗辉","remark":"林宗辉：男，47岁左右，陆丰市丰林镇房头之一，制毒骨干。"},{"title":"林胜武","remark":"林胜武：男，28岁左右，陆丰市丰林镇塔寨村村民，制毒骨干。"},{"title":"林伟华","remark":"林伟华：男，24岁左右，林胜武的弟弟，陆丰市丰林镇塔寨村村民，制毒成员。"},{"title":"包星","remark":"包星：男，30岁左右，广东河源人，原陈珂的男朋友，小毒贩。"},{"title":"蔡军","remark":"蔡军：男，26岁左右，公安局丰林镇派出所干警。李飞同学。制毒团伙保护伞。"},{"title":"陈光荣","remark":"陈光荣：男，45岁左右，公安局丰西镇派出所所长。林耀东制毒团伙的保护伞。"},{"title":"蔡启荣","remark":"蔡启荣：男，66岁左右，汕尾市原政协副主席。林耀东制毒团伙的保护伞。"},{"title":"林可欣","remark":"林可欣：女，25岁左右，林宗辉的女儿，蔡军的女朋友。"},{"title":"丁力伟","remark":"丁力伟：男，35岁左右，公安局交警大队副大队长，林耀东制毒团伙的保护伞。"},{"title":"林凯","remark":"林凯：男，30岁左右，林宗辉的儿子，陆丰市丰林镇塔寨村人，制毒团伙成员。"},{"title":"刘浩宇","remark":"刘浩宇：男，60岁左右，香港浩宇集团董事长，国际贩毒组织首领。"},{"title":"何瑞龙","remark":"何瑞龙：男，50岁左右，某矿业公司总栽，张敏慧前男友，国际贩毒组织成员。"},{"title":"张敏慧","remark":"张敏慧：女，48岁左右，香港浩宇集团账务总监，国际贩毒组织成员。"},{"title":"其他角色要求","remark":"其他角色要求，30岁\u201460岁男性角色居多，表演经验丰富，有良好合作态度，欢迎各大经纪公司，经纪人及个体演员来电质询踊跃推荐。"}]
         */

        private String id;
        private String uid;
        private String lid;
        private String lng;
        private String lat;
        private String title;
        private String city;
        private String lname;
        private String titlepic;
        private String isgood;
        private int fav;
        private int pao;
        private int dig;
        private String newstime;
        private String onclick;
        private String leixing;
        private String leixing_text;
        private String user_mark;
        private List<List<String>> ext;
        private List<PhotoBean> photo;
        private List<UsersBean> users;

        public String getId() {
            return id;
        }

        public String getUser_mark() {
            return user_mark;
        }

        public void setUser_mark(String user_mark) {
            this.user_mark = user_mark;
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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
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

        public int getFav() {
            return fav;
        }

        public void setFav(int fav) {
            this.fav = fav;
        }

        public int getPao() {
            return pao;
        }

        public void setPao(int pao) {
            this.pao = pao;
        }

        public int getDig() {
            return dig;
        }

        public void setDig(int dig) {
            this.dig = dig;
        }

        public String getNewstime() {
            return newstime;
        }

        public void setNewstime(String newstime) {
            this.newstime = newstime;
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

        public List<List<String>> getExt() {
            return ext;
        }

        public void setExt(List<List<String>> ext) {
            this.ext = ext;
        }

        public List<PhotoBean> getPhoto() {
            return photo;
        }

        public void setPhoto(List<PhotoBean> photo) {
            this.photo = photo;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class PhotoBean {
            /**
             * id : 266
             * fid : 449
             * titlepic : http://cdn.23so.cn/web/20180201/711164ab7a191a9b9bcb7f3d87407163.jpg
             * titlepicthumb :
             * px : 0
             */

            private String id;
            private String fid;
            private String titlepic;
            private String titlepicthumb;
            private String filmadid;
            private String filmadpic;
            private String filmadtype;
            private String px;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getTitlepic() {
                return titlepic;
            }

            public void setTitlepic(String titlepic) {
                this.titlepic = titlepic;
            }

            public String getTitlepicthumb() {
                return titlepicthumb;
            }

            public void setTitlepicthumb(String titlepicthumb) {
                this.titlepicthumb = titlepicthumb;
            }

            public String getPx() {
                return px;
            }

            public void setPx(String px) {
                this.px = px;
            }

            public String getFilmadid() {
                return filmadid;
            }

            public void setFilmadid(String filmadid) {
                this.filmadid = filmadid;
            }

            public String getFilmadpic() {
                return filmadpic;
            }

            public void setFilmadpic(String filmadpic) {
                this.filmadpic = filmadpic;
            }

            public String getFilmadtype() {
                return filmadtype;
            }

            public void setFilmadtype(String filmadtype) {
                this.filmadtype = filmadtype;
            }
        }

        public static class UsersBean {
            /**
             * title : 已定演员
             * remark : 保密
             */

            private String title;
            private String remark;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
