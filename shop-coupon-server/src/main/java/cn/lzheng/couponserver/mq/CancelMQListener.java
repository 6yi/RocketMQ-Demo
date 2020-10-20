package cn.lzheng.couponserver.mq;


import cn.lzheng.constant.ShopCode;
import cn.lzheng.couponserver.dao.TradeCouponMapper;
import cn.lzheng.entity.MQEntity;
import cn.lzheng.pojo.TradeCoupon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;


@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.order.topic}", consumerGroup = "${mq.order.consumer.group.name}", messageModel = MessageModel.BROADCASTING)
public class CancelMQListener implements RocketMQListener<MessageExt> {


    @Autowired
    private TradeCouponMapper couponMapper;

    @Override
    public void onMessage(MessageExt message) {

        try {
            //1. 解析消息内容
            String body = new String(message.getBody(), "UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            MQEntity mqEntity = objectMapper.readValue(body, MQEntity.class);
            log.info("接收到消息");
            if(mqEntity.getCouponId()!=null){
                //2. 查询优惠券信息
                TradeCoupon coupon = couponMapper.selectById(mqEntity.getCouponId());
                //3.更改优惠券状态
                coupon.setUsedTime(null);
                coupon.setIsUsed(ShopCode.SHOP_COUPON_UNUSED.getCode());
                coupon.setOrderId(null);
                couponMapper.updateById(coupon);
            }
            log.info("回退优惠券成功");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("回退优惠券失败");
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
