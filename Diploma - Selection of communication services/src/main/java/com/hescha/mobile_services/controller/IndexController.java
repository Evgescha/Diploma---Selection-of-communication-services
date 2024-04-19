package com.hescha.mobile_services.controller;

import com.hescha.mobile_services.entity.MobilePlan;
import com.hescha.mobile_services.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(path = {"/", "/index"})
public class IndexController {

    @Autowired
    private MobilePlanService mobilePlanService;

    @Autowired
    private MobilePlanTypeService mobilePlanTypeService;

    @Autowired
    private MobileServiceService mobileServiceService;

    @Autowired
    private ProviderService providerService;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("plans", mobilePlanService.repository.findAll());
        loadStandartLists(model);

        return "index";
    }

    private void loadStandartLists(Model model) {
        model.addAttribute("types", mobilePlanTypeService.repository.findAll());
        model.addAttribute("services",
                mobileServiceService.repository.findAll());
        model.addAttribute("providers", providerService.repository.findAll());
    }

    @GetMapping("/planDescription/{id}")
    public String planDescription(Model model, @PathVariable("id") Long id) {
        model.addAttribute("entity", mobilePlanService.read(id));
        return "planDescription";
    }


    @PostMapping("/compare")
    public String compare(Model model,
                          @RequestParam(name = "toCompare", required =
                                  false) Long[] toCompare) {
        List<MobilePlan> plans = new ArrayList<>();

        if (toCompare != null) {
            for (Long serviceId : toCompare) {
                plans.add(mobilePlanService.read(serviceId));
            }
        }


        model.addAttribute("list", plans);
        model.addAttribute("services",
                mobileServiceService.repository.findAll());
        return "compare";
    }

    @PostMapping("/findByFilter")
    public String findByFilter(Model model,
                               @RequestParam(name = "priceFrom", required =
                                       false) Long priceFrom,
                               @RequestParam(name = "priceTo", required =
                                       false) Long priceTo,
                               @RequestParam("providerId") Long providerId,
                               @RequestParam("typeId") Long typeId,
                               @RequestParam(name = "service", required =
                                       false) Long[] service,
                               @RequestParam("sortVariant") Long sortVariant) {
        loadStandartLists(model);


        List<MobilePlan> plans = mobilePlanService.repository.findAll();

        plans = MobilePlanFilterService.filterByAll(priceFrom, priceTo,
                providerId, typeId, service, plans);


        if (sortVariant != null || sortVariant != -1) {
            if (sortVariant == 1)
                Collections.sort(plans,
                        (o1, o2) -> new Float(o1.getPrice()).compareTo(o2.getPrice()));
            else if (sortVariant == 2)
                Collections.sort(plans,
                        (o1, o2) -> new Float(o2.getPrice()).compareTo(o1.getPrice()));
            else if (sortVariant == 3)
                Collections.sort(plans,
                        (o1, o2) -> o2.getProvider().getName().compareTo(o1.getProvider().getName()));
            else if (sortVariant == 4)
                Collections.sort(plans,
                        (o1, o2) -> o1.getName().compareTo(o2.getName()));
        }

        model.addAttribute("plans", plans);
        return "index";
    }


}