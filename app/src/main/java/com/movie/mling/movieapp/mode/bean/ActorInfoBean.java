package com.movie.mling.movieapp.mode.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MLing on 2018/5/11 0011.
 */

public class ActorInfoBean {

    /**
     * code : 0
     * message : success
     * data : {"id":"220","xingming":"金巧巧","xingbie":"女","nianling":"43","shengao":"167","tizhong":"48","guoji":"","gongsi":"有","zhuye":"498","techang":"唱歌,跳舞,外语,","tags":"妩媚,性感,知性,御姐,清甜,阳光,","pdf":"http://cdn.23so.cn/web/upload/201805/04/119ceb900e953f6a2a4eb8ae52066a4a421fe925.pdf","video":"http://player.youku.com/embed/XMzU2MTM3Nzk2MA=='","isgood":"1","photos":[{"picurl":"http://cdn.23so.cn/web/upload/201805/04/741f6d457bf71ec923afcbf6c33370fb6b76e115.jpg"}],"isfav":0}
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
         * id : 220
         * xingming : 金巧巧
         * xingbie : 女
         * nianling : 43
         * shengao : 167
         * tizhong : 48
         * guoji :
         * gongsi : 有
         * zhuye : 498
         * techang : 唱歌,跳舞,外语,
         * tags : 妩媚,性感,知性,御姐,清甜,阳光,
         * pdf : http://cdn.23so.cn/web/upload/201805/04/119ceb900e953f6a2a4eb8ae52066a4a421fe925.pdf
         * video : http://player.youku.com/embed/XMzU2MTM3Nzk2MA=='
         * isgood : 1
         * photos : [{"picurl":"http://cdn.23so.cn/web/upload/201805/04/741f6d457bf71ec923afcbf6c33370fb6b76e115.jpg"}]
         * isfav : 0
         */

        private String id;
        private String xingming;
        private String xingbie;
        private String nianling;
        private String shengao;
        private String tizhong;
        private String guoji;
        private String gongsi;
        private String zhuye;
        private String techang;
        private String tags;
        private String pdf;
        private String video;
        private String isgood;
        private String isfav;
        private List<PhotosBean> photos;
        private ArrayList<AlbumBean> albumlist;
        private ArrayList<VideoBean> videolist;

        public ArrayList<AlbumBean> getAlbumlist() {
            return albumlist;
        }

        public void setAlbumlist(ArrayList<AlbumBean> albumlist) {
            this.albumlist = albumlist;
        }

        public ArrayList<VideoBean> getVideolist() {
            return videolist;
        }

        public void setVideolist(ArrayList<VideoBean> videolist) {
            this.videolist = videolist;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getXingming() {
            return xingming;
        }

        public void setXingming(String xingming) {
            this.xingming = xingming;
        }

        public String getXingbie() {
            return xingbie;
        }

        public void setXingbie(String xingbie) {
            this.xingbie = xingbie;
        }

        public String getNianling() {
            return nianling;
        }

        public void setNianling(String nianling) {
            this.nianling = nianling;
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

        public String getGuoji() {
            return guoji;
        }

        public void setGuoji(String guoji) {
            this.guoji = guoji;
        }

        public String getGongsi() {
            return gongsi;
        }

        public void setGongsi(String gongsi) {
            this.gongsi = gongsi;
        }

        public String getZhuye() {
            return zhuye;
        }

        public void setZhuye(String zhuye) {
            this.zhuye = zhuye;
        }

        public String getTechang() {
            return techang;
        }

        public void setTechang(String techang) {
            this.techang = techang;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getPdf() {
            return pdf;
        }

        public void setPdf(String pdf) {
            this.pdf = pdf;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getIsgood() {
            return isgood;
        }

        public void setIsgood(String isgood) {
            this.isgood = isgood;
        }

        public String getIsfav() {
            return isfav;
        }

        public void setIsfav(String isfav) {
            this.isfav = isfav;
        }

        public List<PhotosBean> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosBean> photos) {
            this.photos = photos;
        }

        public static class PhotosBean {
            /**
             * picurl : http://cdn.23so.cn/web/upload/201805/04/741f6d457bf71ec923afcbf6c33370fb6b76e115.jpg
             */

            private String picurl;

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }
        }

        public static class AlbumBean implements Parcelable{
            /**
             * picurl : http://cdn.23so.cn/web/upload/201805/04/741f6d457bf71ec923afcbf6c33370fb6b76e115.jpg
             */

            private String id;
            private String picurl;
            private String pid;
            private String px;
            private String picname;

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
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

            public String getPx() {
                return px;
            }

            public void setPx(String px) {
                this.px = px;
            }

            public String getPicname() {
                return picname;
            }

            public void setPicname(String picname) {
                this.picname = picname;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.picurl);
                dest.writeString(this.pid);
                dest.writeString(this.px);
                dest.writeString(this.picname);
            }

            public AlbumBean() {
            }

            protected AlbumBean(Parcel in) {
                this.id = in.readString();
                this.picurl = in.readString();
                this.pid = in.readString();
                this.px = in.readString();
                this.picname = in.readString();
            }

            public static final Creator<AlbumBean> CREATOR = new Creator<AlbumBean>() {
                @Override
                public AlbumBean createFromParcel(Parcel source) {
                    return new AlbumBean(source);
                }

                @Override
                public AlbumBean[] newArray(int size) {
                    return new AlbumBean[size];
                }
            };
        }

        public static class VideoBean implements Parcelable{
            /**
             * picurl : http://cdn.23so.cn/web/upload/201805/04/741f6d457bf71ec923afcbf6c33370fb6b76e115.jpg
             */

            private String videoname;
            private String videopic;
            private String id;
            private String videourl;
            private String pid;

            public String getVideoname() {
                return videoname;
            }

            public void setVideoname(String videoname) {
                this.videoname = videoname;
            }

            public String getVideopic() {
                return videopic;
            }

            public void setVideopic(String videopic) {
                this.videopic = videopic;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getVideourl() {
                return videourl;
            }

            public void setVideourl(String videourl) {
                this.videourl = videourl;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.videoname);
                dest.writeString(this.videopic);
                dest.writeString(this.id);
                dest.writeString(this.videourl);
                dest.writeString(this.pid);
            }

            public VideoBean() {
            }

            protected VideoBean(Parcel in) {
                this.videoname = in.readString();
                this.videopic = in.readString();
                this.id = in.readString();
                this.videourl = in.readString();
                this.pid = in.readString();
            }

            public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
                @Override
                public VideoBean createFromParcel(Parcel source) {
                    return new VideoBean(source);
                }

                @Override
                public VideoBean[] newArray(int size) {
                    return new VideoBean[size];
                }
            };
        }
    }
}
