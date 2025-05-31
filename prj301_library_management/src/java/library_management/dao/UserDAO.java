/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import library_management.dto.UserDTO;
import library_management.utils.DBUtils;

/**
 *
 * @author Slayer
 */
public class UserDAO {
    public UserDTO login(String name, String password) throws ClassNotFoundException, SQLException {
        UserDTO user = null;
        Connection con = null;
        
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT id, name, email, password, role, status FROM users WHERE name = ? AND password = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
                                
            if (rs != null) {
                if (rs.next()) {
                    user = new UserDTO();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    user.setStatus(rs.getString("status"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    
    public boolean edit(UserDTO user) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;
        
        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getId());
            
            success = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }
}
