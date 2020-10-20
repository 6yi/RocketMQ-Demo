package cn.lzheng.userserver;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("cn.lzheng.userserver.dao")
@EnableDubbo(scanBasePackages = "cn.lzheng.userserver.service")
public class UserserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserserverApplication.class, args);
    }

}
