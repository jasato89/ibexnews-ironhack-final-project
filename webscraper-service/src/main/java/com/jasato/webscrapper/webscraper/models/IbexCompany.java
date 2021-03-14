package com.jasato.webscrapper.webscraper.models;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

@Entity
public class IbexCompany {
    @Id
    private String queryName;
    @NotNull
    private String symbol;
    private String productionName;
    private String cincoDiasTagName;
    private String elConfidencialTagName;
    private String elEconomistaTagName;
    @OneToMany( mappedBy = "ibexCompany")
    @JsonIgnore
    private List<News> news;

    public IbexCompany() {
    }

    public IbexCompany(String queryName, String symbol, String name, String cincoDiasTagName, String elConfidencialTagName, String elEconomistaTagName) {
        this.queryName = queryName;
        this.symbol = symbol;
        this.productionName = name;
        this.cincoDiasTagName = cincoDiasTagName;
        this.elConfidencialTagName = elConfidencialTagName;
        this.elEconomistaTagName = elEconomistaTagName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String name) {
        this.productionName = name;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public String getCincoDiasTagName() {
        return cincoDiasTagName;
    }

    public void setCincoDiasTagName(String cincoDiasTagName) {
        this.cincoDiasTagName = cincoDiasTagName;
    }

    public String getElConfidencialTagName() {
        return elConfidencialTagName;
    }

    public void setElConfidencialTagName(String elConfidencialTagName) {
        this.elConfidencialTagName = elConfidencialTagName;
    }

    public String getElEconomistaTagName() {
        return elEconomistaTagName;
    }

    public void setElEconomistaTagName(String elEconomistaTagName) {
        this.elEconomistaTagName = elEconomistaTagName;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }
}
