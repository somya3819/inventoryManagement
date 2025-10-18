package com.example.inventory_service.controller;

import com.example.inventory_service.models.Item;
import com.example.inventory_service.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService service;
    public ItemController(ItemService service) {
        this.service = service;
    }

    // Get all items
    @GetMapping
    public ResponseEntity<List<Item>> all() {
        List<Item> items = service.getAll();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(items);
    }

    // Get one by id
    @GetMapping("/{id}")
    public ResponseEntity<Item> one(@PathVariable Long id) {
        Item item = service.getById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    // Create with duplicate-check handling
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Item item) {
        try {
            Item created = service.create(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @Valid @RequestBody Item item) {
        try {
            Item updated = service.update(id, item);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); 
        }
    }

    // Search by name
    @GetMapping("/search")
    public ResponseEntity<List<Item>> search(@RequestParam("q") String q) {
        List<Item> results = service.searchByName(q);
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }
}