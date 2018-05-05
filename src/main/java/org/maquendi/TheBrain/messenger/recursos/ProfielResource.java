package org.maquendi.TheBrain.messenger.recursos;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.maquendi.TheBrain.model.Profile;
import org.maquendi.TheBrain.services.ProfileService;


@Path("/profiles")
public class ProfielResource {

	
	private ProfileService profService = new ProfileService();
	
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getProfiles(){
		return profService.getAllProfiles();	
	}
	
	
	@GET
	@Path("/{profileName}")
	@Produces(MediaType.APPLICATION_XML)
	public Profile getProfile(@PathParam("profileName")String profName){
		
	  return profService.getProfile(profName);		
		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile addProf(Profile newProf){
		
		profService.addProfile(newProf);
		
		return newProf;
   
	}
	
	
	
}
