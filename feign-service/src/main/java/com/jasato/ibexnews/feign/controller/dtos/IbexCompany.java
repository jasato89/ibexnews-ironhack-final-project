package com.jasato.ibexnews.feign.controller.dtos;

import java.util.*;

public class IbexCompany {
    private String queryName;
    private String symbol;
    private String productionName;
    private String cincoDiasTagName;
    private String elConfidencialTagName;
    private String elEconomistaTagName;


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
