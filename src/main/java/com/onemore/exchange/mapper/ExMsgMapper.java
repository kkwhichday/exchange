package com.onemore.exchange.mapper;

import com.onemore.exchange.model.ExMsg;
import com.onemore.exchange.model.ExMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExMsgMapper {
    int countByExample(ExMsgExample example);

    int deleteByExample(ExMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExMsg record);

    int insertSelective(ExMsg record);

    List<ExMsg> selectByExample(ExMsgExample example);

    ExMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExMsg record, @Param("example") ExMsgExample example);

    int updateByExample(@Param("record") ExMsg record, @Param("example") ExMsgExample example);

    int updateByPrimaryKeySelective(ExMsg record);

    int updateByPrimaryKey(ExMsg record);
}