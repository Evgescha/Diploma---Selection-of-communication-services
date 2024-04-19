package com.hescha.mobile_services.controller;

import com.hescha.mobile_services.entity.MobilePlan;
import com.hescha.mobile_services.service.MobilePlanService;
import com.hescha.mobile_services.service.MobilePlanTypeService;
import com.hescha.mobile_services.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/mobilePlans")
public class MobilePlanController {

    @Autowired
    private MobilePlanService service;

    @Autowired
    private ProviderService serviceProvider;

    @Autowired
    private MobilePlanTypeService serviceType;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "mobilePlans";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            MobilePlan entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new MobilePlan());
        }
        model.addAttribute("providers", serviceProvider.repository.findAll());
        model.addAttribute("types", serviceType.repository.findAll());
        return "mobilePlans-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/mobilePlans";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(MobilePlan entity,
                                 @Param("providerId") Long providerId,
                                 @Param("typeId") Long typeId) {
        entity.setProvider(serviceProvider.read(providerId));
        entity.setType(serviceType.read(typeId));
        service.create(entity);
        return "redirect:/mobilePlans";
    }
}

