package com.example.realestate.services;

import java.util.List;

import com.example.realestate.models.Item;

public interface ItemService {
	List<Item> findAll();

	List<Item> findByIdIn(List<Long> ids);

	List<Long> toIds(List<Item> items);
}
