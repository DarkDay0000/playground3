package com.gmail.dev.surovtsev.yaroslav.dao;

import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void save(Customer customer) {
        jdbcTemplate.update("insert into customer values(?, ?, ?)", getNextId(), customer.getFirstName(), customer.getLastName());
    }

    public void update(Integer id, Customer updatedCustomer) {
        jdbcTemplate.update("update customer set first_name = ?, last_name = ? where id = ?", updatedCustomer.getFirstName(), updatedCustomer.getLastName(), id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update("delete from customer where id = ?", id);
    }

    private Integer getNextId() {
        Integer lastId = jdbcTemplate.queryForObject("select max(id) from customer", Integer.class);
        return lastId == null ? 1 : lastId + 1;
    }
}
