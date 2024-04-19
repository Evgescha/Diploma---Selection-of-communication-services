package com.hescha.mobile_services.service;

import com.hescha.mobile_services.entity.MobilePlan;

import java.util.ArrayList;
import java.util.List;

public class MobilePlanFilterService {

    public static List<MobilePlan> filterByAll(Long priceFrom, Long priceTo,
                                               Long providerId, Long typeId,
                                               Long[] service,
                                               List<MobilePlan> plans) {
        plans = filterByProvider(providerId, plans);
        plans = filterByType(typeId, plans);
        plans = filterByPriceFrom(priceFrom, plans);
        plans = filterByPriceTo(priceTo, plans);
        plans = filterByAllServices(service, plans);
        return plans;
    }

    private static List<MobilePlan> filterByAllServices(Long[] service,
                                                        List<MobilePlan> plans) {
        if (service != null) {
            for (Long serviceId : service) {
                plans = filterByOneService(plans, serviceId);
            }
        }
        return plans;
    }

    private static List<MobilePlan> filterByOneService(List<MobilePlan> plans,
                                                       Long serviceId) {
        if (serviceId != null) {
            List<MobilePlan> temp = new ArrayList<>();
            for (MobilePlan plan : plans) {
                if (plan.haveServiceWithId(serviceId))
                    temp.add(plan);
            }
            plans = temp;
        }
        return plans;
    }

    private static List<MobilePlan> filterByPriceTo(Long priceTo,
                                                    List<MobilePlan> plans) {
        if (priceTo != null) {
            List<MobilePlan> temp = new ArrayList<>();
            for (MobilePlan plan : plans) {
                if (plan.getPrice() <= priceTo)
                    temp.add(plan);
            }
            plans = temp;
        }
        return plans;
    }

    private static List<MobilePlan> filterByPriceFrom(Long priceFrom,
                                                      List<MobilePlan> plans) {
        if (priceFrom != null) {
            List<MobilePlan> temp = new ArrayList<>();
            for (MobilePlan plan : plans) {
                if (plan.getPrice() >= priceFrom)
                    temp.add(plan);
            }
            plans = temp;
        }
        return plans;
    }

    private static List<MobilePlan> filterByType(Long typeId,
                                                 List<MobilePlan> plans) {
        if (typeId != -1) {
            List<MobilePlan> temp = new ArrayList<>();
            for (MobilePlan plan : plans) {
                if (plan.getType().getId() == typeId)
                    temp.add(plan);
            }
            plans = temp;
        }
        return plans;
    }

    private static List<MobilePlan> filterByProvider(Long providerId,
                                                     List<MobilePlan> plans) {
        if (providerId != -1) {
            List<MobilePlan> temp = new ArrayList<>();
            for (MobilePlan plan : plans) {
                if (plan.getProvider().getId() == providerId)
                    temp.add(plan);
            }
            plans = temp;
        }
        return plans;
    }

}
