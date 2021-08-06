package com.vdegree.grampus.admin.modules.testtx.entity;

import com.vdegree.grampus.common.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.util.Date;

/**
 * (TestTx) 表实体类
 *
 * @author Beck
 * @since 2021-06-21 17:13:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "test_tx")
public class TestTx extends BaseEntity {
	private static final long serialVersionUID = 869764906649304240L;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 时间
	 */
	private Date time;
}
