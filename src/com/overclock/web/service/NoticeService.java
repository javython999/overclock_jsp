package com.overclock.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.overclock.web.entity.Comment;
import com.overclock.web.entity.Notice;
import com.overclock.web.entity.NoticeView;

public class NoticeService {
	

	// 공개 설정된 공지만 리스트로 넘기기
	public List<NoticeView> getNoticePubList(String field, String query, int page) {
		List<NoticeView> list = new ArrayList<>();
		
		String sql = "SELECT * FROM (" + 
					 " SELECT ROWNUM NUM, N.*" + 
					 " FROM (SELECT * FROM OVERCLOCKNOTICE_VIEW WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N" +
					 " )" +
					 " WHERE PUB=1 AND NUM BETWEEN ? AND ?";
		
		//1, 11, 21, 31 -> an = 1+(page-1)*10
		//10, 20, 30, 40 -> page*10
		
		String url ="jdbc:oracle:thin:@localhost:1521/xe";
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				//String content = rs.getString("CONTENT");
				int cmtCount = rs.getInt("CMT_COUNT");
				boolean pub = rs.getBoolean("PUB");
				
				NoticeView notice = new NoticeView(
					id,
					title,
					writerId,
					regdate,
					hit,
					files,
					//content,
					cmtCount,
					pub
				);
				list.add(notice);
			}
				rs.close();
				st.close();
				con.close();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
//---------------------------------------------------------------------------------------
// 공개 설정된 게시글 ID 배열 가져오기
	public ArrayList getPubArticleId() {
		ArrayList<Integer> pubArticleId = new ArrayList<Integer>();
			
		String sql = "SELECT ID FROM OVERCLOCKNOTICE WHERE PUB = 1 ORDER BY REGDATE DESC";
		String url ="jdbc:oracle:thin:@localhost:1521/xe";
			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
				
			while(rs.next()) {
				int id = rs.getInt("ID");
				pubArticleId.add(id);
			}
			rs.close();
			st.close();
			con.close();
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return pubArticleId;
	}
//------------------------------------------------------------------------------------------------------------------------	
// 공지사항 목록 가져오기
	public List<NoticeView> getNoticeList() {
		return getNoticeList("title", "", 1);
	}
	
	public List<NoticeView> getNoticeList(int page) {
		return getNoticeList("title", "", page);
	}
	
	public List<NoticeView> getNoticeList(String field, String query, int page) {
		List<NoticeView> list = new ArrayList<>();
		
		String sql = "SELECT * FROM (" + 
					 " SELECT ROWNUM NUM, N.*" + 
					 " FROM (SELECT * FROM OVERCLOCKNOTICE_VIEW WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N" +
					 " )" +
					 " WHERE NUM BETWEEN ? AND ?";
		
		//1, 11, 21, 31 -> an = 1+(page-1)*10
		//10, 20, 30, 40 -> page*10
		
		String url ="jdbc:oracle:thin:@localhost:1521/xe";
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				//String content = rs.getString("CONTENT");
				int cmtCount = rs.getInt("CMT_COUNT");
				boolean pub = rs.getBoolean("PUB");
				
				NoticeView notice = new NoticeView(
					id,
					title,
					writerId,
					regdate,
					hit,
					files,
					//content,
					cmtCount,
					pub
				);
				list.add(notice);
			}
				rs.close();
				st.close();
				con.close();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//------------------------------------------------------------------------------------------------------------------
//공지글 갯수
	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}
	public int getNoticeCount(String field, String query) {
		
		int count = 0;
		
		String sql = "SELECT COUNT(ID) COUNT FROM (" + 
				 	 " SELECT ROWNUM NUM, N.*" + 
				 	 " FROM (SELECT * FROM OVERCLOCKNOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N" +
				 	 " )";
		
		String url ="jdbc:oracle:thin:@localhost:1521/xe";	
	
			
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		ResultSet rs = st.executeQuery();
		
		if(rs.next())		
			count = rs.getInt("count");
		
		rs.close();
		st.close();
		con.close();
		
	} catch (ClassNotFoundException e){
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return count;
	}
	
	
	
	
// 공지글 내용 가져오기
	public Notice getNotice(int id) {
		increaseHit(id);
		Notice notice = null;
	
		String sql = "SELECT * FROM OVERCLOCKNOTICE WHERE ID=?";
		
		String url ="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
			
			ResultSet rs = st.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Date regdate = rs.getDate("REGDATE"); 
				String writerId =rs.getString("WRITER_ID"); 
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES"); 
				String content = rs.getString("CONTENT"); 
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice( 
							nid,
							title,
							writerId,
							regdate,
							hit,
							files,
							content,
							pub
							);
				}
				rs.close();
				st.close();
				con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}
	
	
