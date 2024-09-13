package com.gmail.dev.surovtsev.yaroslav.services;

import com.gmail.dev.surovtsev.yaroslav.models.Item;
import com.gmail.dev.surovtsev.yaroslav.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findAll() {
        return itemsRepository.findAll();
    }

    public Item findById(int id) {
        return itemsRepository.findById(id).orElse(null);
    }

    @Transactional
    public Item save(Item item) {
        return itemsRepository.save(item);
    }

    @Transactional
    public Item update(int id, Item updatedItem) {
        updatedItem.setId(id);
        return itemsRepository.save(updatedItem);
    }

    @Transactional
    public void deleteById(int id) {
        itemsRepository.deleteById(id);
    }
}
