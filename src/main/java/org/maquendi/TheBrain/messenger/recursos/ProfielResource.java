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

import org.maquendi.TheBrain.model.Profile;
import org.maquendi.TheBrain.services.ProfileService;


@Path("/profiles")
public class ProfielResource {

	
	private ProfileService profService = new ProfileService();
	
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getProfiles(){
		List<Profile> lista = null;
	try {
			lista= profService.finAll();
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
	  return lista;
	}
	
	
	@GET
	@Path("/{profileName}")
	@Produces(MediaType.APPLICATION_XML)
	public Profile getProfile(@PathParam("profileName")String profName){
		Profile prof = null;
   try {
		prof= profService.getProfile(profName);
	} catch (Exception e) {
		e.printStackTrace();
	}		
		return prof;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile addProf(Profile newProf){
		Profile prof = null;
	  try {
			prof = profService.addProfile(newProf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prof;
   
	}
	
	
	
	@DELETE
	@Path("/{prof_name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile delete(@PathParam("prof_name")String profile_name){
		  Profile prof = null;
		try {
			prof  = profService.removeProf(profile_name);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return prof;
	}
	
	
	@PUT
	@Path("/{prof_name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile update(@PathParam("prof_name")String profile_name,Profile prof){
		
		Profile uProf = null;
	try{
		 uProf = profService.updateProf(profile_name,prof);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return uProf;
	}
	
	
	
}
