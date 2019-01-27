package stao.BMS.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import stao.BMS.entity.Book;
import stao.BaseTest;

@Slf4j
public class UserMapperTest extends BaseTest {


    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Test
    public void test(){
        Book user = bookMapper.selectByPrimaryKey(1);

        System.out.println(user.toString());
    }
}
