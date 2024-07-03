package com.jgs.jmh.patterns.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther：jgs
 * @Data：2024/6/25 - 06 - 25 - 11:29
 * @Description:com.jgs.jmh.patterns.ioc
 * @version:1.0
 */
public class IocTest {

    public static void main(String[] args) {
//        Dept dept= (Dept)QfBeanFactory.getBean("dept");
//        System.out.println(dept);
//        System.out.println(dept.getAddress());
//        System.out.println(dept.getName());
//        Emp emp= (Emp)QfBeanFactory.getBean("emp");
//        System.out.println(emp.getId());
//        System.out.println(emp.getSalary());
//        System.out.println(emp);

        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-bean.xml");
        User dept= (User) applicationContext.getBean("user");
        System.out.println(dept);
        System.out.println(dept.getName());
        System.out.println(dept.getAddress());
        SalaryInfo emp= (SalaryInfo) applicationContext.getBean("salaryInfo");
        System.out.println(emp);
        System.out.println(emp.getId());
        System.out.println(emp.getSalary());
    }
}
