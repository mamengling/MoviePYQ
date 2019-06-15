package com.movie.mling.movieapp.utils.widget;

import java.io.Serializable;

/**
 * @author zengtao 2015年5月20日下午7:18:14
 */
public class Pickers implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cancelReason;
    private String id;

    public String getShowConetnt() {
        return cancelReason;
    }

    public String getShowId() {
        return id;
    }

    public Pickers(String showConetnt, String showId) {
        super();
        this.cancelReason = showConetnt;
        this.id = showId;
    }

    public Pickers() {
        super();
    }

}
