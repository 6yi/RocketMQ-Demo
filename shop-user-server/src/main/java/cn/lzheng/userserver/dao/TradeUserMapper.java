package cn.lzheng.userserver.dao;


import cn.lzheng.pojo.TradeUser;
import cn.lzheng.pojo.TradeUserExample;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TradeUserMapper extends BaseMapper<TradeUser> {

}