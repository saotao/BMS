package stao.BMS.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Alarm extends BaseEntity {
    private Integer id;

    private Integer borrowId;

    private Long alarmTime;

    private Long alarmLong;

    private Byte alarmType;

    private String alarmReason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}