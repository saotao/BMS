package stao.BMS.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Dishonest extends BaseEntity {
    private Integer id;

    private Integer userId;

    private Long alarmLong;

    private Long alarmTime;

    private String alarmReason;

    private Byte alarmType;

    private Byte borrowStatue;
}