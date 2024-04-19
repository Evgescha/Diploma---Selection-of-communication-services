package com.hescha.mobile_services.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class MobilePlan extends AbstractEntity {

    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Provider provider;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private MobilePlanType type;

    private String shortDescription;

    @Column(length = 30000, columnDefinition = "TEXT")
    private String longDescription;

    private float price;

    private String urlToConnect;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "plan")
    private List <MobileServiceVariant> services = new ArrayList<>();

    @Override
    public String toString() {
        return "MobilePlan{" +
                "name='" + name + '\'' +
                ", provider=" + provider +
                ", type=" + type +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", price=" + price +
                ", urlToConnect='" + urlToConnect + '\'' +
                '}';
    }

    public boolean haveServiceWithId(Long serviceId){
        boolean have=false;
        for(MobileServiceVariant variant:services){
            if(variant.getService().getId()==serviceId){
                have=true;
            }
        }
        return have;
    }
    public MobileServiceVariant getServiceVariantById(Long serviceId){
        for(MobileServiceVariant variant:services){
            if(variant.getService().getId()==serviceId){
                return variant;
            }
        }
        return new MobileServiceVariant();
    }
}
