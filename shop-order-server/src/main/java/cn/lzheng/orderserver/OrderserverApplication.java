package cn.lzheng.orderserver;

import cn.lzheng.utils.IDWorker;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "cn.lzheng.orderserver.service")
public class OrderserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderserverApplication.class, args);
    }


    @Bean
    public IDWorker getBean(){
        return new IDWorker(1,1);
    }
}
