package co.admin.wh.chat;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        TextMessage msg = new TextMessage(session.getPrincipal().getName() + ": " + message.getPayload());
        session.sendMessage(msg);
    }

}
