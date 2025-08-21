package com.example.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "orders")
@ToString(exclude = "orders")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();
}
