package com.oceancloud.grampus.admin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.oceancloud.grampus.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关联表 实体类
 *
 * @author Beck
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
public class SysUserRole extends BaseEntity {
	private static final long serialVersionUID = -32651746651171969L;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 角色ID
	 */
	private Long roleId;
}