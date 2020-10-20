package cn.lzheng.pojo;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class TradeGoodsNumberLogKey implements Serializable {
    @TableId
    private Long goodsId;

    private Long orderId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}