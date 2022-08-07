package org.wyy.tech.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付表
 * </p>
 *
 * @author [mybatis plus generator]
 * @since 2022-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BizPayment implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 付款金额
     */
    private BigDecimal payAmount;

    /**
     * 付款状态
     */
    private String status;


}
