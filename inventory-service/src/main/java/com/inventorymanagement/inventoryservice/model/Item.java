package com.inventorymanagement.inventoryservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name required")
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantity;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    public Item() {}

    public Item(String name, String description, Integer quantity, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
