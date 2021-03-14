package com.jasato.ibexnews.feign.controller.dtos;


import com.jasato.ibexnews.feign.enums.*;

import java.time.*;


public class News {

    private Long id;
    private LocalDateTime localDateTime;
    private String title;
    private String url;
    private MediaOutlet mediaOutlet;
    private IbexCompany ibexCompany;

    public News(LocalDateTime localDateTime, String title, String url, MediaOutlet mediaOutlet) {
        this.localDateTime = localDateTime;
        this.title = title;
        this.url = url;
        this.mediaOutlet = mediaOutlet;

    }

    public News(LocalDateTime localDateTime, String title, String url, MediaOutlet mediaOutlet, IbexCompany ibexCompany) {
        this.localDateTime = localDateTime;
        this.title = title;
        this.url = url;
        this.mediaOutlet = mediaOutlet;
        this.ibexCompany = ibexCompany;
    }

    public News() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MediaOutlet getMediaOutlet() {
        return mediaOutlet;
    }

    public void setMediaOutlet(MediaOutlet mediaOutlet) {
        this.mediaOutlet = mediaOutlet;
    }

    public IbexCompany getIbexCompany() {
        return ibexCompany;
    }

    public void setIbexCompany(IbexCompany ibexCompany) {
        this.ibexCompany = ibexCompany;
    }
}
