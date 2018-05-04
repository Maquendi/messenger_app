package org.maquendi.TheBrain.services;

import org.maquendi.TheBrain.Database.DatabaseClass;
import org.maquendi.TheBrain.custom.Exceptions.ContentNotFoundException;
import org.maquendi.TheBrain.model.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class MessageService {
	
	private HashMap<Long,Message> messages = DatabaseClass.getMessageMap();
	
	public MessageService(){
		
		messages.put(1L,new Message(1L,"Hello World",new Date(),"Maquendi B."));
		messages.put(2L,new Message(2L,"Hey Mundo",new Date(),"Maquendi B."));
	}
	
	
	
	public List<Message> getAllMessages(){
	   
		return new ArrayList<>(messages.values());
	
	}
	
	public Message getMessage(Long id) throws ContentNotFoundException{
		
		if(messages.get(id) != null){
			return messages.get(id);
		}else
			throw new ContentNotFoundException("El Mensaje Solicitado No Existe !!..");
		
	}
	
	public Message removeMessage(Long id){
		return messages.remove(id);
	}
	
	
	public Message updateMessage(Message mensaje){
		
		if(messages.containsKey(mensaje.getId())){
			messages.put(mensaje.getId(),mensaje);
			return mensaje;
		}
		return null;
	}
   
	public Message addMessage(Message msj){
		
		msj.setId(messages.size()+1L);
		messages.put(msj.getId(),msj);
		
		return msj;
	}

}
