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
	
	String dbURL = "jdbc:mariadb://127.0.0.1:3306/oky";
	String dbID = "root";
	String dbPassword = "dpdlvldzm419!";
	
	public void getCon() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			System.out.println("연결이 완료되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("연결에 실패하였습니다.");
			e.printStackTrace();
		}
	}
	
	
	
	
	public void insertUser(UserDTO userDTO) {
		System.out.println("insertBoard실행");
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
		
		//회원 정보가 없을경우에는 0을 리턴하게됨
		int result = 0;
		
		try {
			String sql = "select count(*) from user where id =? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);//회원정보가 동일하다면 1을 리턴함
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