// 공지글 내용 가져오기 (조회수 변동 없음)
		public Notice getNoticeAdmin(int id) {

			Notice notice = null;
		
			String sql = "SELECT * FROM OVERCLOCKNOTICE WHERE ID=?";
			
			String url ="jdbc:oracle:thin:@localhost:1521/xe";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1, id);
				
				
				ResultSet rs = st.executeQuery();

				if(rs.next()){
					int nid = rs.getInt("ID");
					String title = rs.getString("TITLE"); 
					Date regdate = rs.getDate("REGDATE"); 
					String writerId =rs.getString("WRITER_ID"); 
					String hit = rs.getString("HIT");
					String files = rs.getString("FILES"); 
					String content = rs.getString("CONTENT"); 
					boolean pub = rs.getBoolean("PUB");
					
					notice = new Notice( 
								nid,
								title,
								writerId,
								regdate,
								hit,
								files,
								content,
								pub
								);
					}
					rs.close();
					st.close();
					con.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return notice;
		}
	
//-------------------------------------------------------------------------------------
// 댓글 가져오기
		public List<Comment> getComment(int id) {
			List<Comment> commentlist = new ArrayList<>();

			String sql = "SELECT * FROM OVERCLOCKCOMMENT WHERE NOTICE_ID = ? ORDER BY REGDATE DESC";
			
			String url ="jdbc:oracle:thin:@localhost:1521/xe";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1, id);

				ResultSet rs = st.executeQuery();

				
				while(rs.next()){
					int commentId = rs.getInt("id");
					String content = rs.getString("content");
					Date regdate = rs.getDate("regdate"); 
					String nickname =rs.getString("nickname");
					int noticeId = rs.getInt("NOTICE_ID");
					String writerId = rs.getString("WRITER_ID");
					
					Comment comment = new Comment(
							commentId,
							content,
							regdate,
							nickname,
							noticeId,
							writerId
							);
					
					commentlist.add(comment);
					
					}
				
					rs.close();
					st.close();
					con.close();
					
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return commentlist;
		}
//-------------------------------------------------------------------------------------
// 댓글 삭제하기
	public int deleteCommentAdmin(int id) {

		int result = 0;
		
		String sql = "DELETE FROM OVERCLOCKCOMMENT WHERE ID = " +id;
			
		String url ="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
									
				rs.close();
				st.close();
				con.close();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				}
			return result;
	}	
	
	
//-------------------------------------------------------------------------------------
// 댓글 추가하기		
		
public int insertComment(String content_, String userId_, int noticeId_ ) {
	int result = 0;

			
	String sql = "INSERT INTO OVERCLOCKCOMMENT(CONTENT, NICKNAME, NOTICE_ID, WRITER_ID)" + 
				 "VALUES(" + "'" + content_ + "'" + ", (SELECT NICKNAME FROM OVERCLOCKMEMBER WHERE ID = " + "'" + userId_ + "'" + "), " + noticeId_ + ", '" + userId_ + "')";
				 

	String url ="jdbc:oracle:thin:@localhost:1521/xe";	
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
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
//-------------------------------------------------------------------------------------
//댓글 수정하기
public int updateComment(String content, int id) {
	int result = 0;

	String sql = "UPDATE OVERCLOCKCOMMENT SET CONTENT = ? WHERE ID = ?";
	
	String url ="jdbc:oracle:thin:@localhost:1521/xe";
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, content);
		st.setInt(2, id);

		result = st.executeUpdate();

		st.close();
		con.close();
			
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
}

//-------------------------------------------------------------------------------------
// 마지막 공지글 ID 구하기
public int getLastCount() {
	int lastId = 0;
		
	String sql = "SELECT ID " + 
				     " FROM (SELECT ID FROM overclocknotice ORDER BY REGDATE DESC) " + 
				     " WHERE ROWNUM < 2 ";
				 	   	 
	String url ="jdbc:oracle:thin:@localhost:1521/xe";	
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {
			lastId = rs.getInt("ID");
		}
		
		rs.close();
		st.close();
		con.close();
		
	} catch (ClassNotFoundException e){
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return lastId;
	}
	
	
//--------------------------------------------------------------------------------------------------
//공지글 공개/비공개 설정
	public int pubNoticeAll(int[] oids, int[] cids) {
		
		List<String> oidsList = new ArrayList<>(); 
		for(int i=0; i<oids.length; i++)
			oidsList.add(String.valueOf(oids[i]));
		
		List<String> cidsList = new ArrayList<>(); 
		for(int i =0; i<cids.length; i++)
			oidsList.add(String.valueOf(cids[i]));
		
		return pubNoticeAll(oidsList, cidsList);
	}

	public int pubNoticeAll(List<String> oids, List<String> cids) {
		
		String oidsCSV = String.join(",", oids);
		String cidsCSV = String.join(",", cids);
		return pubNoticeAll(oidsCSV, cidsCSV);
	}
	
	// CSV형식
	public int pubNoticeAll(String oidCSV, String cidsCSV) {
		
		int result = 0;
		
		String sqlOpen = String.format(" UPDATE OVERCLOCKNOTICE SET PUB = 1 WHERE ID IN (%s)", oidCSV);
		//String sqlOpen = "UPDATE OVERCLOCKNOTICE SET PUB = 1 WHERE ID IN ("+oidCSV+")"; 
		String sqlClose = String.format(" UPDATE OVERCLOCKNOTICE SET PUB = 0 WHERE ID IN (%s)", cidsCSV);
		//String sqlClose = "UPDATE OVERCLOCKNOTICE SET PUB = 0 WHERE ID IN ("+cidsCSV+")";
		String url ="jdbc:oracle:thin:@localhost:1521/xe";	
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			
			
			if(oidCSV != null && oidCSV.length() != 0) {
			Statement stOpen = con.createStatement();
			result += stOpen.executeUpdate(sqlOpen);
			
			stOpen.close();
			}
			
			if(cidsCSV != null && cidsCSV.length() != 0) {
			Statement stClose = con.createStatement();
			result += stClose.executeUpdate(sqlClose);
			stClose.close();
			}
			
			
			con.close();
			
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
		
	}
	
