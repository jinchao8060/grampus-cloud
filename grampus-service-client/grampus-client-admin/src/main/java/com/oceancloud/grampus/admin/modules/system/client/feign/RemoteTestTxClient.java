package com.oceancloud.grampus.admin.modules.system.client.feign;

import com.oceancloud.grampus.admin.modules.system.client.dto.TestTxDTO;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.mybatis.page.PageData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(contextId="remoteTestTxClient", value = "grampus-service-admin", path = "/rmi/admin/testTx")
public interface RemoteTestTxClient {

	@GetMapping("page")
	Result<PageData<TestTxDTO>> page(@RequestParam Map<String, Object> params);

	@GetMapping("{id}")
	Result<TestTxDTO> get(@PathVariable("id") Long id);

	@PostMapping
	Result<Void> save(@RequestBody TestTxDTO params);

	@PutMapping
	Result<Void> update(@RequestBody TestTxDTO params);

	@DeleteMapping("/{id}")
	Result<Void> delete(@PathVariable("id") Long id);
}
