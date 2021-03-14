package com.jasato.webscrapper.webscraper.services;

import com.jasato.webscrapper.webscraper.controller.dtos.*;
import com.jasato.webscrapper.webscraper.models.*;
import com.jasato.webscrapper.webscraper.repositories.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class NewsService {
    @Autowired
    IbexCompanyRepository ibexCompanyRepository;
    @Autowired
    NewsRepository newsRepository;
    Logger logger = LoggerFactory.getLogger(NewsService.class);


    public List<NewsDTO> getNewsByCompany(String ibexCompany, Integer offset, Integer limit) {
        logger.info("Requested info for company: " + ibexCompany);
        offset = offset == null ? 0 : offset;
        limit = limit == null ? 10 : limit;
        if (ibexCompanyRepository.findById(ibexCompany.toUpperCase()).isPresent()) {
            IbexCompany company = ibexCompanyRepository.findById(ibexCompany.toUpperCase()).get();
            List<NewsDTO> news = new ArrayList<>();

            for (int i = offset; i < offset + limit; i++) {
                if (i < newsRepository.findByIbexCompanyOrderByLocalDateTimeDesc(company).size()) {
                    News article = newsRepository.findByIbexCompanyOrderByLocalDateTimeDesc(company).get(i);
                    news.add(new NewsDTO(
                            article.getId(),
                            article.getLocalDateTime(),
                            article.getTitle(), article.getUrl(),
                            article.getMediaOutlet(),
                            article.getIbexCompany().getQueryName()
                            ));
                }

            }

            return news;

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, we couldn't find the company you are looking for");
        }
    }

    public List<NewsDTO> getAllNews(Integer offset, Integer limit) {

        logger.info("Requested info for  all companies");

        offset = offset == null ? 0 : offset;
        limit = limit == null ? 10 : limit;
        List<NewsDTO> news = new ArrayList<>();

        for (int i = offset; i < offset + limit; i++) {
            if (i < newsRepository.findAll().size()) {
                News article = newsRepository.findAllByOrderByLocalDateTimeDesc().get(i);
                news.add(new NewsDTO(article.getId(),
                        article.getLocalDateTime(),
                        article.getTitle(),
                        article.getUrl(),
                        article.getMediaOutlet(),
                        article.getIbexCompany().getQueryName()
                ));
            }

        }
        return news;
    }
}
