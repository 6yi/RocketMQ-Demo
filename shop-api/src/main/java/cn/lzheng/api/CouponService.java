package cn.lzheng.api;

import cn.lzheng.entity.Result;
import cn.lzheng.pojo.TradeCoupon;

/**
 * @ClassName CouponService
 * @Author 6yi
 * @Date 2020/10/19 15:56
 * @Version 1.0
 * @Description:
 */

public interface CouponService {
    /**
     * 根据ID查询优惠券对象
     * @param coupouId
     * @return
     */
    public TradeCoupon findOne(Long coupouId);

    /**
     * 更细优惠券状态
     * @param coupon
     * @return
     */
    Result updateCouponStatus(TradeCoupon coupon);
}
