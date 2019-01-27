package stao.BMS.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import stao.BMS.entity.vo.ResponseResult;
import stao.BMS.enu.ResultEnum;
import stao.BMS.service.impl.RedisService;
import stao.BMS.utils.ConfigProperties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AccessTokenAspect {

    @Autowired
    private RedisService redisService;

    @Around("@annotation(stao.BMS.aop.AccessToken)")
    public Object doAccessCheck(ProceedingJoinPoint pjp) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = "";
        Cookie[] cookies = request.getCookies();
        String token = "";
        for(Cookie cookie : cookies){
            if("userName".equals(cookie.getName())){
                userName = cookie.getValue();
            }
            if("token".equals(cookie.getName())){
                token = cookie.getValue();
            }
        }

        boolean verify = redisService.checkToken(ConfigProperties.tokenKey+userName,token);
        if(verify){
            Object object = pjp.proceed(); //执行连接点方法
            //获取执行方法的参数
            return new ResponseResult<>(ResultEnum.SUCCESS.getCode(),object,ResultEnum.SUCCESS.getMsg());
        }else {
            return new ResponseResult<>(ResultEnum.TOKEN_PASS.getCode(),ResultEnum.TOKEN_PASS.getMsg());
        }
    }
}
