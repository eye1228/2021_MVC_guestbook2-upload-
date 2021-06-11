package com.ict.db;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ict.db.VO;

public class DAO {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs; // select문의 결과

	// 싱글톤
	private static DAO dao = new DAO();

	public static DAO getInstance() {
		return dao;
	}

	// DB 접속
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.0.4:1521:xe";
			String user = "c##ictedu02";
			String password = "1111";
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public List<VO> getSelectAll() {

		try {
			List<VO> list = new ArrayList<VO>();

			conn = getConnection();
			String sql = "select * from guestbook2 order by idx";
			pstm = conn.prepareStatement(sql);

			rs = pstm.executeQuery();

			while (rs.next()) {
				VO vo = new VO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setPw(rs.getString("pw"));
				vo.setMsg(rs.getString("msg"));
				vo.setF_name(rs.getString("f_name"));
				vo.setReg(rs.getString("reg"));
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		return null;
	}

	public int getInsert(VO vo) {
		try {
			int result = 0;
			conn = getConnection();
			String sql = "insert into guestbook2 values(guestbook2_seq.nextval,?,?,?,?,?,?,sysdate)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, vo.getName());
			pstm.setString(2, vo.getTitle());
			pstm.setString(3, vo.getEmail());
			pstm.setString(4, vo.getPw());
			pstm.setString(5, vo.getMsg());
			pstm.setString(6, vo.getF_name());

			/*System.out.println(vo.getName());
			System.out.println(vo.getTitle());
			System.out.println(vo.getEmail());
			System.out.println(vo.getPw());
			System.out.println(vo.getMsg());
			System.out.println(vo.getF_name());*/
			
			result = pstm.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("여기 맞음");
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		return 0;
	}

	// 상세보기
	public VO getSelectOne(String idx) {
		try {
			VO vo = null;
			conn = getConnection();
			String sql = "select * from guestbook2 where idx=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idx);

			rs = pstm.executeQuery();
			
			while (rs.next()) {
				vo = new VO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setMsg(rs.getString("msg"));
				vo.setEmail(rs.getString("email"));
				vo.setPw(rs.getString("pw"));
				vo.setReg(rs.getString("reg"));
				vo.setF_name(rs.getString("f_name"));
			}
			return vo;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
			}
		}
		return null;
	}

	// 수정
	public int getUpdate(VO vo) {
		try {
			
			conn = getConnection();
			int result = 0;
			String sql = "update guestbook2 set name=?, title=?,msg=?,email=?,f_name=? where idx=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, vo.getName());
			pstm.setString(2, vo.getTitle());
			pstm.setString(3, vo.getMsg());
			pstm.setString(4, vo.getEmail());
			pstm.setString(5, vo.getF_name());
			pstm.setString(6, vo.getIdx());
			
						
			result = pstm.executeUpdate();
			
			
			return result;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("??????");
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		return 0;
	}

	// delete
	public int getDelete(String idx) {
		try {
			int result = 0;
			conn = getConnection();
			String sql = "delete from guestbook2 where idx=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idx);
			result = pstm.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		return 0;
	}
	
}
