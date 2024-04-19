package com.hescha.mobile_services.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class MobileServiceVariant extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.REMOVE)
    private MobilePlan plan;

    @ManyToOne
    private MobileService service;

    private String value;

    @Override
    public String toString() {
        return service.getName() + ": " + value;
    }
}
