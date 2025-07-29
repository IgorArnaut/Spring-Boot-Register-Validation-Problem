package com.example.realestate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realestate.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByIdIn(List<Long> ids);
}
