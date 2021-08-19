package com.oceancloud.grampus.admin.modules.system.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 参数管理
 *
 * @author Beck
 * @since 2021-04-13
 */
@Setter
@Getter
@ToString
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class SysParamExcel {
	/**
	 * 参数编码
	 */
	@ExcelProperty("参数编码")
	private String code;
	/**
	 * 参数值
	 */
	@ExcelProperty("参数值")
	private String value;
	/**
	 * 备注
	 */
	@ExcelProperty("备注")
	private String remark;
}