package com.gmail.dev.surovtsev.yaroslav.dao;

import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> index() {
        return jdbcTemplate.query("select * from customer", new BeanPropertyRowMapper<>(Customer.class));
    }

    public Customer show(Integer id) {
        return jdbcTemplate.query("select * from customer where id = ?", new BeanPropertyRowMapper<>(Customer.class), id)
                .stream().findFirst().orElse(null);
    }

    public Optional<Customer> show(String email) {
        return jdbcTemplate.query("select * from customer where email = ?", new BeanPropertyRowMapper<>(Customer.class), email)
                .stream().findAny();
    }

    public void save(Customer customer) {
        jdbcTemplate.update("insert into customer(first_name, last_name, email) values(?, ?, ?)", customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }

    public void update(Integer id, Customer updatedCustomer) {
        System.out.println("updatedCustomer email: " + updatedCustomer.getEmail());
        jdbcTemplate.update("update customer set first_name = ?, last_name = ?, email = ? where id = ?", updatedCustomer.getFirstName(), updatedCustomer.getLastName(), updatedCustomer.getEmail(), id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update("delete from customer where id = ?", id);
    }

    public void deleteAll() {
        jdbcTemplate.update("delete from customer");
    }

    private List<Customer> create1000Customers() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            customer.setFirstName("firstname" + i);
            customer.setLastName("lastName" + i);
            customer.setEmail("email" + i + "@gmail.com");
            customers.add(customer);
        }
        return customers;
    }

    public void testMultipleUpdate() {
        long before = System.currentTimeMillis();
        List<Customer> customers = create1000Customers();
        for (Customer customer : customers) {
            save(customer);
        }
        long after = System.currentTimeMillis();
        System.out.println("Time testMultipleUpdate: " + (after - before));
    }

    public void testBatchUpdate() {
        List<Customer> customers = create1000Customers();
        long before = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO customer values (?, ?, ?)",
        new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, customers.get(i).getId());
                ps.setString(2, customers.get(i).getFirstName());
                ps.setString(3, customers.get(i).getLastName());
                ps.setString(4, customers.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return customers.size();
            }
        });
        long after = System.currentTimeMillis();
        System.out.println("Time testBatchUpdate: " + (after - before));
    }
}
