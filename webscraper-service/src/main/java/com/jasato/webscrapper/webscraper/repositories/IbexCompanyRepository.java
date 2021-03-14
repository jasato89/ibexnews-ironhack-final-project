package com.jasato.webscrapper.webscraper.repositories;

import com.jasato.webscrapper.webscraper.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface IbexCompanyRepository extends JpaRepository<IbexCompany, String> {
}
