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
import com.overclock.web.entity.CpuView;
import com.overclock.web.entity.Ram;
import com.overclock.web.entity.RamView;
public class BoardRAMService {
	
//------------------------------------------------------------------------------------------------------------------
//게시글 갯수
		public int getArticleCount() {
			return getArticleCount("title", "");
		}
		public int getArticleCount(String field, String query) {
			
			int count = 0;
			
			String sql = "SELECT COUNT(ID) COUNT FROM (" + 
					 	 " SELECT ROWNUM NUM, N.*" + 
					 	 " FROM (SELECT * FROM OVERCLOCKBOARDRAM WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N" +
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
//---------------------------------------------------------------------------------------
// 공개 설정된 게시글 ID 배열 가져오기
	public ArrayList getPubArticleId() {
		ArrayList<Integer> pubArticleId = new ArrayList<Integer>();
		
		String sql = "SELECT ID FROM OVERCLOCKBOARDRAM WHERE PUB = 1 ORDER BY REGDATE DESC";
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
//---------------------------------------------------------------------------------------
//전체 게시글 가져오기[공개+비공개 (어드민전용)]
	public List<RamView> getArticleList() {
		return getArticleList("title", "", 1);
	}
	
	public List<RamView> getArticleList(int page) {
		return getArticleList("title", "", page);
	}
	
	public List<RamView> getArticleList(String field, String query, int page) {
		   List<RamView> list = new ArrayList<>();
			
			String sql = "SELECT * FROM (" + 
						 " SELECT ROWNUM NUM, N.*" + 
						 " FROM (SELECT * FROM OVERCLOCKBOARDRAM_VIEW WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N" +
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
					String thumbnail = rs.getString("thumbnail");
					String title = rs.getString("TITLE");
					String writerId = rs.getString("WRITER_ID");
					String nickName = rs.getString("NICKNAME");
					Date regdate = rs.getDate("REGDATE");
					String hit = rs.getString("HIT");
					String ram_ = rs.getString("RAM");
					boolean pub = rs.getBoolean("PUB");
					int cmtCount = rs.getInt("CMT_COUNT");
					
					RamView ram = new RamView(
							id, 
							thumbnail, 
							title, 
							writerId,
							nickName,
							regdate, 
							hit, 
							ram_, 
							pub, 
							cmtCount);
					list.add(ram);
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
//공개 설정된 게시글 가져오기
	public List<RamView> getArticlePubList(String field, String query, int page) {
	   List<RamView> list = new ArrayList<>();
		
		String sql = "SELECT * FROM (" + 
					 " SELECT ROWNUM NUM, N.*" + 
					 " FROM (SELECT * FROM OVERCLOCKBOARDRAM_VIEW WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N" +
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
				String thumbnail = rs.getString("thumbnail");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				String nickName = rs.getString("NICKNAME");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String ram_ = rs.getString("RAM");
				boolean pub = rs.getBoolean("PUB");
				int cmtCount = rs.getInt("CMT_COUNT");
				
				RamView ram = new RamView(
						id, 
						thumbnail, 
						title, 
						writerId,
						nickName,
						regdate, 
						hit, 
						ram_, 
						pub, 
						cmtCount);
				list.add(ram);
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
//게시글 내용 가져오기
	public Ram getArticle(int id) {
		Ram ram = null;
		increaseHit(id);
		String sql = "SELECT * FROM OVERCLOCKBOARDRAM WHERE ID = " + id;
		String url ="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
			if(rs.next()){
				int aid = rs.getInt("ID");
				String thumbnail= rs.getString("THUMBNAIL");
				String title = rs.getString("TITLE"); 
				Date regdate = rs.getDate("REGDATE"); 
				String writerId =rs.getString("WRITER_ID");
				String nickName =rs.getString("NICKNAME");
				String hit = rs.getString("HIT");
				String content = rs.getString("CONTENT"); 
				boolean pub = rs.getBoolean("PUB");
				ram = new Ram( 
							aid,
							thumbnail,
							title,
							writerId,
							nickName,
							content,
							regdate,
							hit,
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
		
		return ram;
	} 
//-------------------------------------------------------------------------------------
//게시글 조회수 업데이트
		public int increaseHit(int id) {
			int result = 0;
			
			String sql = "UPDATE OVERCLOCKBOARDRAM SET HIT = HIT+1 WHERE ID = ?";
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
//-------------------------------------------------------------------------------------
//게시글 수정
		public int updateArticle(Ram ram) {
			
				int result = 0;
				
				String sql = "UPDATE OVERCLOCKBOARDRAM SET TITLE = ?, CONTENT = ?, THUMBNAIL = ?, RAM = ? WHERE ID = ?";
				String url ="jdbc:oracle:thin:@localhost:1521/xe";	

				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, ram.getTitle());
					st.setString(2, ram.getContent());
					st.setString(3, ram.getThumbnail());
					st.setString(4, ram.getRam());
					st.setInt(5, ram.getId());
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
//-------------------------------------------------------------------------------------
//게시글 삭제
		public int deleteArticle(int id) {
				int result = 0;
							
				String sql = "DELETE OVERCLOCKBOARDRAM WHERE ID = " + id;
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
		
//---------------------------------------------------------------------------------------------------------
// 새 게시글 추가
		public int insertArticle(Ram ram) {
			int result = 0;
			
			String sql = "INSERT INTO OVERCLOCKBOARDRAM(THUMBNAIL, TITLE, WRITER_ID, CONTENT, RAM, NICKNAME) VALUES(?,?,?,?,?,(SELECT NICKNAME FROM OVERCLOCKMEMBER WHERE ID = ?))";
			String url ="jdbc:oracle:thin:@localhost:1521/xe";	

				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, ram.getThumbnail());
					st.setString(2, ram.getTitle());
					st.setString(3, ram.getWriterId());
					st.setString(4, ram.getContent());
					st.setString(5, ram.getRam());
					st.setString(6, ram.getWriterId());
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
//-------------------------------------------------------------------------------------
// 댓글 추가하기				
		public int insertComment(String content_, String userId_, int articleId_ ) {
			int result = 0;
		
			String sql = "INSERT INTO OVERCLOCKBOARDRAMCOMMENT(CONTENT, NICKNAME, ARTICLE_ID, WRITER_ID)" + 
						 "VALUES(" + "'" + content_ + "'" + ", (SELECT NICKNAME FROM OVERCLOCKMEMBER WHERE ID = " + "'" + userId_ + "'" + "), " + articleId_ + ", '" + userId_ + "')";
						 

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
// 댓글 가져오기
		public List<Comment> getComment(int id) {
		

			List<Comment> commentlist = new ArrayList<>();

			String sql = "SELECT * FROM OVERCLOCKBOARDRAMCOMMENT WHERE ARTICLE_ID = " + id + " ORDER BY REGDATE DESC";
			String url ="jdbc:oracle:thin:@localhost:1521/xe";
					
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql);
						
						while(rs.next()){
							int commentId = rs.getInt("id");
							String content = rs.getString("content");
							Date regdate = rs.getDate("regdate"); 
							String nickname =rs.getString("nickname");
							int noticeId = rs.getInt("ARTICLE_ID");
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
//댓글 수정하기
		public int updateComment(String content, int id) {
			int result = 0;

			String sql = "UPDATE OVERCLOCKBOARDRAMCOMMENT SET CONTENT = ? WHERE ID = ?";
			
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
// 댓글 삭제하기
		public int deleteCommentAdmin(int id) {

				int result = 0;
				
				String sql = "DELETE FROM OVERCLOCKBOARDRAMCOMMENT WHERE ID = " +id;
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
		
//--------------------------------------------------------------------------------------------------
//공지글 공개/비공개 설정
			public int pubArticleAll(int[] oids, int[] cids) {
				
				List<String> oidsList = new ArrayList<>(); 
				for(int i=0; i<oids.length; i++)
					oidsList.add(String.valueOf(oids[i]));
				
				List<String> cidsList = new ArrayList<>(); 
				for(int i =0; i<cids.length; i++)
					oidsList.add(String.valueOf(cids[i]));
				
				return pubArticleAll(oidsList, cidsList);
			}

			public int pubArticleAll(List<String> oids, List<String> cids) {
				
				String oidsCSV = String.join(",", oids);
				String cidsCSV = String.join(",", cids);
				return pubArticleAll(oidsCSV, cidsCSV);
			}
			
			// CSV형식
			public int pubArticleAll(String oidCSV, String cidsCSV) {
				
				int result = 0;
				
				String sqlOpen = String.format(" UPDATE OVERCLOCKBOARDRAM SET PUB = 1 WHERE ID IN (%s)", oidCSV);
				//String sqlOpen = "UPDATE OVERCLOCKNOTICE SET PUB = 1 WHERE ID IN ("+oidCSV+")"; 
				String sqlClose = String.format(" UPDATE OVERCLOCKBOARDRAM SET PUB = 0 WHERE ID IN (%s)", cidsCSV);
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
		public int noPubArticleAll(String[] ids) {
				
				List<String> idsList = new ArrayList<>(); 
				for(int i =0; i<ids.length; i++)
				idsList.add(String.valueOf(ids[i]));
					
				return noPubArticleAll(idsList);
					
				}
			
			
		public int noPubArticleAll(List<String> idsList) {
			String idsCSV = String.join(",", idsList);
			return noPubArticleAll(idsCSV);
			}

			
		public int noPubArticleAll(String idsCSV) {
				int result = 0;
				
				String sqlClose = String.format(" UPDATE OVERCLOCKBOARDRAM SET PUB = 0 WHERE ID IN (%s)", idsCSV);
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
//공지글 삭제
		public int deleteArticleAll(int[] ids) {
			int result = 0;
				
			String params = "";
			for(int i=0; i<ids.length; i++) {
				params += ids[i];
				if(i < ids.length-1)
					params += ",";
				}
				
			String sql = "DELETE OVERCLOCKBOARDRAM WHERE ID IN ("+params+")";
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

//---------------------------------------------------------------------------------------------
//인덱스용 공지사항 리스트 가져오기 
		public List<RamView> getIndexArticleList() {
					List<RamView> list = new ArrayList<>();
						
						String sql =  "SELECT * FROM (SELECT * FROM OVERCLOCKBOARDRAM_VIEW WHERE PUB = 1 ORDER BY REGDATE DESC) where rownum < 4";
						String url ="jdbc:oracle:thin:@localhost:1521/xe";
								
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con = DriverManager.getConnection(url, "ORACLE", "1234");
							Statement st = con.createStatement();
							ResultSet rs = st.executeQuery(sql);
							
							while(rs.next()) {
								int id = rs.getInt("ID");
								String thumbnail = rs.getString("THUMBNAIL");
								String title = rs.getString("TITLE");
								String nickName = rs.getString("NICKNAME");
								Date regdate = rs.getDate("REGDATE");
								String hit = rs.getString("HIT");
								String ram_ = rs.getString("RAM");
								int cmtCount = rs.getInt("CMT_COUNT");
								boolean pub = rs.getBoolean("PUB");
								
								RamView ram = new RamView(
										id, 
										thumbnail, 
										title, 
										nickName,
										nickName,
										regdate, 
										hit, 
										ram_, 
										pub, 
										cmtCount);
								
								list.add(ram);
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


