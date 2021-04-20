package com.dv16888.www;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dv16888.www.model.SendMessage;
import com.dv16888.www.repository.QueueRepository;
import com.dv16888.www.service.QueueService;
import com.dv16888.www.service.WebSocketServer;
import com.dv16888.www.vo.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private WebSocketServer webSocketServer;
	@Autowired
	private QueueService queueService;

	/*@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}*/

	@PostMapping("/sendmessage")
	public String sendmessage(@RequestBody JSONObject jsonParam, HttpServletRequest request){
		String customerid = (String) request.getSession().getAttribute("customerid");

		SendMessage sm = new SendMessage();

		for(String str:jsonParam.keySet()){
			if(str.equals("touid"))
				sm.setTouid(jsonParam.get(str).toString());
			if(str.equals("content"))
				sm.setContent(jsonParam.get(str).toString());
		}
		sm.setType("chat");
		sm.setUid(customerid);

		//判断该uid 在队列里是否被激活，发送的消息必须到队列里的客服id
		Queue oneQueue = queueService.findFirstByCustomerid(customerid);
		if(oneQueue==null) {
			// redirect to "/"
		} else if(oneQueue.getActive()==0) {
			// show message "wait the queue, please"
		}

		String welcomeMessage = JSON.toJSONString(sm);
        webSocketServer.sendInfo(sm.getTouid(), welcomeMessage);
        //System.out.println("在线人数：" + webSocketServer.getOnlineNum());
		return jsonParam.toJSONString();
	}

}