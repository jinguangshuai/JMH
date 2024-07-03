package com.jgs.jmh.patterns.ioc;

import org.springframework.beans.factory.BeanFactory;

import java.util.*;

/**
 * @Auther：jgs
 * @Data：2024/6/25 - 06 - 25 - 11:23
 * @Description:com.jgs.jmh.patterns.ioc
 * @version:1.0
 */
public class QfBeanFactory {
    private static Properties properties = new Properties();
    //该map相当于spring容器，用来存储创建好的bean对象
    private static Map<String, Object> ioc = new HashMap<String, Object>();
    static {
        try {
            properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("beans.properties"));
            //获取所有属性名
            Set<String> names = properties.stringPropertyNames();
            System.out.println(names);
            //通过反射技术创建属性文件里配置的要创建的bean开始
            for (String name : names) {
                //获取全类名
                String clsName= (String) properties.get(name);
                Class clazz=Class.forName(clsName);
                if (!Objects.isNull(clazz)){
                    //反射构建对象
                    Object object=clazz.getConstructor().newInstance();
                    // 存储到map中
                    ioc.put(name,object);
                }
            }
            //通过反射技术创建属性文件里配置的要创建的bean结束
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     通过属性文件中属性名(类似spring配置文件中的bean的id)获取工厂创建好的对象
     */
    public  static Object getBean(String beanName){
        return ioc.get(beanName);
    }
}
