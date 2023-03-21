package co.admin.wh.chat;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {

	private String roomId; // room id
	private String roomName; // room 이름
	private String password; // 비밀번호
	private String createdBy; //생성자

	public static ChatRoom create(String name, String password, String createdBy) {
		ChatRoom room = new ChatRoom();
		room.roomId = UUID.randomUUID().toString();
		room.roomName = name;
		room.password = password;
		room.createdBy = createdBy;
		return room;
	}
}
