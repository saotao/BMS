package stao.BMS.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {
    private Integer id;

    private String userName;

    private String userPwd;

    private String userImg;

    private String userRealName;

    private Date userBirth;

    private String userEmail;

    private String userPhone;

    private Byte role;

    public User() {
    }
}
