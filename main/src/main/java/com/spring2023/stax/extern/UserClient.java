package com.spring2023.stax.extern;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="userClient", url = "${external.url]")
public interface UserClient {

}
