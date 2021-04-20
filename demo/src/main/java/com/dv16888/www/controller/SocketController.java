package com.dv16888.www.controller;

import com.alibaba.fastjson.JSON;
import com.dv16888.www.model.SendMessage;
import com.dv16888.www.repository.QueueRepository;
import com.dv16888.www.service.QueueService;
import com.dv16888.www.service.WebSocketServer;
import com.dv16888.www.vo.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SocketController {

    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private QueueService queueService;

    //客服首页
    @GetMapping("/")
    public String home() {

        return "customer";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    //等待界面
    @RequestMapping("/waitqueue")
    public String waitqueue(HttpServletRequest request, Model model) {
        //从session里得到 customerid
        String customerid = (String) request.getSession().getAttribute("customerid");

        //如果customerid为空，则跳回 "/"
        if(customerid.equals("")) {
            return "redirect:/";
        }

        //否则查询当前用户在队列中的记录
        Queue oneQueue = queueService.findFirstByCustomerid(customerid);
        if(oneQueue==null) {
            // redirect to "/" 若查不到记录，也跳回 "/"
        }

        //查询 active=0 and created_at<自己时间的数量
        Long countWaiting = queueService.showCount(oneQueue.getCreatedat());

        model.addAttribute("countWaiting", countWaiting);

        return "waitqueue";
    }

    @GetMapping("/webSocket")
    public ModelAndView socket() {
        ModelAndView mav=new ModelAndView("/webSocket");
//        mav.addObject("userId", userId);
        return mav;
    }

    @GetMapping("/send")
    public String send(@RequestParam(value = "uid", defaultValue = "10") String uid, @RequestParam(value = "message") String message){
        //保存到数据库
        SendMessage sm = new SendMessage();
        sm.setUid("1");
        sm.setTouid(uid);
        sm.setContent(message);
        sm.setType("chat");

        String welcomeMessage = JSON.toJSONString(sm);

        webSocketServer.sendInfo(uid, welcomeMessage);
        return "index";
    }

    @GetMapping("/client")
    public String client(@RequestParam(name="uid") String uid, @RequestParam(name="touid") String touid, Model model) {
        model.addAttribute("uid", uid);
        model.addAttribute("touid", touid);
        return "client";
    }


    /**
     * show page of backend of customer service
     * */
    @GetMapping("/backend")
    public String customerservice() {

        return "backend";
    }


}
