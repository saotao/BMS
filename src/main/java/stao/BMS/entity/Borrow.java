package stao.BMS.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Borrow extends BaseEntity {
    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Long borrowTime;

    private Byte borrowStatue;
}