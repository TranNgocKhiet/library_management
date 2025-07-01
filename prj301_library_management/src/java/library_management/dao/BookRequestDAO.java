/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.dao;

import library_management.dto.BookRequestDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import library_management.dto.BookDTO;
import library_management.utils.DBUtils;

/**
 *
 * @author Slayer
 */
public class BookRequestDAO {
public int insertBorrowedRequest(int userid, ArrayList<BookDTO> list){
    int totalInserted = 0;
    Connection cn = null;
    PreparedStatement st = null;
    try {
        cn = DBUtils.getConnection();
        if (cn != null) {
            cn.setAutoCommit(false);
            String sql = "INSERT INTO book_requests (user_id, book_id, request_date, status, request_type) VALUES (?, ?, ?, ?, ?)";
            st = cn.prepareStatement(sql);

            String day = new Date(System.currentTimeMillis()).toString();

            for (BookDTO book : list) {
                st.setInt(1, userid);
                st.setInt(2, book.getId());
                st.setString(3, day);
                st.setString(4, "pending");
                st.setString(5, "borrow");
                totalInserted += st.executeUpdate();
            }

            cn.commit();
            cn.setAutoCommit(true);
        }
    } catch (Exception e) {
        e.printStackTrace();
        if (cn != null) {
            try {
                cn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    } finally {
        try {
            if (st != null) st.close();
            if (cn != null) cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return totalInserted;
}

    public ArrayList<BookRequestDTO> getBookRequestsByUserId(int userId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        ArrayList<BookRequestDTO> requests = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT r.id, user_id, book_id, b.title, request_date, r.status, request_type FROM book_requests r LEFT JOIN books b ON r.book_id = b.id WHERE user_id = ? ORDER BY id ASC";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BookRequestDTO request = new BookRequestDTO();
                request.setId(rs.getInt("id"));
                request.setUserId(rs.getInt("user_id"));
                request.setBookId(rs.getInt("book_id"));
                request.setTitle(rs.getString("title"));
                request.setRequestDate(rs.getDate("request_date"));
                request.setStatus(rs.getString("status"));
                request.setRequestType(rs.getString("request_type"));
                requests.add(request);
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
        return requests;
    }

    public ArrayList<BookRequestDTO> getAllBookRequests() throws SQLException, ClassNotFoundException {
        Connection con = null;
        ArrayList<BookRequestDTO> requests = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT id, user_id, book_id, request_date, status, request_type FROM book_requests";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                BookRequestDTO request = new BookRequestDTO();
                request.setId(rs.getInt("id"));
                request.setUserId(rs.getInt("user_id"));
                request.setBookId(rs.getInt("book_id"));
                request.setRequestDate(rs.getDate("request_date"));
                request.setStatus(rs.getString("status"));
                request.setRequestType(rs.getString("request_type"));
                requests.add(request);
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
        return requests;
    }

    public boolean addBookRequest(BookRequestDTO request) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();
            String sql = "INSERT INTO book_requests (user_id, book_id, request_date, status, request_type) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, request.getUserId());
            stmt.setInt(2, request.getBookId());
            stmt.setDate(3, request.getRequestDate());
            stmt.setString(4, request.getStatus());
            stmt.setString(5, request.getRequestType());

            int rows = stmt.executeUpdate();

            success = rows > 0;
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

    public boolean setRequestStatusById(int id, String status) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();

            String sql = "UPDATE book_requests SET status = ? WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();

            success = rows > 0;
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
