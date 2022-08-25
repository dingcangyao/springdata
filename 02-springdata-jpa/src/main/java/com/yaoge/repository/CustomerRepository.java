package com.yaoge.repository;

import com.yaoge.entity.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {

    //演示增删查改


    //查询

    /**
     * 参数传递 可以通过位置  ？1  或者具名参数 ，但是需要@param修饰
     * @param custName
     * @return
     */
    @Query("FROM Customer where custName=?1")
    Customer findCustomerByCusName(String custName);
    /**
     * 原生SQL
     * 主要依靠 @Query中的一个属性 nativeQuery 来设置
     * @param custName
     * @return
     */
    @Query(value = "select * from cst_customer where  cust_name=:custName",nativeQuery = true)
    Customer findCustomerByNative(@Param("custName") String custName);
    //修改


    @Modifying//增删改操作
    @Query("update Customer c set c.custName=:custName where c.custId=:custId")
    int updateCustomer(@Param("custName") String custName, @Param("custId") Long custId);


    @Modifying//增删改操作
    @Query("delete from Customer c where c.custId=?1")
    int deleteCustomer(Long custId);

    //新增，Hql 只支持特殊的新增
    @Modifying//增删改操作
    @Query("INSERT INTO Customer(custName) select c.custName from Customer c where c.custId=?1")
    int insertCustomer(Long custId);

}
