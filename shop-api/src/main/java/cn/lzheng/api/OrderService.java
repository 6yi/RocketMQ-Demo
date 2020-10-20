package cn.lzheng.api;

import cn.lzheng.entity.Result;
import cn.lzheng.pojo.TradeOrder;

/**
 * @ClassName OrderService
 * @Author 6yi
 * @Date 2020/10/16 0:42
 * @Version 1.0
 * @Description:
 */

public interface OrderService {

    /**
     * 下单接口
     * @param order
     * @return
     */
    Result confirmOrder(TradeOrder order);


}
