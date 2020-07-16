package cms.repository;

import cms.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface ICustomerRepository extends PagingAndSortingRepository<Customer,Long> {
}

