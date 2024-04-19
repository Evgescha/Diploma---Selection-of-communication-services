package com.hescha.mobile_services.service;

import com.hescha.mobile_services.entity.MobileService;
import com.hescha.mobile_services.repository.MobileServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileServiceService extends CrudImpl<MobileService> {

    public MobileServiceRepository repository;

    @Autowired
    public MobileServiceService(MobileServiceRepository repository) {
        super(repository);
        this.repository = repository;
    }


}