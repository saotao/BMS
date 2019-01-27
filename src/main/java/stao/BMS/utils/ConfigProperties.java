package stao.BMS.utils;

public class ConfigProperties {

    public static final String tokenKey="stao_bms_user_token_";

    public static final int TOKEN_PASS=60*30*1000;

    public static final String salt = "staoq;qfw/quifma*@#$^!_+ORW";

    /*发邮件*/
    public static final String emailFrom="stao0246@qq.com";
    public static final String emailHost="smtp.qq.com";
    public static final String emailToken="qtmvrsdxhzinbdah";
    public static final String registerBody="<p>请点击下面链接激活账户</p><br>";
    public static final String registerSubject="激活邮件";
    public static final String checkToken="http://localhost:8080/user/checkToken";


    /*redis前缀*/

    public static final String registerTokenCheck="bms_sign_";

}
