package com.chat.Chat_webApp.Repositary;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chat.Chat_webApp.entites.Room;

public interface RoomRepositay extends MongoRepository<Room, String>{

	//get room using room Id
	
	Room findByRoomId(String roomId);
}
