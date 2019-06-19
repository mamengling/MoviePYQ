package com.movie.mling.movieapp.mode.bean;

import java.util.List;

public class ScreenListBean {
    private String nameTitle;
    private List<ScreenBean.labelBean> list;

    public String getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    public List<ScreenBean.labelBean> getList() {
        return list;
    }

    public void setList(List<ScreenBean.labelBean> list) {
        this.list = list;
    }
}
