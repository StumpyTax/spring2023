package com.spring2023.stax.extern.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="AuthClient", url = "${auth}"+"/auth")
public interface AuthClient {

    @PostMapping("/validate")
    String validate(@RequestParam String token);

}
