package com.oceancloud.grampus.admin.modules.system.service.impl;

import com.oceancloud.grampus.admin.modules.system.dao.SysParamDao;
import com.oceancloud.grampus.admin.modules.system.dto.SysParamDTO;
import com.oceancloud.grampus.admin.modules.system.entity.SysParam;
import com.oceancloud.grampus.admin.modules.system.service.SysParamService;
import com.oceancloud.grampus.framework.mybatis.service.impl.EnhancedBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 公共参数表 服务实现类
 *
 * @author Beck
 * @since 2020-12-09
 */
@Service("sysParamService")
public class SysParamServiceImpl extends EnhancedBaseServiceImpl<SysParamDao, SysParam, SysParamDTO> implements SysParamService {

}