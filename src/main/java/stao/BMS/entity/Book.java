package stao.BMS.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Book extends BaseEntity {
    private Integer id;

    private String bookName;

    private String bookAuther;

    private String bookRemark;

    private Integer bookPageSize;

    private Integer bookWordSize;

    private BigDecimal bookPrice;

    private Date bookPublish;

    private Long bookPublishTime;

    private Byte bookType;

    private Integer bookStorage;
}
