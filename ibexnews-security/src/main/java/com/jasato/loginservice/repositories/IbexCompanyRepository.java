package com.jasato.loginservice.repositories;

import com.jasato.loginservice.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface IbexCompanyRepository extends JpaRepository<IbexCompany, String> {
    @Modifying
    @Query(value = "DELETE from ibex_company where name = :name and user_user_id = :user", nativeQuery = true)
    void deleteByNameAndUser(String name, Long user);

    Optional<IbexCompany> findByNameAndUser(String name, User user);

}
