package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.services.DocsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DocsController {

    @Autowired
    private DocsService docsService;

    @GetMapping("/")
    public ModelAndView getPage() {
        return new ModelAndView("docs");
    }

    @PostMapping("/")
    public String carStatements(UserDto userDto) {
        try {
            docsService.sendToExchange(userDto);
            return "redirect:/";
        } catch (IllegalStateException e) {
            return "redirect:/?error";
        }

    }
}
