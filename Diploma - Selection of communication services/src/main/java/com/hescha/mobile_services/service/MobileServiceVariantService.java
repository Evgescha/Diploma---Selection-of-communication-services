package com.hescha.mobile_services.service;

import com.hescha.mobile_services.entity.MobileServiceVariant;
import com.hescha.mobile_services.repository.MobileServiceVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileServiceVariantService extends CrudImpl<MobileServiceVariant> {

    public MobileServiceVariantRepository repository;

    @Autowired
    public MobileServiceVariantService(MobileServiceVariantRepository repository) {
        super(repository);
        this.repository = repository;
    }


}