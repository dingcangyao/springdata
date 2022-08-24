package com.yaoge.test;

import com.yaoge.pojo.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * create by yaoge
 * 2022/8/23 19:01
 * 测试JPA操作
 *
 * 如果想要更更换 ORM框架，就在 persistence.xml 添加持久化单元和相关配置
 *   然后创建factory的时候，就指定就行了
 */
public class JpaTest {
    private EntityManagerFactory factory;//其实底层实现还是hibernate的相关实现
    EntityManager em;

    @Before
    public void init(){

        //会去读取  MRTA-INF下面的persistence.xml中对应名称的持久化单元来构建 EntityManangerFactory
        factory=Persistence.createEntityManagerFactory("hibernateJPA");

        em=factory.createEntityManager();
    }

    /**
     * 测试 持久化对象到数据库
     */
    @Test
    public void testC(){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
        customer.setCustName("张麻子");
        customer.setCustAdress("山上");
        em.persist(customer);//持久化操作


        transaction.commit();
        em.close();
    }
    /**
     * 测试 查询
     */
    @Test
    public void testR(){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

//        Customer customer = em.find(Customer.class, 1L);//立即查询
        Customer customer = em.getReference(Customer.class, 1L);//延迟查询，在用到customer对象的时候，才去查询
        System.out.println("++++++++++");
        System.out.println(customer);

        transaction.commit();
        em.close();
    }
    /**
     * 测试 更新  使用 merge（）
     * 如果指定了Id  会先执行 查询操作，如果值不一样，就执行更新，否则就不更新
     * 但是更新的话不会单独更新你设置的那个值
     *
     * 如果不指定 ID 就会执行插入操作
     *
     * merge 的方法除了要先查询一遍，还有就是属性值是null 也会帮你覆盖掉，因为它不知道
     * 是否需要处理，hibernate也没有提供响应的解决，可以使用jpql解决
     */
    @Test
    public void testU(){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Customer customer = em.find(Customer.class, 1L);
        customer.setCustName("长泽雅美");

        em.merge(customer);
        transaction.commit();
        em.close();
    }

    /**
     * 测试jpql 进行更新，其实可以更加灵活
     * 进行删改查 必须要放在事务中
     */
    @Test
    public void testUjpql(){



        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        String jpql="update Customer set custName=:name where custId=:id";
        em.createQuery(jpql).setParameter("name","长泽雅美").setParameter("id",1L).executeUpdate();
        transaction.commit();
        em.close();
        System.out.println("更新完成");

    }
    /**
     * 测试使用SQL语句
     */
    @Test
    public void testUSQL(){



        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        String sql="update cst_customer set cust_name=:name where cust_id=:id";
        em.createNativeQuery(sql).setParameter("name","堺雅人").setParameter("id",1L).executeUpdate();
        transaction.commit();
        em.close();
        System.out.println("更新完成");

    }
    /**
     * 删除  注意，不能删除一个游离状态的对象
     * 想要通过remove 就必须 先查再删除
     * 4种状态
     *
     * 临时状态
     * 持久状态
     * 删除状态：执行了 remove 但是还没有提交
     * 游离状态：实体了数据库的关系断开了 实体的改变不会同步到数据库
     */
    @Test
    public void testD(){
        /**
         * 一级缓存同一个 Entitymanager 就会有缓存
         */


        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Customer customer= em.find(Customer.class,3L);

        em.remove(customer);


        transaction.commit();
        em.close();
        System.out.println("更新完成");

    }
}
