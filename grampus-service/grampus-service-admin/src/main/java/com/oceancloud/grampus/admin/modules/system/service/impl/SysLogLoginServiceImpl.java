package com.oceancloud.grampus.admin.modules.system.service.impl;

import com.oceancloud.grampus.admin.modules.system.dao.SysLogLoginDao;
import com.oceancloud.grampus.admin.modules.system.entity.SysLogLogin;
import com.oceancloud.grampus.admin.modules.system.service.SysLogLoginService;
import com.oceancloud.grampus.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户登陆日志表 服务实现类
 *
 * @author Beck
 * @since 2020-12-09
 */
@Service("sysLogLoginService")
public class SysLogLoginServiceImpl extends BaseServiceImpl<SysLogLoginDao, SysLogLogin> implements SysLogLoginService {

}