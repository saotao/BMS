package stao.BMS.entity.po;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterPo {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "用户密码不能为空")
    private String userPwd;

    private MultipartFile imgFile;

    @NotBlank(message = "真实名称不能为空")
    private String userRealName;

    @NotBlank(message = "生日不能为空")
    private String userBirth;

    @NotBlank(message = "非法邮箱")
    private String userEmail;

    @NotBlank(message = "电话不能为空")
    private String userPhone;

    @Override
    public String toString() {
        return "UserRegisterPo{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", imgFile=" + imgFile.getOriginalFilename() +
                ", userRealName='" + userRealName + '\'' +
                ", userBirth=" + userBirth +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
