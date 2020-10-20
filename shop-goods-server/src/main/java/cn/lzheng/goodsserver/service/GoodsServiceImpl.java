package cn.lzheng.goodsserver.service;

import cn.lzheng.api.GoodsService;
import cn.lzheng.constant.ShopCode;
import cn.lzheng.entity.Result;
import cn.lzheng.exception.CastException;
import cn.lzheng.goodsserver.mapper.TradeGoodsMapper;
import cn.lzheng.goodsserver.mapper.TradeGoodsNumberLogMapper;
import cn.lzheng.pojo.TradeGoods;
import cn.lzheng.pojo.TradeGoodsNumberLog;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName GoodsService
 * @Author 6yi
 * @Date 2020/10/16 1:24
 * @Version 1.0
 * @Description:
 */

@DubboService
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private TradeGoodsMapper goodsMapper;
    @Autowired
    private TradeGoodsNumberLogMapper goodsNumberLogMapper;

    @Override
    public TradeGoods findOne(Long goodsId) {
        if (goodsId == null) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        TradeGoods tradeGoods = goodsMapper.selectById(goodsId);
        System.out.println("goods:"+tradeGoods);
        return tradeGoods;
    }

    @Override
    public Result reduceGoodsNum(TradeGoodsNumberLog goodsNumberLog) {
        if (goodsNumberLog == null ||
                goodsNumberLog.getGoodsNumber() == null ||
                goodsNumberLog.getOrderId() == null ||
                goodsNumberLog.getGoodsNumber() == null ||
                goodsNumberLog.getGoodsNumber().intValue() <= 0) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        TradeGoods goods = goodsMapper.selectById(goodsNumberLog.getGoodsId());
        if(goods.getGoodsNumber()<goodsNumberLog.getGoodsNumber()){
            //库存不足
            CastException.cast(ShopCode.SHOP_GOODS_NUM_NOT_ENOUGH);
        }
        //减库存
        goods.setGoodsNumber(goods.getGoodsNumber()-goodsNumberLog.getGoodsNumber());
        goodsMapper.updateById(goods);


        //记录库存操作日志
        goodsNumberLog.setGoodsNumber(-(goodsNumberLog.getGoodsNumber()));
        goodsNumberLog.setLogTime(new Date());
        goodsNumberLogMapper.insert(goodsNumberLog);

        return new Result(ShopCode.SHOP_SUCCESS.getSuccess(),ShopCode.SHOP_SUCCESS.getMessage());
    }
}
