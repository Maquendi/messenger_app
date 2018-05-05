package org.maquendi.TheBrain.Database;

import java.util.HashMap;

import org.maquendi.TheBrain.model.Message;
import org.maquendi.TheBrain.model.Profile;

public class DatabaseClass {

	
	private static HashMap<Long,Message> messageMap = new HashMap<>();
	private static HashMap<String,Profile> profileMap = new HashMap<>();
	
	
	public static HashMap<Long, Message> getMessageMap() {
		return messageMap;
	}
	public static HashMap<String, Profile> getProfileMap() {
		return profileMap;
	}

}
