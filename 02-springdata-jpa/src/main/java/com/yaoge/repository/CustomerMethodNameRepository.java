package com.yaoge.repository;

import com.yaoge.entity.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 书写方法名实现自定义操作
 */
public interface CustomerMethodNameRepository extends PagingAndSortingRepository<Customer,Long> {

    List<Customer> findByCustName(String custName);

    boolean existsByCustName(String custName);

}
