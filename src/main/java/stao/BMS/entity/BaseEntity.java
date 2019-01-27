package stao.BMS.entity;

import lombok.Data;
import java.util.Date;

@Data
public class BaseEntity {

    private Byte status;

    private Date createTime;

    private Date updateTime;
}
