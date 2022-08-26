package com.yaoge.test;

import com.yaoge.config.SpringDataJpaConfiguration;
import com.yaoge.entity.Customer;
import com.yaoge.repository.CustomerQBERepository;
import com.yaoge.repository.CustomerSpecificationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * create by yaoge
 * 2022/8/24 18:07
 *
 * Specification 查询测试
 * 支持的范围更广
 */
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)//java config 配置方式
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(value = false)//test方法中会对事务进行默认回滚，要设置一下
public class SpecificationTest {

    @Autowired
    CustomerSpecificationRepository customerRepository;

    /**
     * 简单示例，客户名称、客户地址动态查询
     */
    @Test
    public void test01(){

        List<Customer> all = customerRepository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {


                /**
                 * //root 理解成 from  ，获取到列
                 * CriteriaBuilder:设置各种条件（>,<,in)
                 * CriteriaQuery 用来组合（order by，where）
                 */

                return null;
            }
        });

    }

    /**
     *
     * 测试
     * 查询客户范围 in
     * id 大于
     * 地址精确
     */
    @Test
    public void test02(){

        List<Customer> all = customerRepository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {


                /**
                 * //root 理解成 from  ，获取到列
                 * CriteriaBuilder:设置各种条件（>,<,in)
                 * CriteriaQuery 用来组合（order by，where）
                 */

                //1.先拿到你需要的列
                Path<Long> custId = root.get("custId");
                Path<String> custName = root.get("custName");
                Path<String> custAddress = root.get("custAdress");

                //利用CriteriaBuilder设置条件
                Predicate custAddressP = criteriaBuilder.equal(custAddress, "beijing");//参数1 ：条件字段，参数2：值

                Predicate custIdP = criteriaBuilder.greaterThan(custId, 7L);//大于

                CriteriaBuilder.In<String> in = criteriaBuilder.in(custName);//in 稍微有写不同
                CriteriaBuilder.In<String> custNameP = in.value("宋庆龄").value("翁美玲");


                Predicate predicate1 = criteriaBuilder.or(custAddressP, custIdP);//可以使用or或者and来进行拼接

                Predicate predicate = criteriaBuilder.and(predicate1, custNameP);
                return predicate;


            }
        });
        all.forEach(item-> System.out.println(item));

    }
    /**
     * 模拟接收前端参数，实现动态查询
     */
    @Test
    public void test03(){
        Customer param = new Customer();
//        param.setCustAdress("beijing");
        param.setCustId(0L);
        param.setCustName("翁美玲,宋庆龄,张作霖,李四");

        List<Customer> all = customerRepository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {


                /**
                 * //root 理解成 from  ，获取到列
                 * CriteriaBuilder:设置各种条件（>,<,in)
                 * CriteriaQuery 用来组合（order by，where）
                 */

                //1.先拿到你需要的列
                Path<Long> custId = root.get("custId");
                Path<String> custName = root.get("custName");
                Path<String> custAddress = root.get("custAdress");


                List<Predicate> predicates=new ArrayList<>();
                if (param.getCustAdress()!=null){
                    predicates.add(criteriaBuilder.equal(custAddress, param.getCustAdress()));
                }
                if (param.getCustId()>0L){
                    predicates.add(criteriaBuilder.greaterThan(custId, param.getCustId()));
                }

                String[] split = param.getCustName().split(",");
                if (split.length>0){
                    CriteriaBuilder.In<String> in = criteriaBuilder.in(custName);
                    for (String s:split){
                        in.value(s);
                    }
                    predicates.add(in);
                }

                Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                return predicate;


            }
        });
        all.forEach(item-> System.out.println(item));

    }


}
