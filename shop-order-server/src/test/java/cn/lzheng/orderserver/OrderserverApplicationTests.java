package cn.lzheng.orderserver;

import cn.lzheng.orderserver.service.OrderServiceImpl;
import cn.lzheng.pojo.TradeOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

@SpringBootTest
class OrderserverApplicationTests {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    void contextLoads() throws IOException {
        Long coupouId = 345988230098857984L;
        Long goodsId = 345959443973935104L;
        Long userId = 345963634385633280L;

        TradeOrder order = new TradeOrder();
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        order.setCouponId(coupouId);
        order.setAddress("北京");
        order.setGoodsNumber(1);
        order.setGoodsPrice(new BigDecimal(5000.00));
        order.setShippingFee(BigDecimal.ZERO);
        order.setOrderAmount(new BigDecimal(5000.00));
        order.setMoneyPaid(new BigDecimal(5000));

        orderService.confirmOrder(order);
        System.in.read();

    }

}
