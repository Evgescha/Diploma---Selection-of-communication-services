package com.hescha.mobile_services.repository;

import com.hescha.mobile_services.entity.MobilePlanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MobilePlanTypeRepository extends JpaRepository<MobilePlanType, Long> {
    List<MobilePlanType> findByNameLike(String name);
}