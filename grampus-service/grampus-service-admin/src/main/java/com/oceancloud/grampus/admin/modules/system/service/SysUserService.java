package com.oceancloud.grampus.admin.modules.system.service;

import com.oceancloud.grampus.admin.modules.system.dto.SysUserDTO;
import com.oceancloud.grampus.admin.modules.system.entity.SysUser;
import com.oceancloud.grampus.framework.mybatis.service.EnhancedBaseService;

/**
 * 用户表 服务接口
 *
 * @author Beck
 * @since 2020-12-09
 */
public interface SysUserService extends EnhancedBaseService<SysUser, SysUserDTO> {

	/**
	 * 根据会员号获取会员数据
	 *
	 * @param userNo 会员号
	 * @return 会员实体
	 */
	SysUser getSysUserByUserNo(String userNo);

	/**
	 * 修改用户代码
	 *
	 * @param userId      用户ID
	 * @param newPassword 新密码
	 */
	void updatePassword(Long userId, String newPassword);
}