package org.wyy.tech.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wyy.tech.entity.SysRoleAuth;
import org.wyy.tech.mapper.SysRoleAuthMapper;
import org.wyy.tech.service.ISysRoleAuthService;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author wyy
 * @since 2022-08-11
 */
@Service
public class SysRoleAuthService extends ServiceImpl<SysRoleAuthMapper, SysRoleAuth> implements ISysRoleAuthService {

}
