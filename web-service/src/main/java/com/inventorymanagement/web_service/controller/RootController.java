package com.inventorymanagement.web_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String redirectToItems() {
        return "redirect:/web/items";
    }
}
