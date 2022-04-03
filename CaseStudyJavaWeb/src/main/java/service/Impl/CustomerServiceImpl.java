package service.Impl;

import model.Customer;
import repository.CustomerRepository;
import service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> search(String name) {
        return null;
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public boolean save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean delete(int id) {
        return customerRepository.delete(id);
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }


}

//    @Override
//    public List<Customer> search(String name) {
//        return customerRepository.search(name);
//    }
//}
