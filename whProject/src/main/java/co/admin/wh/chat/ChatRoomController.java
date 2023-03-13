package co.admin.wh.chat;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
	private final ChatService chatService;
	


	// 채팅 리스트 화면
	@GetMapping("/room")
	public String rooms(Model model) {
		return "/chat/room";
	}

	// 모든 채팅방 목록 반환
	@GetMapping("/rooms")
	@ResponseBody
	public List<ChatRoom> room() {
		return chatService.findAllRoom();
	}

	// 채팅방 생성
	@PostMapping("/room")
	@ResponseBody
	public ChatRoom createRoom(@RequestParam String name, @RequestParam String password) {
		return chatService.createRoom(name, password);
	}

	// 채팅방 삭제
	@PostMapping("/room/delete/{roomId}")
	@ResponseBody
	public String deleteRoom(@PathVariable String roomId) {
		ChatRoom chatRoom = chatService.findById(roomId);
		chatService.deleteRoom(chatRoom);
		return "success";
	}

	// 채팅방 입장 화면
	@GetMapping("/room/enter/{roomId}")
	public String roomDetail(Model model, @PathVariable String roomId) {
		model.addAttribute("roomId", roomId);
		return "/chat/roomdetail";
	}

	// 특정 채팅방 조회
	@GetMapping("/room/{roomId}")
	@ResponseBody
	public ChatRoom roomInfo(@PathVariable String roomId) {
		return chatService.findById(roomId);
	}

	@PostMapping(value = "/room/check-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public ResponseEntity<String> checkPassword(@RequestParam("roomId") String roomId,
			@RequestBody Map<String, Object> params) {
		String password = (String) params.get("password");
		ChatRoom chatRoom = chatService.findById(roomId);
		if (chatRoom != null && chatRoom.getPassword().equals(password)) {
			return ResponseEntity.ok("success");
		} else {
			return ResponseEntity.badRequest().body("fail");
		}
	}

}
