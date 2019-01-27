package stao.BMS.enu;

import lombok.Getter;

@Getter
public enum  ResultEnum {

    SUCCESS(200,"成功"),
    FAIL(100,"失败"),
    TOKEN_PASS(401,"token过期"),
    REGISTER_USER_NAME_ERROR(560,"用户名已存在"),
    REGISTER_USER_PHONE_ERROR(561,"用户电话已存在"),
    REGISTER_USER_EMAIL_ERROR(562,"用户邮箱已存在"),
    REGISTER_VALID_ERROR(563,"您已激活或者验证码错误"),

    LOGIN_FAIL(570,"用户名或密码错误"),
    ;

    private int code;
    private String msg;

    ResultEnum(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
