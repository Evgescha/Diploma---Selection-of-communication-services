package com.hescha.mobile_services.repository;

import com.hescha.mobile_services.entity.MobilePlan;
import com.hescha.mobile_services.entity.MobilePlanType;
import com.hescha.mobile_services.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MobilePlanRepository extends JpaRepository<MobilePlan, Long> {
    List<MobilePlan> findByNameLike(String name);

    List<MobilePlan> findByProvider(Provider provider);

    List<MobilePlan> findByType(MobilePlanType type);

    List<MobilePlan> findByProviderAndType(Provider provider,
                                           MobilePlanType type);
}