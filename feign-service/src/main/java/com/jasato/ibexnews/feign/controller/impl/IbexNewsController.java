package com.jasato.ibexnews.feign.controller.impl;

import com.jasato.ibexnews.feign.client.*;
import com.jasato.ibexnews.feign.controller.dtos.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cloud.client.discovery.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
public class IbexNewsController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebscrapperClient webscrapperClient;

    @Autowired
    private UsersClient worldClient;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/get-news/all")
    List<NewsDTO> getAllNews(@RequestParam(name = "offset", required = false) Integer offset, @RequestParam(name = "limit", required = false) Integer limit) {
        logger.info("Getting news through feign client");
        return webscrapperClient.getAllNews(offset, limit);
    }


    @GetMapping("/get-companies")
    List<IbexCompany> getAllCompanies() {
        return webscrapperClient.getAllCompanies();
    }

    @GetMapping("/get-news/company/{company}")
    List<NewsDTO> getNewsByCompany(@PathVariable(name = "company") String ibexCompany, @RequestParam(name = "offset", required = false) Integer offset, @RequestParam(name = "limit", required = false) Integer limit) {
        return webscrapperClient.getNewsByCompany(ibexCompany, offset, limit);
    }


}
