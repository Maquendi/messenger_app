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
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages(){
     return messageService.getAllMessages();	
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message){
		
		return messageService.addMessage(message);	
	}
	
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId")String id, Message message){
		
		try{
			Long mjsID = Long.parseLong(id);
			message.setId(mjsID);
			return messageService.updateMessage(message);
			
		}catch(NumberFormatException e){
			
		}
		return null;
	}
	
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("messageId") String id){
		
		
		try{
			
		  messageService.removeMessage(Long.parseLong(id));
			
		}catch(NumberFormatException e){
			
		}
		
	}
	
	
	
	
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId")String messageId){
		
		Message message = null;
		
		try{
			
			
		 message = messageService.getMessage(Long.parseLong(messageId));
			
		}catch(ContentNotFoundException e){
			
			Message ms = new Message();
			ms.setMessage(e.getError());
			return ms;
		}catch(NumberFormatException ex){
			Message ms = new Message();
			
			ms.setMessage("The Request Resources does not exist: " +ex.getMessage());
			return ms;
		}
	
		return message;
	}
	
	

	
	
	
}
