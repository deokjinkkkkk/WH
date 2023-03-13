package co.admin.wh.chat;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
	
	private String roomId;
    private String roomName;
    private String password;
    private String createdBy;

    public static ChatRoom create(String name, String password, String createdBy) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        room.password = password;
        room.createdBy = createdBy;
        return room;
    }
}
