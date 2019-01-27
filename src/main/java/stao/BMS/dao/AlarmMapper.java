package stao.BMS.dao;

import org.apache.ibatis.annotations.Mapper;
import stao.BMS.entity.Alarm;

@Mapper
public interface AlarmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Alarm record);

    int insertSelective(Alarm record);

    Alarm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);
}