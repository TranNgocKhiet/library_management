/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.dao;

import library_management.dto.BookRequestDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library_management.utils.DBUtils;

/**
 *
 * @author Slayer
 */
    public class BookRequestDAO {
        public ArrayList<BookRequestDTO> getBookRequestsByUserId(int userId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        ArrayList<BookRequestDTO> requests = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT r.id, user_id, book_id, b.title, request_date, r.status FROM book_requests r LEFT JOIN books b ON r.book_id = b.id WHERE user_id = ? ORDER BY id ASC";
            
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
                requests.add(request);
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
        return requests;
    }

    public boolean addBookRequest(BookRequestDTO request) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();
            String sql = "INSERT INTO book_requests (user_id, book_id, request_date, status) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, request.getUserId());
            stmt.setInt(2, request.getBookId());
            stmt.setDate(3, request.getRequestDate());
            stmt.setString(4, request.getStatus());
            int rows = stmt.executeUpdate();
            
            success = rows > 0;
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
