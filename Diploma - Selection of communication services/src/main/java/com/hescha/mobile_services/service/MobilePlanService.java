package com.hescha.mobile_services.service;

import com.hescha.mobile_services.entity.MobilePlan;
import com.hescha.mobile_services.repository.MobilePlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobilePlanService extends CrudImpl<MobilePlan> {

    public MobilePlanRepository repository;

    @Autowired
    public MobilePlanService(MobilePlanRepository repository) {
        super(repository);
        this.repository = repository;
    }


}