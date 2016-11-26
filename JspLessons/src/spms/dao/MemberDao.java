/**
 * 
 */
package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public class MemberDao {
    Connection connection;
    
    public void setConnecion(Connection connection) {
    	this.connection = connection;
    }
    
    public List<Member> selectList() throws Exception {
    	Statement stmt = null;
		ResultSet rs = null;
		
	    try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select MNO, MNAME, EMAIL, CRE_DATE from MEMBERS order by MNO ASC");
			
			ArrayList<Member> members = new ArrayList<Member>();

			while (rs.next()) {
				members.add(new Member()
						       .setNo(rs.getInt("MNO"))
						       .setName(rs.getString("MNAME"))
						       .setEmail(rs.getString("EMAIL"))
						       .setCreatedDate(rs.getDate("CRE_DATE")));
			}
			
			return members;
	    } catch (Exception e) {
	    	throw e;
		}
	    finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
    }
    
    public int insert(Member member) throws Exception {
		PreparedStatement stmt = null;
		
	    try {
	    	stmt = connection.prepareStatement("INSERT INTO MEMBERS(MNAME, EMAIL, PWD, CRE_DATE, MOD_DATE) VALUES (?, ?, ?, NOW(), NOW());");
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setString(3, member.getPassword());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	    finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
    }
    
    public Member select(int no) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		
	    try {
	        stmt = connection.createStatement();
			rs = stmt.executeQuery(String.format("select MNO, MNAME, EMAIL, CRE_DATE from MEMBERS where MNO = %d;", no));

			Member member = new Member();

			if(rs.next())
				member.setNo(rs.getInt("MNO"))
				      .setName(rs.getString("MNAME"))
				      .setEmail(rs.getString("EMAIL"))
				      .setCreatedDate(rs.getTimestamp("CRE_DATE"));
			
			return member;
		} catch (Exception e) {
			throw e;
		}
	    finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
    }
    
    public int update(Member member) throws Exception {
		PreparedStatement stmt = null;
		
	    try {
	    	stmt = connection.prepareStatement("UPDATE MEMBERS SET MNAME=?, EMAIL=?, MOD_DATE=now() WHERE MNO = ?;");
			
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setInt(3, member.getNo());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	    finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
    }
    
    public int delete(int no) throws Exception {
		PreparedStatement stmt = null;
		
	    try {
	    	stmt = connection.prepareStatement("DELETE FROM MEMBERS WHERE MNO = ?;");
			
			stmt.setInt(1, no);
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	    finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
    }
    
    public Member exist(String email, String password) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
	    try {
            stmt = connection.prepareStatement("SELECT MNO, MNAME, EMAIL FROM MEMBERS WHERE EMAIL = ? AND PWD = ?;");
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			Member member = null;
			
            if(rs.next()) {
				member = new Member()
				            .setNo(rs.getInt("MNO"))
					        .setName(rs.getString("MNAME"))
					        .setEmail(rs.getString("EMAIL"));
			}
            
            return member;
		} catch (Exception e) {
			throw e;
		}
	    finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
    }
}
