package stao.BMS.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stao.BMS.dao.UserMapper;
import stao.BMS.entity.User;
import stao.BMS.entity.po.UserRegisterPo;
import stao.BMS.entity.vo.ResponseResult;
import stao.BMS.enu.ResultEnum;
import stao.BMS.service.UserService;
import stao.BMS.utils.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisService redisService;


    @Override
    public ResponseResult login(User user, HttpServletResponse response) throws Exception{

        String userName = user.getUserName();
        String password = user.getUserPwd();

        int i = userMapper.selectByUserNamePass(userName, password);
        if(i>0) {
            String token = TokenUtil.createToken(userName);
            redisService.setExpire(ConfigProperties.tokenKey+userName,token,ConfigProperties.TOKEN_PASS, TimeUnit.MILLISECONDS);
            Cookie userNameCookie = new Cookie("userName",user.getUserName());
            Cookie tokenCookie = new Cookie("token",token);
            response.addCookie(userNameCookie);
            response.addCookie(tokenCookie);
            return new ResponseResult(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
        }else {
            return new ResponseResult(ResultEnum.LOGIN_FAIL.getCode(),ResultEnum.LOGIN_FAIL.getMsg());
        }
    }

    @Override
    public ResponseResult register(UserRegisterPo user, HttpServletRequest request) {
        //判断：userName,userPhone,userEmail
        String userName = user.getUserName();
        String userEmail = user.getUserEmail();
        String userPhone = user.getUserPhone();

        if(userMapper.selectByUserNEP(userName,1,0,0)>0){
            return new ResponseResult(ResultEnum.REGISTER_USER_NAME_ERROR.getCode(),ResultEnum.REGISTER_USER_NAME_ERROR.getMsg());
        }

        if(userMapper.selectByUserNEP(userEmail,0,1,0)>0){
            return new ResponseResult(ResultEnum.REGISTER_USER_EMAIL_ERROR.getCode(),ResultEnum.REGISTER_USER_EMAIL_ERROR.getMsg());
        }

        if(userMapper.selectByUserNEP(userPhone,0,0,1)>0){
            return new ResponseResult(ResultEnum.REGISTER_USER_PHONE_ERROR.getCode(),ResultEnum.REGISTER_USER_PHONE_ERROR.getMsg());
        }
        String psw = EncryptUtil.getInstance().MD5(user.getUserPwd());
        String upload = UploadUtil.upload(user.getImgFile(), request);

        User insertUser = new User();
        insertUser.setUserName(userName);
        Date birth = DateUtil.parseDate(user.getUserBirth(), "yyyy-MM-dd");
        insertUser.setUserBirth(birth);
        insertUser.setUserEmail(userEmail);
        insertUser.setUserImg(upload);
        insertUser.setUserPhone(userPhone);
        insertUser.setUserPwd(psw);
        insertUser.setUserRealName(user.getUserRealName());
        insertUser.setStatus((byte) 0);

        try {
            userMapper.insertSelective(insertUser);
        }catch (Exception e){
            log.error("stao.BMS.service.impl.UserServiceImpl.register():",e);
            return new ResponseResult(ResultEnum.FAIL.getCode(),ResultEnum.FAIL.getMsg());
        }
        String token = EncryptUtil.getUUID();
        redisService.set(ConfigProperties.registerTokenCheck+userEmail,token);
        String body = ConfigProperties.registerBody+"<a>"+ConfigProperties.checkToken+"?email="+userEmail+"&token="+token+"</a>";
        int i = SendEmailUtil.sendEmail(new String[]{userEmail}, ConfigProperties.registerSubject, body);
        int count = 0;
        while (i!=0&&count<3){
            i = SendEmailUtil.sendEmail(new String[]{userEmail}, ConfigProperties.registerSubject, body);
            count++;
        }
        if(i==0) return new ResponseResult(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
        else return new ResponseResult(ResultEnum.FAIL.getCode(),ResultEnum.FAIL.getMsg());
    }

    @Override
    public ResponseResult forgetPass(User user) {
        return null;
    }

    @Override
    public void updateUserStatus(String email) {
        userMapper.updateUserStatus(email);
    }
}
