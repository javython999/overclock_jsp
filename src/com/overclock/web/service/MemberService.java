package com.overclock.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import com.overclock.web.entity.Member;

public class MemberService {
	//----------------------------------------------------------------------------------------------------
	// 아이디 중복 체크
	public String idoverlapcheck(String id) {
		String result = "";
		System.out.println(id);
		JSONObject json = new JSONObject();
		String sql = "SELECT COUNT(*) FROM overclockmember WHERE ID = " + "'"+id+"'";
		String url ="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				int idCheck = rs.getInt("COUNT(*)");
				if(idCheck==1) {
					result = "{\"result\":\"1\"}"; // 중복아이디 = 사용불가함
					
				} else {
					result = "{\"result\":\"0\"}"; // 사용가능한 아이디
					
				}
			}
			
				
			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//----------------------------------------------------------------------------------------------------
	// 닉네임 중복 체크
		public String nicoverlapcheck(String nic) {
			String result = "";
			System.out.println(nic);
			
			String sql = "SELECT COUNT(*) FROM overclockmember WHERE NICKNAME = " + "'"+nic+"'";
			String url ="jdbc:oracle:thin:@localhost:1521/xe";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				if(rs.next()) {
					int idCheck = rs.getInt("COUNT(*)");
					if(idCheck==1) {
						result = "{\"result\":\"1\"}"; // 중복 닉네임 = 사용불가함
						
					} else {
						result = "{\"result\":\"0\"}"; // 사용가능한 닉네임
						
					}
				}
				
					
				rs.close();
				st.close();
				con.close();
				
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}
	
		
		//----------------------------------------------------------------------------------------------------
		// 이메일 중복 체크
			public String emailoverlapcheck(String email) {
				String result = "";
				
				String sql = "SELECT COUNT(*) FROM overclockmember WHERE EMAIL LIKE " + "'"+email+"'";
				String url ="jdbc:oracle:thin:@localhost:1521/xe";
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					
					if(rs.next()) {
						int idCheck = rs.getInt("COUNT(*)");
						if(idCheck==1) {
							result = "{\"result\":\"1\"}"; // 중복 이메일 = 사용불가함
							
						} else {
							result = "{\"result\":\"0\"}"; // 사용가능한 이메일
							
						}
					}
					
						
					rs.close();
					st.close();
					con.close();
					
				} catch (ClassNotFoundException e){
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return result;
			}
			
			
	//----------------------------------------------------------------------------------------------------
	// 회원 등록
	public int joinmember(Member member) {
		
		int result = 0;
		
		String sql = "INSERT INTO OVERCLOCKMEMBER(ID, PW, NICKNAME, NAME, EMAIL) VALUES(?,?,?,?,?)";
		
		String url ="jdbc:oracle:thin:@localhost:1521/xe";	

		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, member.getId());
			st.setString(2, member.getPw());
			st.setString(3, member.getNickName());
			st.setString(4, member.getName());
			st.setString(5, member.getEmail());
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}
	//----------------------------------------------------------------------------------------------------
	// 로그인
	public int loginmember(Member member) {
		int result = 0;
		
		String id = member.getId();
		String pw = member.getPw();
		
		String sql = "SELECT * FROM OVERCLOCKMEMBER WHERE ID = '" + id + "'";
		String url ="jdbc:oracle:thin:@localhost:1521/xe";	
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);		
			
			if(rs.next()) {
				String id_ = rs.getString("ID");
				String pw_ = rs.getString("PW");

				if(id.equals(id_) && pw.equals(pw_)) {
					result = 1;
					System.out.println("정상 로그인");
				} else if(id.equals(id_) && !pw.equals(pw_)) {
					result = 2;
					System.out.println("비밀번호 불일치");
				} else if(!id.equals(id_) && pw.equals(pw_)) {
					result = 3;
					System.out.println("아이디 불일치 ");
				} else if (!id.equals(id_) && !pw.equals(pw_)) {
					result = 4;
					System.out.println("아이디 불일치 / 비밀번호 불일치");
				} else {
					result = 5;
					System.out.println("없는 계정 또는 DB문제");
				}
				
			} else {
				result = 0;
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}
	//----------------------------------------------------------------------------------------------------
	// 닉네임 가져오기
	public String nickNameInfo(String id) {
		String nickname = "";
		
		String sql = "SELECT NICKNAME FROM OVERCLOCKMEMBER WHERE ID = '" + id + "'";
		String url ="jdbc:oracle:thin:@localhost:1521/xe";	
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);		
			
			if(rs.next()) {
				String nickname_ = rs.getString("NICKNAME");
				nickname = nickname_;
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nickname;
	}
}	
