package com.automation.etc_mapping.controller;

import com.automation.etc_mapping.model.EtcMappingForm;
import com.automation.etc_mapping.service.EtcMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jcr.RepositoryException;

@Controller
public class EtcMappingController {

    @Autowired
    private EtcMappingService etcMappingService;

    @GetMapping
    public String index(){
        return "<h1>ETC mapping</h1>";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("etcMappingForm", new EtcMappingForm());
        return "form";
    }

    @PostMapping("/createMappings")
    public String createMappings(
            @ModelAttribute EtcMappingForm etcMappingForm,
            RedirectAttributes redirectAttributes) {
        try {
            etcMappingService.createEtcMappings(etcMappingForm.getMatchPattern(), etcMappingForm.getRedirectPattern());
            redirectAttributes.addFlashAttribute("message", "ETC Mappings created successfully.");
        } catch (RepositoryException e) {
            redirectAttributes.addFlashAttribute("error", "Error creating ETC Mappings: " + e.getMessage());
        }
        return "redirect:/form";
    }
}