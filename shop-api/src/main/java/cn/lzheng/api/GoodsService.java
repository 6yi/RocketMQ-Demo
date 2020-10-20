package cn.lzheng.api;

import cn.lzheng.entity.Result;
import cn.lzheng.pojo.TradeGoods;
import cn.lzheng.pojo.TradeGoodsNumberLog;

/**
 * @ClassName GoodsService
 * @Author 6yi
 * @Date 2020/10/16 1:22
 * @Version 1.0
 * @Description:
 */
public interface GoodsService {
    /**
     * 根据ID查询商品对象
     * @param goodsId
     * @return
     */
    TradeGoods findOne(Long goodsId);
    /**
     * 扣减库存
     * @param goodsNumberLog
     * @return
     */
    Result reduceGoodsNum(TradeGoodsNumberLog goodsNumberLog);
}
