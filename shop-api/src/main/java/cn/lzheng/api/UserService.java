package cn.lzheng.api;

import cn.lzheng.entity.Result;
import cn.lzheng.pojo.TradeUser;
import cn.lzheng.pojo.TradeUserMoneyLog;

/**
 * @ClassName UserService
 * @Author 6yi
 * @Date 2020/10/16 0:28
 * @Version 1.0
 * @Description:
 */

public interface UserService {
    TradeUser findOne(Long userId);

    Result updateMoneyPaid(TradeUserMoneyLog userMoneyLog);
}
