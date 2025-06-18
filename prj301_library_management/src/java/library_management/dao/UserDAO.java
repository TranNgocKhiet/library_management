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
import java.sql.Statement;
import java.util.ArrayList;
import library_management.dto.UserDTO;
import library_management.utils.DBUtils;

/**
 *
 * @author Slayer
 */
public class UserDAO {

    public ArrayList<UserDTO> getAllUser() throws ClassNotFoundException, SQLException {
        ArrayList<UserDTO> usList = new ArrayList<>();
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT * FROM users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                usList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usList;
    }

    public ArrayList<UserDTO> getUserByEmail(String email) throws ClassNotFoundException, SQLException {
        ArrayList<UserDTO> usList = new ArrayList<>();
        Connection con = null;

        try {
            con = DBUtils.getConnection();

            String sql = "SELECT * FROM users WHERE email LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + email + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                usList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return usList;
    }

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
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public boolean register(String name, String password, String email) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            String sql = "insert into [library_system].[dbo].[users](name, email, password, role, status)\n"
                    + "values (?, ?, ?, 'user', 'active')";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            int rowsAffected = stmt.executeUpdate();
            result = (rowsAffected > 0);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean isUserExists(String name, String email) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            String sql = "select 1 from [library_system].[dbo].[users] where name = ? or email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            result = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
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
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean updateStatus(int id, String status) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE users SET status = ? WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, id);

            success = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }
}
