package com.jasato.loginservice.repositories;

import com.jasato.loginservice.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
