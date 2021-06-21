package com.vdegree.grampus.auth.modules.system.service;

import com.vdegree.grampus.admin.modules.system.client.dto.TestTxDTO;
import com.vdegree.grampus.admin.modules.system.client.feign.RemoteTestTxClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * TestTxService
 *
 * @author Beck
 * @since 2021-06-21
 */
@AllArgsConstructor
@Service
public class TestTxService {

	private final RemoteTestTxClient remoteTestTxClient;

	@GlobalTransactional
	public void update(TestTxDTO testTxDTO) {
		remoteTestTxClient.update(testTxDTO);
		if (testTxDTO.getName().equals("break2")) {
			throw new IllegalArgumentException("break2");
		}
	}

}
