package com.jasato.ibexnews.feign.client;

import com.jasato.ibexnews.feign.controller.dtos.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "webscrapper", url = "https://ibexnews-webscrapper.herokuapp.com/")
public interface WebscrapperClient {
    @GetMapping("/get-news/all")
    List<NewsDTO> getAllNews(@RequestParam(name = "offset", required = false) Integer offset, @RequestParam(name = "limit", required = false) Integer limit);

    @GetMapping("/get-companies")
    List<IbexCompany> getAllCompanies();

    @GetMapping("/get-news/company/{company}")
    List<NewsDTO> getNewsByCompany(@PathVariable(name = "company") String ibexCompany, @RequestParam(name = "offset", required = false) Integer offset, @RequestParam(name = "limit", required = false) Integer limit);

}
