package stao.BMS.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import stao.BMS.entity.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectByUserNEP(@Param("userNEP")String userNEP, @Param("name") int name, @Param("email")int email, @Param("phone")int phone);

    void updateUserStatus(String email);

    int selectByUserNamePass(@Param("userName") String userName, @Param("password") String password);
}
