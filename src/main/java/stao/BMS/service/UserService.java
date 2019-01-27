package stao.BMS.service;

import org.springframework.stereotype.Service;
import stao.BMS.entity.User;
import stao.BMS.entity.po.UserRegisterPo;
import stao.BMS.entity.vo.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface UserService {

    ResponseResult login(User user, HttpServletResponse response) throws Exception;

    ResponseResult register(UserRegisterPo user, HttpServletRequest request);

    ResponseResult forgetPass(User user);

    void updateUserStatus(String email);
}
