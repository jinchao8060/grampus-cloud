package com.oceancloud.grampus.admin.modules.system.client.dto;

import lombok.Data;

/**
 * TestDTO
 *
 * @author Beck
 * @since 2022-03-11
 */
@Data
public class TestDTO {

	private Long id;

	private String status;

	public TestDTO(Long id, String status) {
		this.id = id;
		this.status = status;
	}
}
