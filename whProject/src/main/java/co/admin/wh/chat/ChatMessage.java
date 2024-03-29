package co.admin.wh.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
	public enum MessageType {
		ENTER, TALK, QUIT
	}

	private MessageType type;
	// 채팅방 ID
	private String roomId;
	// 보내는 사람
	private String sender;
	// 내용
	private String message;
}
