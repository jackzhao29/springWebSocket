package x.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexCtrl {

	@RequestMapping(value = { "/" })
	@ResponseBody
	public Model index(Model model) {
		model.addAttribute("v", "x");
		return model;
	}

	@MessageMapping("/hello")
	public Model greeting(Model model) throws Exception {

		System.out.println(".....server..MessageMapping..");

		Thread.sleep(3000);
		model.addAttribute("t", System.currentTimeMillis());
		return model;
	}

	@SendTo("/topic/greetings")
	public Long push() throws Exception {

		System.out.println(".....server..SendTo..");
		
		Thread.sleep(1000);
		return System.currentTimeMillis();
	}

}
