package com.hescha.mobile_services.repository;

import com.hescha.mobile_services.entity.MobileService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MobileServiceRepository extends JpaRepository<MobileService,
        Long> {
    List<MobileService> findByNameLike(String name);
}