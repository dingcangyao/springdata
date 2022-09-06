package com.yaoge.springbootpostgresql.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.yaoge.springbootpostgresql.type.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
* create by yaoge
* 2022/9/5 14:59
*/
@Data
@Entity
@Table(name = "people")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class People {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "money")
    private Double money;

    @Column(name = "name")
    @Type(type = "json")
    private JsonNode jsonNode;


}
