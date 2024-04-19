package com.hescha.mobile_services.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Provider extends AbstractEntity {
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provider")
    private List<MobilePlan> plans = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }
}
