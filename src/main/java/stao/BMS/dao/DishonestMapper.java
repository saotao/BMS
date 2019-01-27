package stao.BMS.dao;

import org.apache.ibatis.annotations.Mapper;
import stao.BMS.entity.Dishonest;

@Mapper
public interface DishonestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dishonest record);

    int insertSelective(Dishonest record);

    Dishonest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dishonest record);

    int updateByPrimaryKey(Dishonest record);
}