package com.chat.Chat_webApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.Chat_webApp.Repositary.RoomRepositay;
import com.chat.Chat_webApp.entites.Message;
import com.chat.Chat_webApp.entites.Room;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class RoomController {

	@Autowired
	private RoomRepositay roomRepositay;
	
	//create Room
	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody String roomId)
	{
		if(roomRepositay.findByRoomId(roomId)!=null) {
			//room present
			return ResponseEntity.badRequest().body("Room Already Create");
		}
		//create room
		Room room= new Room();
		room.setRoomId(roomId);
		
		Room save = roomRepositay.save(room);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(room);
	}
	
	
	//get Room 
	@GetMapping("/{roomId}")
	public ResponseEntity<?> joinRoom(@PathVariable String roomId)
	{
		Room room = roomRepositay.findByRoomId(roomId);
		
		if(room == null) {
			return ResponseEntity.badRequest().body("Room Not Found");
		}
		
		return ResponseEntity.ok(room);
	}
	  
	
	//get Message of Room
	@GetMapping("/{roomId}/messages")
	public ResponseEntity<List<Message>> getMesseges(
			@PathVariable String roomId,
			@RequestParam(value = "page" , defaultValue = "0" , required = false)int page,
			@RequestParam(value = "size" , defaultValue = "20" , required = false) int size
			)
	{
		Room room = roomRepositay.findByRoomId(roomId);
		if(room == null)
		{
			return ResponseEntity.badRequest().build();
		}
		//get all
		
		List<Message> messages = room.getMessages();
		
		int start  = Math.max(0, messages.size()- (page +1) * size);
		int end = Math.min(messages.size(), start+size);
		
		List<Message> paginatedMessages = messages.subList(start, end);
		
		return ResponseEntity.ok(paginatedMessages);
	}
	
}
