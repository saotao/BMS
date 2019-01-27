package stao.BMS.dao;

import org.apache.ibatis.annotations.Mapper;
import stao.BMS.entity.Borrow;

@Mapper
public interface BorrowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    Borrow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);
}