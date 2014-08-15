package x.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
public class IndexCtrl {

	@Autowired
	private SimpMessagingTemplate template;

	@RequestMapping(value = { "/" })
	@ResponseBody
	public Model index(Model model) {
		model.addAttribute("v", "x");
		return model;
	}
        
	//接受socket消息
	@MessageMapping("/hello")
	public void greeting() throws Exception {
		System.out.println(".....server..MessageMapping..");
		updatePriceAndBroadcast();
	}
	
	//接受socket消息，并给clien发送消息
	@MessageMapping("/hello2")
	@SendTo("/topic/send")
	public Long push() throws Exception {
		System.out.println(".....server..SendTo..");
        return System.currentTimeMillis();
	}
	
	//给客户端发送消息
	private void updatePriceAndBroadcast() {
		System.out.println(".....server..updatePriceAndBroadcast..");
		template.convertAndSend("/topic/msg", System.currentTimeMillis());
	}

}
