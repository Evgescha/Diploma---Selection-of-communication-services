package com.hescha.mobile_services.repository;

import com.hescha.mobile_services.entity.MobilePlan;
import com.hescha.mobile_services.entity.MobileService;
import com.hescha.mobile_services.entity.MobileServiceVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MobileServiceVariantRepository extends JpaRepository<MobileServiceVariant,
        Long> {
    List<MobileServiceVariant> findByService(MobileService service);

    List<MobileServiceVariant> findByServiceAndPlan(MobileService service,
                                                    MobilePlan plan);

    List<MobileServiceVariant> findByPlan(MobilePlan plan);
}