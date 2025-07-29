package com.example.realestate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realestate.models.Item;
import com.example.realestate.repositories.ItemRepository;
import com.example.realestate.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemRepository repository;

	@Override
	public List<Item> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Item> findByIdIn(List<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public List<Long> toIds(List<Item> items) {
		return items.stream().map(item -> item.getId()).toList();
	}
}
