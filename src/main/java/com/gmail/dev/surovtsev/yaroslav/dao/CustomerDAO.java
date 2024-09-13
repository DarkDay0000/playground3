package com.gmail.dev.surovtsev.yaroslav.dao;

import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import com.gmail.dev.surovtsev.yaroslav.models.Item;
import com.gmail.dev.surovtsev.yaroslav.repositories.CustomersRepository;
import com.gmail.dev.surovtsev.yaroslav.repositories.ItemsRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDAO {

    private final EntityManager em;
    private final CustomersRepository customersRepository;
    private final ItemsRepository itemsRepository;

    @Autowired
    public CustomerDAO(EntityManager em, CustomersRepository customersRepository, ItemsRepository itemsRepository) {
        this.em = em;
        this.customersRepository = customersRepository;
        this.itemsRepository = itemsRepository;
    }

    @Transactional
    public void deleteTestData() {
        customersRepository.deleteAll();
        itemsRepository.deleteAll();
    }

    @Transactional
    public void createTestData() {
        List<Customer> testCustomers = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Item item1 = new Item("Item1_" + i);
            Item item2 = new Item("Item2_" + i);
            Customer customer = new Customer();
            customer.setFirstName("customer_first_name" + i);
            customer.setLastName("customer_last_name" + i);
            customer.setEmail("customer_email_" + i + "@gmail.com");
            customer.addItem(item1);
            customer.addItem(item2);
            testCustomers.add(customer);
            items.add(item1);
            items.add(item2);
        }
        customersRepository.saveAll(testCustomers);
        itemsRepository.saveAll(items);
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = em.unwrap(Session.class);
        long before = System.currentTimeMillis();

        List<Customer> customers = session.createQuery("select c from Customer c", Customer.class).getResultList();
        customers.forEach(customer -> {
            System.out.println(customer.toString() + " has: " + customer.getItems());
        });

        long after = System.currentTimeMillis();

        System.out.println("===============================================================================");

        long before2 = System.currentTimeMillis();

        List<Customer> customers2 = session.createQuery("select c from Customer c LEFT JOIN FETCH c.items", Customer.class).getResultList();
        customers2.forEach(customer -> {
            System.out.println(customer.toString() + " has: " + customer.getItems());
        });

        long after2 = System.currentTimeMillis();

        System.out.println("1. Testing N+1: " + (after - before));
        System.out.println("2. Testing N+1: " + (after2 - before2));
    }

    @Transactional(readOnly = true)
    public void testGetAndLoad() {
        Session session = em.unwrap(Session.class);
        Customer customer = session.load(Customer.class, 35);
        System.out.println(customer);
        System.out.println(customer.getEmail());

        System.out.println(session.get(Customer.class, 36));
    }
}
