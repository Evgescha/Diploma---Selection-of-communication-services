package com.hescha.mobile_services.service;

import com.hescha.mobile_services.entity.Provider;
import com.hescha.mobile_services.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService extends CrudImpl<Provider> {

    public ProviderRepository repository;

    @Autowired
    public ProviderService(ProviderRepository repository) {
        super(repository);
        this.repository = repository;
    }


}