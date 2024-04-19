package com.hescha.mobile_services.service;

import com.hescha.mobile_services.entity.MobilePlanType;
import com.hescha.mobile_services.repository.MobilePlanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobilePlanTypeService extends CrudImpl<MobilePlanType> {

    public MobilePlanTypeRepository repository;

    @Autowired
    public MobilePlanTypeService(MobilePlanTypeRepository repository) {
        super(repository);
        this.repository = repository;
    }


}