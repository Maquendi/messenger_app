package org.maquendi.TheBrain.services;

import java.util.HashMap;
import java.util.List;

import org.maquendi.TheBrain.Dao.ProfileDao;
import org.maquendi.TheBrain.model.Profile;


public class ProfileService {

	private ProfileDao profile = new ProfileDao();
	
	public List<Profile> finAll() throws Exception{
		return profile.findAll();
	}
	
	public Profile getProfile(String profileName) throws Exception{
		return profile.getProfile(profileName);		
	}
	
	public Profile addProfile(Profile prof) throws Exception{
		return profile.addProfile(prof);
	}
	
	
	public Profile updateProf(String prof_name,Profile prof) throws Exception{
		return profile.update(prof_name,prof);
	}
	
	public Profile removeProf(String prof) throws Exception{
		
        return profile.deleteProf(prof);	
	}
	
}
