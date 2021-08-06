package com.vdegree.grampus.admin.modules.system.service.impl;

import com.vdegree.grampus.admin.modules.system.dto.SysMenuDTO;
import com.vdegree.grampus.admin.modules.system.enums.MenuTypeEnum;
import com.vdegree.grampus.admin.modules.system.enums.SuperAdminEnum;
import com.vdegree.grampus.admin.modules.system.service.SysLanguageService;
import com.vdegree.grampus.common.auth.modules.system.users.SystemUserDetails;
import com.vdegree.grampus.common.core.utils.CollectionUtil;
import com.vdegree.grampus.common.core.utils.ObjectUtil;
import com.vdegree.grampus.common.core.utils.WebUtil;
import com.vdegree.grampus.common.core.utils.chars.StringPool;
import com.vdegree.grampus.common.core.utils.tree.TreeUtils;
import com.vdegree.grampus.common.core.utils.BeanUtil;
import com.vdegree.grampus.admin.modules.system.dao.SysMenuDao;
import com.vdegree.grampus.admin.modules.system.entity.SysMenu;
import com.vdegree.grampus.admin.modules.system.service.SysMenuService;
import com.vdegree.grampus.common.core.utils.StringUtil;
import com.vdegree.grampus.common.mybatis.service.impl.EnhancedBaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单表 服务实现类
 *
 * @author Beck
 * @since 2020-12-09 19:49:31
 */
@AllArgsConstructor
@Service("sysMenuService")
public class SysMenuServiceImpl extends EnhancedBaseServiceImpl<SysMenuDao, SysMenu, SysMenuDTO> implements SysMenuService {

	private final SysLanguageService sysLanguageService;

	@Override
	public List<SysMenuDTO> getMenuList(Integer type) {
		Example.Builder exampleBuilder = Example.builder(SysMenu.class).orderBy("sort", "id");
		if (ObjectUtil.isNotEmpty(type)) {
			exampleBuilder = exampleBuilder.where(WeekendSqls.<SysMenu>custom().andEqualTo(SysMenu::getType, type));
		}
		List<SysMenu> menuList = baseMapper.selectByExample(exampleBuilder.build());
		convertLanguage(menuList);
		return BeanUtil.copyList(menuList, SysMenuDTO.class);
	}

	@Override
	public List<SysMenuDTO> getUserMenuList(SystemUserDetails userDetail, Integer type) {
		List<SysMenuDTO> sysMenuList;
		boolean isSuperAdmin = SuperAdminEnum.TRUE.getValue().equals(userDetail.getSuperAdmin());
		if (Boolean.TRUE.equals(isSuperAdmin)) {
			sysMenuList = this.getMenuList(type);
		} else {
			List<SysMenu> menuList = baseMapper.queryUserMenuList(userDetail.getId(), type);
			convertLanguage(menuList);
			sysMenuList = BeanUtil.copyList(menuList, SysMenuDTO.class);
		}
		return TreeUtils.build(sysMenuList);
	}

	@Override
	public List<SysMenuDTO> getUserMenuNavList(SystemUserDetails userDetail) {
		return this.getUserMenuList(userDetail, MenuTypeEnum.MENU.getValue());
	}

	@Override
	public Set<String> getUserPermissions(SystemUserDetails userDetail) {
		return Arrays.stream(userDetail.getPermissions().trim().split(StringPool.COMMA))
				.filter(permission -> Objects.nonNull(permission)
						&& StringUtil.isNotBlank(permission))
				.collect(Collectors.toSet());
	}

	@Override
	public List<SysMenuDTO> getListByPid(Long pid) {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setParentId(pid);
		List<SysMenu> menuList = baseMapper.select(sysMenu);
		convertLanguage(menuList);
		return BeanUtil.copyList(menuList, SysMenuDTO.class);
	}

	/**
	 * SysMenu的menu_name字段适配多语言 TODO 多语言转换代码统一封装
	 *
	 * @param list 需要转换语言的SysMenu
	 */
	private void convertLanguage(List<SysMenu> list) {
		List<Long> ids = list.stream().map(SysMenu::getId).distinct().collect(Collectors.toList());
		Map<Long, String> fieldValueMap = sysLanguageService.convertFieldValue("sys_menu", ids, "menu_name", WebUtil.getAcceptLanguage());
		if (CollectionUtil.isNotEmpty(fieldValueMap)) {
			for (SysMenu sysMenu : list) {
				String menuName = fieldValueMap.get(sysMenu.getId());
				if (StringUtil.isNotBlank(menuName)) {
					sysMenu.setMenuName(menuName);
				}
			}
		}
	}
}