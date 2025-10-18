package com.inventorymanagement.web_service.controller;

import com.inventorymanagement.web_service.model.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/web/items")
public class WebController {

    private final RestTemplate rest;
    private final String inventoryBase;

    public WebController(RestTemplate rest,
            @Value("${inventory.service.url:http://localhost:8081}") String inventoryBase) {
        this.rest = rest;
        this.inventoryBase = inventoryBase.endsWith("/") ? inventoryBase.substring(0, inventoryBase.length() - 1)
                : inventoryBase;
    }

    // List all items
    @GetMapping
    public String listItems(Model model) {
        try {
            Item[] items = rest.getForObject(inventoryBase + "/api/items", Item[].class);
            model.addAttribute("items",
                    items != null ? java.util.Arrays.asList(items) : java.util.Collections.emptyList());
        } catch (Exception e) {
            model.addAttribute("items", java.util.Collections.emptyList());
            model.addAttribute("error", "Unable to fetch items from inventory service.");
        }
        return "items";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new Item());
        return "create-item";
    }

    // Updated to handle duplicate item error (409)
    @PostMapping("/create")
    public String createItem(@ModelAttribute Item item, Model model) {
        try {
            rest.postForObject(inventoryBase + "/api/items", item, Item.class);
            return "redirect:/web/items";
        } catch (HttpClientErrorException.Conflict ex) {
            // 409 Conflict → duplicate item
            model.addAttribute("error", ex.getResponseBodyAsString());

            // reload list so page shows items + error
            Item[] items = rest.getForObject(inventoryBase + "/api/items", Item[].class);
            model.addAttribute("items",
                    items != null ? java.util.Arrays.asList(items) : java.util.Collections.emptyList());
            return "items";
        } catch (Exception ex) {
            model.addAttribute("error", "Unexpected error: " + ex.getMessage());
            Item[] items = rest.getForObject(inventoryBase + "/api/items", Item[].class);
            model.addAttribute("items",
                    items != null ? java.util.Arrays.asList(items) : java.util.Collections.emptyList());
            return "items";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Item item = rest.getForObject(inventoryBase + "/api/items/" + id, Item.class);
        model.addAttribute("item", item);
        return "edit-item";
    }

    @PostMapping("/edit/{id}")
    public String updateItem(@PathVariable Long id, @ModelAttribute Item item) {
        rest.exchange(inventoryBase + "/api/items/" + id, HttpMethod.PUT, new HttpEntity<>(item), Void.class);
        return "redirect:/web/items";
    }

    @GetMapping("/delete/{id}")
    public String confirmDelete(@PathVariable Long id, Model model) {
        Item item = rest.getForObject(inventoryBase + "/api/items/" + id, Item.class);
        model.addAttribute("item", item);
        return "delete-item";
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        rest.delete(inventoryBase + "/api/items/" + id);
        return "redirect:/web/items";
    }

    @GetMapping("/view")
    public String showViewForm() {
        return "view-form";
    }

    @GetMapping("/view/search")
    public String showItemById(@RequestParam("id") Long id, Model model) {
        try {
            Item item = rest.getForObject(inventoryBase + "/api/items/" + id, Item.class);
            model.addAttribute("item", item);
        } catch (Exception e) {
            model.addAttribute("item", null);
            model.addAttribute("error", "Item not found with ID: " + id);
        }
        return "view-item";
    }
}