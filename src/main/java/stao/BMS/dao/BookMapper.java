package stao.BMS.dao;

import org.apache.ibatis.annotations.Mapper;
import stao.BMS.entity.Book;

@Mapper
public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}