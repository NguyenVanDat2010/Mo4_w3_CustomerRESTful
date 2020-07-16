package cms.service.customer;

import cms.model.Customer;
import cms.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        ArrayList<Customer> list=new ArrayList<>();
        customerRepository.findAll().forEach(list::add);
        return  list;
//        return streamAll().collect(Collectors.toList());
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void save(Customer model) {
        customerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        customerRepository.delete(id);
    }

//    private Stream<Customer> streamAll() {
//        return StreamSupport.stream(customerRepository.findAll().spliterator(), false);
//    }

//    private Stream<Customer> streamAll(Iterable<Customer> customers) {
//        return StreamSupport.stream(customers.spliterator(), false);
//    }
}
