package com.vdegree.grampus.admin.modules.system.service.impl;

import com.vdegree.grampus.common.mybatis.service.impl.BaseServiceImpl;
import com.vdegree.grampus.admin.modules.system.dao.SysLogLoginDao;
import com.vdegree.grampus.admin.modules.system.entity.SysLogLogin;
import com.vdegree.grampus.admin.modules.system.service.SysLogLoginService;
import org.springframework.stereotype.Service;

/**
 * 用户登陆日志表 服务实现类
 *
 * @author Beck
 * @since 2020-12-09 19:49:00
 */
@Service("sysLogLoginService")
public class SysLogLoginServiceImpl extends BaseServiceImpl<SysLogLoginDao, SysLogLogin> implements SysLogLoginService {

}