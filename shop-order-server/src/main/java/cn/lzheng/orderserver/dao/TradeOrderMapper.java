package cn.lzheng.orderserver.dao;

import cn.lzheng.pojo.TradeOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TradeOrderMapper extends BaseMapper<TradeOrder> {

}