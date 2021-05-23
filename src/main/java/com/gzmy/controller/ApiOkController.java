package com.gzmy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dravin
 * @date 2021/5/23 上午5:06
 */
@RequestMapping("api")
@RestController
public class ApiOkController {
    @GetMapping("/ok")
    public String apiOk() {
        return "success";
    }
}
