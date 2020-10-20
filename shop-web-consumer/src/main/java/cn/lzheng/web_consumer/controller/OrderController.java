package cn.lzheng.web_consumer.controller;

import cn.lzheng.api.OrderService;
import cn.lzheng.entity.Result;
import cn.lzheng.pojo.TradeOrder;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @ClassName OrderController
 * @Author 6yi
 * @Date 2020/10/20 14:20
 * @Version 1.0
 * @Description:
 */

@RestController
public class OrderController {

    @DubboReference
    private OrderService orderService;


    @GetMapping("/order")
    public Result order(){
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
        Result result = orderService.confirmOrder(order);
        return result;
    }



}
