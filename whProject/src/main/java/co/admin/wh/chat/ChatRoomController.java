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
	
	String currentUserId = getCurrentUserId();
	
	// 현재 로그인한 사용자의 ID 가져오기
	private String getCurrentUserId() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	      return authentication.getName();
	    } else {
	      // 로그인 되어 있지 않은 사용자의 경우 예외 처리 또는 기본값 설정 등을 수행할 수 있습니다.
	      return "anonymous";
	    }
	  }
	
	

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
	    String createdBy = getCurrentUserId(); // 현재 로그인한 사용자의 ID를 createdBy로 지정
	    return chatService.createRoom(name, password, createdBy);
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

	// 채팅방 삭제
	@PostMapping("/room/delete/{roomId}")
	@ResponseBody
	public String deleteRoom(@PathVariable String roomId) {
	    ChatRoom chatRoom = chatService.findById(roomId);
	    String createdBy = chatRoom.getCreatedBy();
	    String currentUserId = getCurrentUserId();
	    if (!createdBy.equals(currentUserId)) {
	        throw new RoomAccessException("삭제할 권한이 없습니다");
	    }
	    chatService.deleteRoom(chatRoom);
	    return "success";
	}
	
	public class RoomAccessException extends RuntimeException {
	    public RoomAccessException(String message) {
	        super(message);
	    }
	}
//	@PostMapping("/room/delete/{roomId}")
//	@ResponseBody
//	public String deleteRoom(@PathVariable String roomId, @RequestBody Map<String, Object> requestBody) {
//	    String currentUserId = (String) requestBody.get("currentUserId");
//	    ChatRoom chatRoom = chatService.findById(roomId);
//	    if (!currentUserId.equals(chatRoom.getCreatedBy())) {
//	        // 현재 로그인한 사용자와 방을 개설한 사용자의 ID가 다를 경우 삭제 불가
//	        return "fail";
//	    }
//	    chatService.deleteRoom(chatRoom);
//	    return "success";
//	}
}
