package cn.lzheng.goodsserver.mapper;


import cn.lzheng.pojo.TradeGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TradeGoodsMapper extends BaseMapper<TradeGoods> {

}