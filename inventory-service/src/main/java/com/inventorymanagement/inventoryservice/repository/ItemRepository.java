package com.inventorymanagement.inventoryservice.repository;

import com.inventorymanagement.inventoryservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);
}
