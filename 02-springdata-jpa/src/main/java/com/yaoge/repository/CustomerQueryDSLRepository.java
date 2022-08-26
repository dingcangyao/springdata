package com.yaoge.repository;

import com.yaoge.entity.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Query By Example 查询
 */
public interface CustomerQueryDSLRepository extends
        PagingAndSortingRepository<Customer,Long> , QuerydslPredicateExecutor<Customer> {



}
