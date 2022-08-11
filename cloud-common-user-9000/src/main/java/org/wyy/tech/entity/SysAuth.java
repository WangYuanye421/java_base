package org.wyy.tech.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.wyy.tech.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wyy
 * @since 2022-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysAuth extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限定义
     */
    private String permission;

    /**
     * 资源路径
     */
    private String url;


}
