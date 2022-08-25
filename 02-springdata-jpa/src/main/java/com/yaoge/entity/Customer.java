package com.yaoge.entity;


import javax.persistence.*;

/**
 * create by yaoge
 * 2022/8/23 17:48
 */
@Entity
@Table(name = "cst_customer")
public class Customer {

    /**
     *     @Id  声明主键的配置
     *     @GeneratedValue(strategy = GenerationType.IDENTITY) ：配置主键的生成策略
     *     GenerationType.IDENTITY :自增 ，mysql 地城数据库必须支持自动增长
     *      GenerationType.SEQUENCE：序列 oracle 底层数据库必须支持序列
     *      GenerationType.TABLE： jpa提供的一种机制，通过一张数据库表的形式帮助我们完成主键自增
     *      GenerationType.AUTO： 由程序自动的帮组我们选择主键生成策略
     *
     *
     *
     *
     *     @Column(name = "cust_id") 数据库中表字段的名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;//客户的主键

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_adress")
    private String custAdress;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAdress() {
        return custAdress;
    }

    public void setCustAdress(String custAdress) {
        this.custAdress = custAdress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custAdress='" + custAdress + '\'' +
                '}';
    }
}
