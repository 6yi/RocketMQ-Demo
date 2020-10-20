package cn.lzheng.goodsserver.mapper;


import cn.lzheng.pojo.TradeMqConsumerLog;
import cn.lzheng.pojo.TradeMqConsumerLogExample;
import cn.lzheng.pojo.TradeMqConsumerLogKey;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;



@Mapper
@Repository
public interface TradeMqConsumerLogMapper extends BaseMapper<TradeMqConsumerLog> {

    int updateByPrimaryKeySelective(TradeMqConsumerLog record);

    TradeMqConsumerLog selectByPrimaryKey(TradeMqConsumerLogKey key);

    int updateByExampleSelective(@Param("record") TradeMqConsumerLog record, @Param("example") TradeMqConsumerLogExample example);

}