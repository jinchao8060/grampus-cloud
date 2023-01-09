package com.oceancloud.grampus.admin.config;

import com.oceancloud.grampus.framework.oauth2.modules.system.utils.SystemSecurityUtils;
import com.oceancloud.grampus.framework.core.utils.ReflectUtil;
import com.oceancloud.grampus.framework.mybatis.annotation.FieldFill;
import com.oceancloud.grampus.framework.mybatis.annotation.TableField;
import com.oceancloud.grampus.framework.mybatis.handler.FieldFillHandler;
import com.oceancloud.grampus.framework.mybatis.interceptor.FieldFillInterceptor;
import com.oceancloud.grampus.framework.mybatis.interceptor.TableFieldObject;
import org.apache.ibatis.mapping.SqlCommandType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mybatis配置类
 *
 * @author Beck
 * @since 2021-04-26
 */
@Configuration
@MapperScan(basePackages = "com.oceancloud.**.dao")
public class MybatisConfig {

	@Bean
	public FieldFillInterceptor fieldFillInterceptor(FieldFillHandler fieldFillHandler) {
		return new FieldFillInterceptor(fieldFillHandler);
	}

	/**
	 * 字段自动填充处理器
	 */
	@Component
	public static class FieldAutoFillHandler implements FieldFillHandler {
		private final static String CREATE_BY = "createBy";
		private final static String CREATE_DATE = "createDate";
		private final static String UPDATE_BY = "updateBy";
		private final static String UPDATE_DATE = "updateDate";

		@Override
		public void fill(TableFieldObject tableFieldObject) {
			String currentUserNo = Objects.requireNonNull(SystemSecurityUtils.getUserDetails()).getUserNo();
			LocalDateTime currentDate = LocalDateTime.now();

			SqlCommandType sqlCommandType = tableFieldObject.getSqlCommandType();
			Object paramObj = tableFieldObject.getParamObj();
			List<Field> fields = tableFieldObject.getFields();
			Map<FieldFill, List<Field>> fillFieldMap = fields.stream()
					.collect(Collectors.groupingBy(field -> field.getAnnotation(TableField.class).fill()));

			boolean isInsertSql = SqlCommandType.INSERT.equals(sqlCommandType);
			boolean isUpdateSql = SqlCommandType.UPDATE.equals(sqlCommandType);
			for (Map.Entry<FieldFill, List<Field>> fillFieldEntry : fillFieldMap.entrySet()) {
				FieldFill fill = fillFieldEntry.getKey();
				boolean withInsertFill = FieldFill.INSERT.equals(fill) || FieldFill.INSERT_UPDATE.equals(fill);
				boolean withUpdateFill = FieldFill.UPDATE.equals(fill) || FieldFill.INSERT_UPDATE.equals(fill);
				for (Field field : fillFieldEntry.getValue()) {
					if (isInsertSql && withInsertFill) {
						// INSERT SQL
						this.insertFillIfNull(paramObj, field, currentUserNo, currentDate);
					} else if (isUpdateSql && withUpdateFill) {
						// UPDATE SQL
						this.updateFillIfNull(paramObj, field, currentUserNo, currentDate);
					}
				}
			}
		}

		private void insertFillIfNull(Object paramObj, Field field, String currentUserNo, LocalDateTime currentDate) {
			if (Objects.nonNull(ReflectUtil.getField(field, paramObj))) {
				return;
			}
			if (CREATE_BY.equals(field.getName()) || UPDATE_BY.equals(field.getName())) {
				ReflectUtil.setField(field, paramObj, currentUserNo);
			} else if (CREATE_DATE.equals(field.getName()) || UPDATE_DATE.equals(field.getName())) {
				ReflectUtil.setField(field, paramObj, currentDate);
			}
		}

		private void updateFillIfNull(Object paramObj, Field field, String currentUserNo, LocalDateTime currentDate) {
			if (Objects.nonNull(ReflectUtil.getField(field, paramObj))) {
				return;
			}
			if (UPDATE_BY.equals(field.getName())) {
				ReflectUtil.setField(field, paramObj, currentUserNo);
			} else if (UPDATE_DATE.equals(field.getName())) {
				ReflectUtil.setField(field, paramObj, currentDate);
			}
		}
	}

}
