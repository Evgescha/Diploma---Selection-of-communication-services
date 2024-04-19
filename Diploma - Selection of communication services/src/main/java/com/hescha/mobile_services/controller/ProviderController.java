package com.hescha.mobile_services.controller;

import java.util.List;

import com.hescha.mobile_services.entity.Provider;
import com.hescha.mobile_services.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService service;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "providers";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            Provider entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Provider());
        }
        return "providers-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/providers";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Provider entity) {
        service.create(entity);
        return "redirect:/providers";
    }
}

