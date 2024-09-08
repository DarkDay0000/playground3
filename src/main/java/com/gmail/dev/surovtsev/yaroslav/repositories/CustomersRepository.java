package com.gmail.dev.surovtsev.yaroslav.repositories;

import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {

}
