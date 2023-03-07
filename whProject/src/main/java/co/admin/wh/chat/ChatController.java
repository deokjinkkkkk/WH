package co.admin.wh.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception{
		Thread.sleep(1000);
		return new Greeting("Hello, "+ HtmlUtils.htmlEscape(message.getName())+ "!");
	}
	
	
	
	@RequestMapping("/chat")
	public String chat() {
		
		 
		return "chat/chat";
	}
}
