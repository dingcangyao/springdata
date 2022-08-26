package com.yaoge.repository;

import com.yaoge.entity.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Query By Example 查询
 */
public interface CustomerQBERepository extends
        PagingAndSortingRepository<Customer,Long> , QueryByExampleExecutor<Customer> {



}
