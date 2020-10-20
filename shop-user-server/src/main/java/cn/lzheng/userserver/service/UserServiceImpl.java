package cn.lzheng.userserver.service;

import cn.lzheng.api.UserService;
import cn.lzheng.constant.ShopCode;
import cn.lzheng.entity.Result;
import cn.lzheng.exception.CastException;
import cn.lzheng.pojo.TradeUser;
import cn.lzheng.pojo.TradeUserMoneyLog;
import cn.lzheng.pojo.TradeUserMoneyLogExample;
import cn.lzheng.userserver.dao.TradeUserMapper;
import cn.lzheng.userserver.dao.TradeUserMoneyLogMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName UserServiceImpl
 * @Author 6yi
 * @Date 2020/10/19 14:54
 * @Version 1.0
 * @Description:
 */

@Service
@DubboService
public class UserServiceImpl implements UserService {

    @Autowired
    private TradeUserMapper userMapper;

    @Autowired
    private TradeUserMoneyLogMapper userMoneyLogMapper;

    @Override
    public TradeUser findOne(Long userId) {
        if(userId==null){
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        return userMapper.selectById(userId);
    }

    @Override
    public Result updateMoneyPaid(TradeUserMoneyLog userMoneyLog) {
        //1.校验参数是否合法
        if(userMoneyLog==null ||
                userMoneyLog.getUserId()==null ||
                userMoneyLog.getOrderId()==null ||
                userMoneyLog.getUseMoney()==null||
                userMoneyLog.getUseMoney().compareTo(BigDecimal.ZERO)<=0){
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }

        //2.查询订单余额使用日志
        TradeUserMoneyLogExample userMoneyLogExample = new TradeUserMoneyLogExample();
        TradeUserMoneyLogExample.Criteria criteria = userMoneyLogExample.createCriteria();
        criteria.andOrderIdEqualTo(userMoneyLog.getOrderId());
        criteria.andUserIdEqualTo(userMoneyLog.getUserId());

        int r = userMoneyLogMapper.countByExample(userMoneyLogExample);

        TradeUser tradeUser = userMapper.selectById(userMoneyLog.getUserId());

        //3.扣减余额...
        if(userMoneyLog.getMoneyLogType().intValue()==ShopCode.SHOP_USER_MONEY_PAID.getCode().intValue()){
            if(r>0){
                //已经付款
                CastException.cast(ShopCode.SHOP_ORDER_PAY_STATUS_IS_PAY);
            }
            //减余额
            tradeUser.setUserMoney(new BigDecimal(tradeUser.getUserMoney()).subtract(userMoneyLog.getUseMoney()).longValue());
            userMapper.updateById(tradeUser);
        }
        //4.回退余额...
        if(userMoneyLog.getMoneyLogType().intValue()==ShopCode.SHOP_USER_MONEY_REFUND.getCode().intValue()){
            if(r<0){
                //如果没有支付,则不能回退余额
                CastException.cast(ShopCode.SHOP_ORDER_PAY_STATUS_NO_PAY);
            }
            //防止多次退款
            TradeUserMoneyLogExample userMoneyLogExample2 = new TradeUserMoneyLogExample();
            TradeUserMoneyLogExample.Criteria criteria1 = userMoneyLogExample2.createCriteria();
            criteria1.andOrderIdEqualTo(userMoneyLog.getOrderId());
            criteria1.andUserIdEqualTo(userMoneyLog.getUserId());
            criteria1.andMoneyLogTypeEqualTo(ShopCode.SHOP_USER_MONEY_REFUND.getCode());
            int r2 = userMoneyLogMapper.countByExample(userMoneyLogExample2);
            if(r2>0){
                CastException.cast(ShopCode.SHOP_USER_MONEY_REFUND_ALREADY);
            }
            //退款
            tradeUser.setUserMoney(new BigDecimal(tradeUser.getUserMoney()).add(userMoneyLog.getUseMoney()).longValue());
            userMapper.updateById(tradeUser);
        }
        //5.记录订单余额使用日志
        userMoneyLog.setCreateTime(new Date());
        userMoneyLogMapper.insert(userMoneyLog);
        return new Result(ShopCode.SHOP_SUCCESS.getSuccess(),ShopCode.SHOP_SUCCESS.getMessage());

    }
}
