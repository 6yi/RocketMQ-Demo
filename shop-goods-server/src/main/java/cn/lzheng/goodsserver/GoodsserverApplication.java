package cn.lzheng.goodsserver;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "cn.lzheng.goodsserver.service")
public class GoodsserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsserverApplication.class, args);
    }

}
