package com.dv16888.www.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dv16888.www.model.ReturnMessage;
import com.dv16888.www.service.Ip2LocationsService;
import com.dv16888.www.service.QueueService;
import com.dv16888.www.service.VisitService;
import com.dv16888.www.util.IpUtil;
import com.dv16888.www.vo.Ip2location;
import com.dv16888.www.vo.Queue;
import com.dv16888.www.vo.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class QueueController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private QueueService queueService;
    @Autowired
    private Ip2LocationsService ip2LocationsService;

    private static Long iptolong(String strip)
    //将127.0.0.1形式的ip地址转换成10进制整数，这里没有进行任何错误处理
    {
        int j=0;
        int i=0;
        long[] ip=new long[4];
        int position1=strip.indexOf(".");
        int position2=strip.indexOf(".",position1+1);
        int position3=strip.indexOf(".",position2+1);
        ip[0]=Long.parseLong(strip.substring(0,position1));
        ip[1]=Long.parseLong(strip.substring(position1+1,position2));
        ip[2]=Long.parseLong(strip.substring(position2+1,position3));
        ip[3]=Long.parseLong(strip.substring(position3+1));
        return(ip[0]<<24)+(ip[1]<<16)+(ip[2]<<8)+ip[3];//ip1*256*256*256+ip2*256*256+ip3*256+ip4
    }

    @PostMapping("/join")
    public String join(@RequestBody JSONObject jsonParam, HttpServletRequest request){
        String customerid = UUID.randomUUID().toString();   // 生成customerid
        String content = jsonParam.toJSONString();      // 获得预先得知的信息
        Date dt = new Date();       // 当前时间

        //获得ip
        String ip = IpUtil.getIpAddr(request);
        if(ip.equals("0:0:0:0:0:0:0:1")){
            ip = "127.0.0.1";
        }
        //查询国家，省，市，运营商
        Long decimal_ip =  iptolong(ip);
        System.out.println(decimal_ip);

        Ip2location ip2 = ip2LocationsService.getLocation(decimal_ip);

        //保存至 Visit
        Visit vt = new Visit();
        vt.setContent(content);
        vt.setCreated_at(dt);
        vt.setCustomerid(customerid);
        visitService.save(vt);

        //保存至 Queue
        Queue qe = new Queue();
        Byte b = 0;
        qe.setActive(b);
        qe.setCreatedat(dt);
        qe.setCustomerid(customerid);
        qe.setIp(ip);
        qe.setCsid(0);
        qe.setCountry(ip2.getCountry());
        qe.setProvince(ip2.getProvince());
        qe.setCity(ip2.getCity());
        qe.setIsp(ip2.getIsp());
        queueService.save(qe);

        //保存customerid 到session
        //第一步：获取session
        HttpSession session = request.getSession();
        //第二步：将想要保存到数据存入session中
        session.setAttribute("customerid",customerid);

        ReturnMessage rm = new ReturnMessage();
        rm.setCode("1");
        rm.setData("");
        rm.setMessage("已加入队列");
        String welcomeMessage = JSON.toJSONString(rm);

        return welcomeMessage;
    }

    /**
     * list queue of waiting
     * return json string
     */
    @GetMapping("/listqueue")
    public String listqueue(){
        List<Queue> lists = queueService.showListQueue();
        String str = JSON.toJSONString(lists);

        return str;
    }


}
