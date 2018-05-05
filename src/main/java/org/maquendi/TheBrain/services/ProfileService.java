package org.maquendi.TheBrain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.maquendi.TheBrain.Database.DatabaseClass;
import org.maquendi.TheBrain.model.Profile;


public class ProfileService {

	private HashMap<String,Profile> profiles = DatabaseClass.getProfileMap();
	
	
	public ProfileService(){
		profiles.put("Maq", new Profile(1L,"Maq","Maquendi","Beltran Novas"));
		profiles.put("ros", new Profile(2L,"ros","Rosaura","Taveras Veloz"));
	}
	
	
	public List<Profile> getAllProfiles(){
		
		return new ArrayList<>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		
		return profiles.get(profileName);		
	}
	
	public Profile addProfile(Profile prof){
		
		prof.setId(profiles.size() + 1L);
		profiles.put(prof.getProfileName(), prof);
		return prof;
		
	}
	
	public Profile updateProf(Profile prof){
		
		if(prof.getProfileName().isEmpty()){
			return null;
		}
		return profiles.put(prof.getProfileName(),prof);
	}
	
	public void removeProf(Profile prof){
		
		profiles.remove(prof.getProfileName());
	}
	
}
