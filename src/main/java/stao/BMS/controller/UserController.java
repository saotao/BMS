package stao.BMS.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stao.BMS.aop.AccessToken;
import stao.BMS.entity.User;
import stao.BMS.entity.po.UserRegisterPo;
import stao.BMS.entity.vo.ResponseResult;
import stao.BMS.enu.ResultEnum;
import stao.BMS.service.UserService;
import stao.BMS.service.impl.RedisService;
import stao.BMS.utils.ConfigProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody User user, HttpServletResponse response){
        try {
            log.info("user:{}",user.toString());
            return userService.login(user,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult(ResultEnum.FAIL.getCode(),ResultEnum.FAIL.getMsg());
    }

    @PostMapping(value = "/register",produces = "application/json")
    public ResponseResult register(@Validated UserRegisterPo user, BindingResult result, HttpServletRequest request,HttpServletResponse response){
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return new ResponseResult(ResultEnum.REGISTER_VALID_ERROR.getCode(),errors,ResultEnum.REGISTER_VALID_ERROR.getMsg());
        }
        try {
            log.info("userPo:{}",user.toString());
            response.setContentType("text/html;charset=UTF-8");
            return userService.register(user,request);
        }catch (Exception e){
            log.info("stao.BMS.controller.UserController.register:",e);
        }
        return null;
    }

    @RequestMapping(value = "/checkToken")
    public ResponseResult checkToken(@RequestParam("email")String email,@RequestParam("token")String token){

        String s = redisService.get(ConfigProperties.registerTokenCheck + email);
        if(s!=null&&s.equals(token)){
            redisService.delete(ConfigProperties.registerTokenCheck+email);
            userService.updateUserStatus(email);
            return new ResponseResult(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
        }
        return new ResponseResult(ResultEnum.REGISTER_VALID_ERROR.getCode(),ResultEnum.REGISTER_VALID_ERROR.getMsg());
    }

    @RequestMapping(value = "/forgetPass")
    @AccessToken
    public ResponseResult forgetPass(@RequestParam("email")String userName){
        String s = redisService.get(ConfigProperties.tokenKey + userName);
        return new ResponseResult(ResultEnum.REGISTER_VALID_ERROR.getCode(),s,ResultEnum.REGISTER_VALID_ERROR.getMsg());
    }
}
