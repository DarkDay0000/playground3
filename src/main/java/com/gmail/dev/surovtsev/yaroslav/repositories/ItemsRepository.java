package com.gmail.dev.surovtsev.yaroslav.repositories;

import com.gmail.dev.surovtsev.yaroslav.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {

}
