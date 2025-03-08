package com.chat.Chat_webApp.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.chat.Chat_webApp.Repositary.RoomRepositay;
import com.chat.Chat_webApp.entites.Message;
import com.chat.Chat_webApp.entites.Room;
import com.chat.Chat_webApp.payload.MessageRequest;

@Controller
@CrossOrigin("http://localhost:5173")
public class ChatController {
	
	@Autowired
	private RoomRepositay roomRepositay;

	@MessageMapping("/sendMessage{roomId}")
	@SendTo("/topic/room/{roomId}")
	public Message sendMessage(
			@DestinationVariable String roomId,
			@RequestBody MessageRequest request) throws Exception {
		
		Room room = roomRepositay.findByRoomId(request.getRoomId());
		
		Message message = new Message();
		message.setContent(request.getContent());
		message.setSender(request.getSender());
		message.setTimeStamp(LocalDateTime.now());
		
		if(room!= null)
		{
			room.getMessages().add(message);
			roomRepositay.save(room);
		}else
		{
			throw new Exception("Room Not Found");
		}
		
		return message;
		
	}
	
}
