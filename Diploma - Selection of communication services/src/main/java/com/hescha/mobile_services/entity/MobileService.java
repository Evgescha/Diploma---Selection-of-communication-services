package com.hescha.mobile_services.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class MobileService extends AbstractEntity {
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
            "service")
    private List<MobileServiceVariant> serviceVariants = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }
}
