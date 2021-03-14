package com.jasato.webscrapper.webscraper.repositories;

import com.jasato.webscrapper.webscraper.enums.*;
import com.jasato.webscrapper.webscraper.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findByTitle(String title);
    List<News> findByIbexCompanyOrderByLocalDateTimeDesc(IbexCompany ibexCompany);
    List<News> findAllByOrderByLocalDateTimeDesc();
}
