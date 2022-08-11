package org.wyy.tech.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang.StringUtils;
import org.wyy.tech.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author wyy
 * @since 2022-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 加密盐
     */
    private String salt;

    public void paramCheck(){
        if (StringUtils.isBlank(this.username)) {
            throw new RuntimeException("用户名不能为空");
        }
        if (StringUtils.isBlank(this.pwd)) {
            throw new RuntimeException("密码不能为空");
        }
    }

}
