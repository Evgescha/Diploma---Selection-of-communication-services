package com.hescha.mobile_services.controller;

import com.hescha.mobile_services.entity.MobilePlan;
import com.hescha.mobile_services.entity.MobileServiceVariant;
import com.hescha.mobile_services.service.MobilePlanService;
import com.hescha.mobile_services.service.MobileServiceService;
import com.hescha.mobile_services.service.MobileServiceVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/mobileServiceVariants")
public class MobileServiceVariantController {

    @Autowired
    private MobileServiceVariantService service;

    @Autowired
    private MobilePlanService servicePlan;

    @Autowired
    private MobileServiceService serviceService;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "mobileServiceVariants";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/mobileServiceVariants";
    }


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(MobileServiceVariant entity,
                                 @Param("planId") Long planId, @Param(
            "serviceId") Long serviceId) {
        entity.setPlan(servicePlan.read(planId));
        entity.setService(serviceService.read(serviceId));
        service.create(entity);

        return "redirect:/mobileServiceVariants/byPlanId/" + planId;
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            MobileServiceVariant entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new MobileServiceVariant());
        }

        model.addAttribute("plans", servicePlan.repository.findAll());
        model.addAttribute("services", serviceService.repository.findAll());

        return "mobileServiceVariants-add-edit";
    }


    @RequestMapping("/byPlanId/{id}")
    public String byPlanId(Model model, @PathVariable("id") Long id) {
        MobilePlan mobilePlan = servicePlan.read(id);
        model.addAttribute("list", mobilePlan.getServices());
        model.addAttribute("entity", mobilePlan);
        return "mobileServiceVariants";
    }

    @RequestMapping("/editByPlan/{id}")
    public String editByPlan(Model model, @PathVariable("id") Long id) {
        MobilePlan mobilePlan = servicePlan.read(id);
        List<MobilePlan> plans = new ArrayList<>();
        plans.add(mobilePlan);
        model.addAttribute("plans", plans);
        model.addAttribute("services", serviceService.repository.findAll());
        model.addAttribute("entity", new MobileServiceVariant());

        return "mobileServiceVariants-add-edit";
    }
}

