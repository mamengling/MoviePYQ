package com.movie.mling.movieapp.mode.bean;

import java.util.List;

public class ScreenBean {
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
        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }
    }

    public static class ListBean {
        private List<labelBean> techang;
        private List<labelBean> fengge;
        private List<labelBean> leixing;
        private List<labelBean> shencai_male;
        private List<labelBean> shencai_female;
        private List<labelBean> shijue_male;
        private List<labelBean> shijue_female;
        private List<labelBean> dingwei_male;
        private List<labelBean> dingwei_female;
        private List<labelBean> waiji;
        private List<labelBean> waiyu;

        public List<labelBean> getTechang() {
            return techang;
        }

        public void setTechang(List<labelBean> techang) {
            this.techang = techang;
        }

        public List<labelBean> getFengge() {
            return fengge;
        }

        public void setFengge(List<labelBean> fengge) {
            this.fengge = fengge;
        }

        public List<labelBean> getLeixing() {
            return leixing;
        }

        public void setLeixing(List<labelBean> leixing) {
            this.leixing = leixing;
        }

        public List<labelBean> getShencai_male() {
            return shencai_male;
        }

        public void setShencai_male(List<labelBean> shencai_male) {
            this.shencai_male = shencai_male;
        }

        public List<labelBean> getShencai_female() {
            return shencai_female;
        }

        public void setShencai_female(List<labelBean> shencai_female) {
            this.shencai_female = shencai_female;
        }

        public List<labelBean> getShijue_male() {
            return shijue_male;
        }

        public void setShijue_male(List<labelBean> shijue_male) {
            this.shijue_male = shijue_male;
        }

        public List<labelBean> getShijue_female() {
            return shijue_female;
        }

        public void setShijue_female(List<labelBean> shijue_female) {
            this.shijue_female = shijue_female;
        }

        public List<labelBean> getDingwei_male() {
            return dingwei_male;
        }

        public void setDingwei_male(List<labelBean> dingwei_male) {
            this.dingwei_male = dingwei_male;
        }

        public List<labelBean> getDingwei_female() {
            return dingwei_female;
        }

        public void setDingwei_female(List<labelBean> dingwei_female) {
            this.dingwei_female = dingwei_female;
        }

        public List<labelBean> getWaiji() {
            return waiji;
        }

        public void setWaiji(List<labelBean> waiji) {
            this.waiji = waiji;
        }

        public List<labelBean> getWaiyu() {
            return waiyu;
        }

        public void setWaiyu(List<labelBean> waiyu) {
            this.waiyu = waiyu;
        }
    }

    public static class labelBean {
        private int viewType;
        private int sizeView;
        private String title;
        private String id;
        private String pid;
        private String name;
        private String gender;
        private String px;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPx() {
            return px;
        }

        public void setPx(String px) {
            this.px = px;
        }

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSizeView() {
            return sizeView;
        }

        public void setSizeView(int sizeView) {
            this.sizeView = sizeView;
        }
    }
}
