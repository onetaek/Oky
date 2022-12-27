package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;

public class UserDAO {
	PreparedStatement pstmt;
	Connection conn;
	ResultSet rs;
	
	String dbURL = "jdbc:mariadb://127.0.0.1:3308/oky";
	String dbID = "root";
	String dbPassword = "5452";
	
	public void getCon() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			System.out.println("�뿰寃곗씠 �셿猷뚮릺�뿀�뒿�땲�떎.");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�뿰寃곗뿉 �떎�뙣�븯���뒿�땲�떎.");
			e.printStackTrace();
		}
	}
	
	
	
	
	public void insertUser(UserDTO userDTO) {
		System.out.println("insertBoard�떎�뻾");
		getCon();
		
		try {
			System.out.println("00000000");
			pstmt = conn.prepareStatement("select * from user");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		try {
			String sql = "insert into user (id,password,name,nickName)"
					+ "values (?,?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDTO.getId());
			pstmt.setString(2, userDTO.getPassword());
			pstmt.setString(3, userDTO.getName());
			pstmt.setString(4, userDTO.getNickName());
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public int getUser(String id, String password) {
		getCon();
		
		//�쉶�썝 �젙蹂닿� �뾾�쓣寃쎌슦�뿉�뒗 0�쓣 由ы꽩�븯寃뚮맖
		int result = 0;
		
		try {
			String sql = "select count(*) from user where id =? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);//�쉶�썝�젙蹂닿� �룞�씪�븯�떎硫� 1�쓣 由ы꽩�븿
			}
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public String getNickName(String id) {
		
		String nickName = null;
		getCon();
		try {
			String sql = "select nickName from user where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				nickName = rs.getString(1);
				System.out.println(nickName);
			}
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nickName;
	}
	
}
