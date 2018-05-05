package org.maquendi.TheBrain.services;

import org.maquendi.TheBrain.Dao.MessageDao;
import org.maquendi.TheBrain.model.Message;
import java.util.List;


public class MessageService {
	

	private MessageDao messagedao = new MessageDao();
	
	
	//Long id, String message, Date created, String author
	
	public List<Message> getAllMessages() throws Exception{
		
	   return messagedao.findAll();
	}
	
	public Message getMessage(int id) throws Exception{
		return messagedao.getMessage(id);
	}
	
	public Message removeMessage(int id) throws Exception{
		return messagedao.remove(id);
	}
	
	
	public Message updateMessage(Message message) throws Exception{
		return messagedao.updateMessage(message);
	}
   
	public Message addMessage(Message message) throws Exception{
		return messagedao.save_message(message);	
	}

}
