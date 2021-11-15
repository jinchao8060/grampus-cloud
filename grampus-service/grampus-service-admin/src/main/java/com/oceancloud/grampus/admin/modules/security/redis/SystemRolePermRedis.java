package com.oceancloud.grampus.admin.modules.security.redis;

import com.google.common.base.Joiner;
import com.oceancloud.grampus.framework.core.utils.BeanUtil;
import com.oceancloud.grampus.framework.core.utils.StringPool;
import com.oceancloud.grampus.framework.core.utils.StringUtil;
import com.oceancloud.grampus.framework.redis.utils.RedisCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 系统角色权限 Redis操作
 *
 * @author Beck
 * @since 2021-06-09
 */
@Component
@AllArgsConstructor
public class SystemRolePermRedis {

	private final RedisCache redisCache;

	/**
	 * 角色权限缓存
	 * KEY sys:role_perm:{roleId}
	 * VAL 权限标识String
	 */
	private static final String SYSTEM_ROLE_PERM_KEY = "sys:role_perm:{0}";

	public void saveSystemRolePerms(Long roleId, String perms) {
		String key = MessageFormat.format(SYSTEM_ROLE_PERM_KEY, roleId);
		redisCache.setEx(key, perms, Duration.ofHours(3));
	}

	public void removeSystemRolePerms(Long roleId) {
		String key = MessageFormat.format(SYSTEM_ROLE_PERM_KEY, roleId);
		redisCache.del(key);
	}

	public String getSystemRolePerms(Long roleId) {
		String key = MessageFormat.format(SYSTEM_ROLE_PERM_KEY, roleId);
		return BeanUtil.copy(redisCache.get(key), String.class);
	}

	public String getSystemRolePerms(List<Long> roleIds) {
		return Joiner.on(StringPool.COMMA).join(roleIds.stream().map(this::getSystemRolePerms)
				.filter(perms -> Objects.nonNull(perms) && StringUtil.isNotBlank(perms))
				.map(perms -> perms.trim().split(StringPool.COMMA))
				.flatMap(Arrays::stream)
				.collect(Collectors.toSet()));
	}
}
