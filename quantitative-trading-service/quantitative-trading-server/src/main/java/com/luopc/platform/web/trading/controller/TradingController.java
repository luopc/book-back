package com.luopc.platform.web.trading.controller;

import com.luopc.platform.base.util.SmartJsonUtil;
import com.luopc.platform.base.util.SmartNumIDUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by Robin
 * @className UserController
 * @date 2024/1/20 0020 13:03
 */
@RestController
public class TradingController {

    @GetMapping("example/get")
    public String getUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("id", SmartNumIDUtil.nextPkId());
        user.put("name", "Zhang san");
        return SmartJsonUtil.writeJson(user);
    }

    @GetMapping("example/list")
    public String getUserList() {
        List<Map<String, Object>> userList = new ArrayList<>();

        Map<String, Object> user = new HashMap<>();
        user.put("id", SmartNumIDUtil.nextPkId());
        user.put("name", "Zhang san");
        userList.add(user);

        user = new HashMap<>();
        user.put("id", SmartNumIDUtil.nextPkId());
        user.put("name", "Li si");
        userList.add(user);

        return SmartJsonUtil.writeJson(userList);
    }

}
