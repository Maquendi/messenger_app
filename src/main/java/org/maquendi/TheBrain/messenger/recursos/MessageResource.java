package org.maquendi.TheBrain.messenger.recursos;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.maquendi.TheBrain.services.MessageService;
import org.maquendi.TheBrain.custom.Exceptions.ContentNotFoundException;
import org.maquendi.TheBrain.model.Message;




@Path("/messages")
public class MessageResource {

   private MessageService messageService = new MessageService();	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(){
		
		List<Message> lista = null;
      try {
		
    	  lista = messageService.getAllMessages();
		
	   } catch (Exception e) {
		
		   e.printStackTrace();
	   }	
      
      return lista;
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message){
		
		Message newMessage = null;
		try {
			newMessage= messageService.addMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return newMessage;
	}
	
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId")String id, Message message){
		
		Message newMessage = null;
		try{
			Long mjsID = Long.parseLong(id);
			message.setId(mjsID);
			newMessage = messageService.updateMessage(message);
			
		}catch(Exception e){
			
		}
		return newMessage;
	}
	
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("messageId") String id){
		
		
		try{
			
		   messageService.removeMessage(Integer.parseInt(id));
			
		}catch(Exception e){
			
		}
		
	}
	
	
	
	
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId")String messageId){
		
		Message message = null;
		
		try{
			
		      message = messageService.getMessage(Integer.parseInt(messageId));
			
		}catch(Exception e){}
	
		return message;
	}
	
	

	
	
	
}
