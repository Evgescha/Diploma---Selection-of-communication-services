package com.hescha.mobile_services.controller;

import com.hescha.mobile_services.entity.MobileService;
import com.hescha.mobile_services.service.MobileServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/mobileServices")
public class MobileServiceController {

    @Autowired
    private MobileServiceService service;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "mobileServices";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            MobileService entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new MobileService());
        }
        return "mobileServices-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/mobileServices";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(MobileService entity) {
        service.create(entity);
        return "redirect:/mobileServices";
    }
}

