package com.dv16888.www;

import com.dv16888.www.util.IpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Quick {
    @GetMapping("/quick")
    @ResponseBody
    public String quick(HttpServletRequest request) {
        //获得用户ip

        return IpUtil.getIpAddr(request);
    }
}
