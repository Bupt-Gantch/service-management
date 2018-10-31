package cn.edu.bupt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2018/4/10.
 */
@SpringBootApplication
@ComponentScan("cn.edu.bupt")
@MapperScan("cn.edu.bupt.mapper")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
