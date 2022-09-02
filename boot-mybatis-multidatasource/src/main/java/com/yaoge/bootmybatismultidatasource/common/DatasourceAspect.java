package com.yaoge.bootmybatismultidatasource.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * create by yaoge
 * 2022/9/2 15:23
 */
@Aspect
@Order(0)
@Component
public class DatasourceAspect {

    @Pointcut("@annotation(com.yaoge.bootmybatismultidatasource.common.Datasource)" +//这个是方法上注解
            "|| @within(com.yaoge.bootmybatismultidatasource.common.Datasource)")//这个是类上注解
    public void pointcut(){


    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {


        this.changeDbType(joinPoint);//切换数据源

            return joinPoint.proceed();

    }

    /**
     * 数据源切换，优先方法上的配置，方法上没有配置，则取类上的配置
     * @param joinPoint
     */
    private void changeDbType(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();

        Method method = methodSignature.getMethod();



        Datasource datasource = method.getAnnotation(Datasource.class);//方法上获取注解

        if (datasource!=null){
            DatasourceTypeManager.setDatasource(datasource.type());
            return;
        }

        //获取类上的注解

        datasource = joinPoint.getTarget().getClass().getAnnotation(Datasource.class);

        if (datasource==null){
            DatasourceTypeManager.setDatasource(DBTypeEnum.ADB);
        }else {
            DatasourceTypeManager.setDatasource(datasource.type());
        }

    }



}
