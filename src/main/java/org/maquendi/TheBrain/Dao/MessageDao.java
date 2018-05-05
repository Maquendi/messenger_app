package org.maquendi.TheBrain.Dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.maquendi.TheBrain.model.Message;

import com.mysql.cj.jdbc.PreparedStatement;

public class MessageDao{

	private Conexion conn;
	
	public MessageDao(){
		conn = new Conexion();
	}
	
	public Message save_message(Message message) throws Exception{
		
		PreparedStatement pst = null;
		String sql = "INSERT INTO message(message,date_created,author) VALUES(?,?,?)";
		Message mess = null;
		
	   
		try{
			
			
			conn.connect().setAutoCommit(false); //starts transaction here...
	
			pst =  (PreparedStatement) conn.connect().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);	
			pst.setString(1,message.getMessage());
			pst.setDate(2,message.getCreated());
			pst.setString(3,message.getAuthor());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			
			if(rs.next()){
				int messageId = rs.getInt(1);
				mess = getMessage(messageId);
			}
			
			conn.connect().commit();

		}catch(SQLException e){
			conn.connect().rollback();
			throw e;
		}finally{
			conn.cerrar();
		}
       
		return mess;
	}
	
	
	
	
	public Message getMessage(Integer messageId) throws Exception {
		
		String query = "SELECT * FROM message WHERE messageId = ?";
		Message newMessage = null;
		PreparedStatement pst = null;
		
		try{
			
			pst = (PreparedStatement) conn.connect().prepareStatement(query);
			pst.setInt(1,messageId);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				newMessage = new Message();
				newMessage.setId((long)messageId);
				newMessage.setAuthor(rs.getString("author"));
				newMessage.setCreated(rs.getDate("date_created"));
				newMessage.setMessage(rs.getString("message"));
			}

		   }catch(SQLException e){
			throw e;
		}finally{
			conn.cerrar();
		}

		return newMessage;
	}
	
	
	public List<Message> findAll()throws Exception{
		
		List<Message> lista = new ArrayList<>();
		String query = "SELECT * FROM message";
		PreparedStatement pst = null;
		Message m = null;
		//Long id, String message, Date created, String author
		try{
			
			pst = (PreparedStatement) conn.connect().prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()){
				m = new Message();
				
				m.setId((long)rs.getInt("messageId"));
				m.setMessage(rs.getString("message"));
				m.setAuthor(rs.getString("author"));
				m.setCreated(rs.getDate("date_created"));
				lista.add(m);
			  }
			
			  }catch(Exception e){
			      throw e;
		    }finally{
			    conn.cerrar();
		}

		return lista;
	}
	
	
	
	
	public Message remove(int messageId)throws Exception{
		
		String sql = "DELETE FROM message WHERE messageId = ?";
		Message message = null;
		
		
		try{

			message = this.getMessage(messageId);
			
			if(message != null){
				PreparedStatement pst = (PreparedStatement)conn.connect().prepareStatement(sql);
				pst.setLong(1,messageId);
				pst.executeUpdate();
			}
			
			
		}catch(Exception e){
			throw e;
		}finally{
			conn.cerrar();
		}
	 
		return message;
	}
	
	
	
	public Message updateMessage(Message message) throws Exception{
		
		String query = "UPDATE message m SET m.message = ? WHERE m.messageId = ?";
		
		try{
			PreparedStatement pst=(PreparedStatement)conn.connect().prepareStatement(query);
			pst.setString(1,message.getMessage());
			pst.setLong(2,message.getId());
			pst.executeUpdate();
			
			
		}catch(Exception e){
			throw e;
		}finally{
			conn.cerrar();
		}
		
		return message;
	}
	
}
