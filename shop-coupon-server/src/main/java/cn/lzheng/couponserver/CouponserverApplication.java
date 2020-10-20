package cn.lzheng.couponserver;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "cn.lzheng.couponserver.service")
public class CouponserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponserverApplication.class, args);
    }

}