//---------------------------------------------------------------------------------------
//공지글 전부 비공개 설정시
public int noPubNoticeAll(String[] ids) {
		
		List<String> idsList = new ArrayList<>(); 
		for(int i =0; i<ids.length; i++)
		idsList.add(String.valueOf(ids[i]));
			
		return noPubNoticeAll(idsList);
			
		}
	
	
public int noPubNoticeAll(List<String> idsList) {
	String idsCSV = String.join(",", idsList);
	return noPubNoticeAll(idsCSV);
	}

	
public int noPubNoticeAll(String idsCSV) {
		int result = 0;
		
		String sqlClose = String.format(" UPDATE OVERCLOCKNOTICE SET PUB = 0 WHERE ID IN (%s)", idsCSV);
		//String sqlClose = "UPDATE OVERCLOCKNOTICE SET PUB = 0 WHERE ID IN ("+cidsCSV+")";
		String url ="jdbc:oracle:thin:@localhost:1521/xe";	
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			
			Statement stClose = con.createStatement();
			result += stClose.executeUpdate(sqlClose);
			stClose.close();
						
			con.close();
			
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
		
	}
	
	
	
//-------------------------------------------------------------------------------------
//공지글 조회수 업데이트
	public int increaseHit(int id) {
		int result = 0;
		
		String sql = "UPDATE OVERCLOCKNOTICE SET HIT = HIT+1 WHERE ID = ?";
		String url ="jdbc:oracle:thin:@localhost:1521/xe";	
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
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

//---------------------------------------------------------------------------------------------------------
// 새 공지글 추가하기
	public int insertNotice(Notice notice) {
		int result = 0;
		
			
		String sql = "INSERT INTO OVERCLOCKNOTICE(TITLE, CONTENT, WRITER_ID, PUB, files) VALUES(?,?,?,?,?)";
	
		String url ="jdbc:oracle:thin:@localhost:1521/xe";	

		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getWriterId());
			st.setBoolean(4, notice.getPub());
			st.setString(5, notice.getFiles());
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
	
	
	public int deleteNotice(int id) {
		return 0;
	}

	public List<Notice> getNoticeNewestList() {
		return null;
	}
	
	
	
//-------------------------------------------------------------------------------------
//공지글 삭제
	public int deleteNoticeAll(int[] ids) {
		int result = 0;
		
		String params = "";
		for(int i=0; i<ids.length; i++) {
			params += ids[i];
			if(i < ids.length-1)
				params += ",";
		}
		
		String sql = "DELETE OVERCLOCKNOTICE WHERE ID IN ("+params+")";
	
		String url ="jdbc:oracle:thin:@localhost:1521/xe";	

		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			Statement st = con.createStatement();
			
			result = st.executeUpdate(sql);
			
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}

	
	
	//-------------------------------------------------------------------------------------
	//공지사항 수정
	public int updateNotice(Notice notice) {
		
			int result = 0;
			
			String sql = "UPDATE OVERCLOCKNOTICE SET TITLE = ?, CONTENT = ?, PUB = ?, files = ? WHERE ID = ?";
			String url ="jdbc:oracle:thin:@localhost:1521/xe";	

			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, notice.getTitle());
				st.setString(2, notice.getContent());
				st.setBoolean(3, notice.getPub());
				st.setString(4, notice.getFiles());
				st.setInt(5, notice.getId());
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
//---------------------------------------------------------------------------------------------
//인덱스용 공지사항 리스트 가져오기 

	public List<NoticeView> getIndexNoticeList() {
		List<NoticeView> list = new ArrayList<>();
		
		String sql =  "SELECT * FROM (SELECT * FROM overclocknotice_view WHERE PUB = 1 ORDER BY REGDATE DESC) where rownum < 10";
		
		//1, 11, 21, 31 -> an = 1+(page-1)*10
		//10, 20, 30, 40 -> page*10
		
		String url ="jdbc:oracle:thin:@localhost:1521/xe";
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				int cmtCount = rs.getInt("CMT_COUNT");
				boolean pub = rs.getBoolean("PUB");
				
				NoticeView notice = new NoticeView(
					id,
					title,
					writerId,
					regdate,
					hit,
					files,
					cmtCount,
					pub
				);
				list.add(notice);
			}
				rs.close();
				st.close();
				con.close();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}


