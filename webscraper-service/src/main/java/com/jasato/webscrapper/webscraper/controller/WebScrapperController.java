package com.jasato.webscrapper.webscraper.controller;

import com.jasato.webscrapper.webscraper.controller.dtos.*;
import com.jasato.webscrapper.webscraper.models.*;
import com.jasato.webscrapper.webscraper.repositories.*;
import com.jasato.webscrapper.webscraper.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
public class WebScrapperController {
    @Autowired
    IbexCompanyRepository ibexCompanyRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsService newsService;

    @PostMapping("/add-company")
    @ResponseStatus(HttpStatus.OK)
    void addCompany(@RequestBody IbexCompany company) {
        ibexCompanyRepository.save(company);
    }

    @GetMapping("/get-news/company/{company}")
    List<NewsDTO> getNewsByCompany(@PathVariable(name = "company") String ibexCompany, @RequestParam(name = "offset", required = false)  Integer offset, @RequestParam(name = "limit", required = false) Integer limit) {

        return newsService.getNewsByCompany(ibexCompany, offset, limit);

    }

    @GetMapping("/get-news/all")
    List<NewsDTO> getAllNews(@RequestParam(name = "offset", required = false)  Integer offset, @RequestParam(name = "limit", required = false) Integer limit) {

        return newsService.getAllNews(offset, limit);
    }

    @GetMapping("/get-companies")
    List<IbexCompany> getAllCompanies() {
        return ibexCompanyRepository.findAll();
    }


}
