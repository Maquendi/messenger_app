package org.maquendi.TheBrain.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import org.maquendi.TheBrain.model.Profile;

import com.mysql.cj.jdbc.PreparedStatement;

public class ProfileDao {

	
	Conexion conn = null;
	
	public ProfileDao(){
		conn = new Conexion();
	}
	
	
	
	public List<Profile> findAll()throws Exception{
		List<Profile> lista = new ArrayList<>();
		String query = "SELECT * FROM profile";
		Profile prof = null;
		
		
		try{
			
			PreparedStatement pst = (PreparedStatement) conn.connect().prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
		    while(rs.next()){
		    prof = new Profile();
		    prof.setId((long)rs.getInt("profileId"));
		    prof.setFirstName(rs.getString("first_name"));
		    prof.setLastName(rs.getString("last_name"));
		    prof.setProfileName(rs.getString("prof_name"));
			lista.add(prof);
				
			}
		}catch(Exception e){
			throw e;
		}finally{
			conn.disconect();
		}
		
		return lista;
		
	}
	
	
	
	public Profile getProfile(String prof_name) throws Exception{
		
		String query = "SELECT * FROM profile p WHERE p.prof_name=?";
		Profile prof = null;
		
		
		try{
			PreparedStatement pst = (PreparedStatement) conn.connect().prepareStatement(query);
			pst.setString(1,prof_name);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				
				prof = new Profile();
				prof.setId((long) rs.getInt("profileId"));
				prof.setProfileName(rs.getString("prof_name"));
				prof.setFirstName(rs.getString("first_name"));
				prof.setLastName(rs.getString("last_name"));
				
			}
			
		}catch(Exception e){
			throw e;
		}finally{
			conn.disconect();
		}
		
		return prof;
	}
	
	
	public Profile addProfile(Profile prof) throws Exception{
		 
		String insert = "INSERT INTO profile(prof_name,first_name,last_name) VALUES(?,?,?)";
		int key = 0;
		//Connection conexion = conn.connect();
		
		try{
			conn.connect().setAutoCommit(false);
			PreparedStatement pst = (PreparedStatement) conn.connect().prepareStatement(insert);
			pst.setString(1,prof.getProfileName());
			pst.setString(2,prof.getFirstName());
			pst.setString(3,prof.getLastName());
			pst.executeUpdate();
			conn.connect().commit();
		   }catch(Exception e){
			 conn.connect().rollback();
			 throw e;
		 }finally{
			 conn.disconect();
		 }
	  	
		return getProfile(prof.getProfileName());
	 }
	
	
	
	
	public Profile deleteProf(String profile_name)throws Exception{
		
		String sql = "DELETE FROM profile WHERE prof_name = ?";
		Profile deletedProf = null;
		
		try{
			deletedProf = getProfile(profile_name);
			
			if(deletedProf != null){
				PreparedStatement pst = (PreparedStatement) conn.connect().prepareStatement(sql);
				pst.setString(1,profile_name);
				pst.executeUpdate();
			}
			
		   }catch(Exception e){
			throw e;
		  }finally{
			 conn.disconect();
		}
		
		return deletedProf;
	}
	
	
	public Profile update(String prof_name, Profile prof) throws Exception{
		String query = "UPDATE profile p SET p.prof_name = ?, p.first_name = ?, p.last_name = ? WHERE p.prof_name = ?";
		Connection conexion = conn.connect();
		
	    try{
			conexion.setAutoCommit(false);
			PreparedStatement pst = (PreparedStatement) conexion.prepareStatement(query);
			pst.setString(1,prof.getProfileName());
			pst.setString(2,prof.getFirstName());
			pst.setString(3,prof.getLastName());
			pst.setString(4,prof_name);
			pst.executeUpdate();
			conexion.commit();
			
			
		}catch(Exception e){
			conexion.rollback();
			throw e;
		}
		
		return getProfile(prof.getProfileName());
	}
	
	
}
