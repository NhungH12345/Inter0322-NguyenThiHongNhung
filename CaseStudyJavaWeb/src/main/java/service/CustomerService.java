package service;

import model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    List<Customer> search(String name);
    Customer findById(int id);
    boolean save(Customer customer);
    boolean delete(int id);
    boolean update(Customer customer);
}
