package com.yaoge.repository;

import com.yaoge.entity.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Query By Example 查询
 */
public interface CustomerSpecificationRepository extends
        PagingAndSortingRepository<Customer,Long> , JpaSpecificationExecutor<Customer> {



}
