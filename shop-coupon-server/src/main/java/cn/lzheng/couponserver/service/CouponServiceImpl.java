package cn.lzheng.couponserver.service;

import cn.lzheng.constant.ShopCode;
import cn.lzheng.couponserver.dao.TradeCouponMapper;
import cn.lzheng.entity.Result;
import cn.lzheng.exception.CastException;
import cn.lzheng.pojo.TradeCoupon;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CouponService
 * @Author 6yi
 * @Date 2020/10/19 15:57
 * @Version 1.0
 * @Description:
 */

@DubboService
@Service
public class CouponServiceImpl implements cn.lzheng.api.CouponService {
    @Autowired
    private TradeCouponMapper couponMapper;

    @Override
    public TradeCoupon findOne(Long coupouId) {
        if(coupouId==null){
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }

        return couponMapper.selectById(coupouId);
    }

    @Override
    public Result updateCouponStatus(TradeCoupon coupon) {
        if(coupon==null||coupon.getCouponId()==null){
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        //更新优惠券状态
        couponMapper.updateById(coupon);
        return new Result(ShopCode.SHOP_SUCCESS.getSuccess(),ShopCode.SHOP_SUCCESS.getMessage());
    }
}
