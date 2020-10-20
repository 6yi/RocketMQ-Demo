package cn.lzheng.couponserver.dao;


import cn.lzheng.pojo.TradeCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TradeCouponMapper extends BaseMapper<TradeCoupon> {

}