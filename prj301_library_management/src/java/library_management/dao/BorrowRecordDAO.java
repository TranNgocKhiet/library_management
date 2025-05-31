/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library_management.dto.BorrowRecordDTO;
import library_management.utils.DBUtils;

/**
 *
 * @author Slayer
 */
public class BorrowRecordDAO {
    public ArrayList<BorrowRecordDTO> getBorrowRecordsByUserId(int userId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        ArrayList<BorrowRecordDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT r.id, user_id, book_id, b.title, borrow_date, due_date, return_date, r.status FROM borrow_records r LEFT JOIN books b ON r.book_id = b.id WHERE user_id = ? ORDER BY id ASC";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                BorrowRecordDTO record = new BorrowRecordDTO();
                record.setId(rs.getInt("id"));
                record.setUserId(rs.getInt("user_id"));
                record.setBookId(rs.getInt("book_id"));
                record.setTitle(rs.getString("title"));
                record.setBorrowDate(rs.getDate("borrow_date"));
                record.setDueDate(rs.getDate("due_date"));
                record.setReturnDate(rs.getDate("return_date"));
                record.setStatus(rs.getString("status"));
                list.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return list;
    }
    
    public boolean addBorrowRecord(BorrowRecordDTO record) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();
            String sql = "INSERT INTO borrow_records (user_id, book_id, borrow_date, due_date, return_date, status) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, record.getUserId());
            stmt.setInt(2, record.getBookId());
            stmt.setDate(3, record.getBorrowDate());
            stmt.setDate(4, record.getDueDate());
            stmt.setDate(5, record.getReturnDate());  
            stmt.setString(6, record.getStatus());
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

    public boolean updateReturnStatus(int id, Date returnDate, String newStatus) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE borrow_records SET return_date = ?, status = ? WHERE id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, returnDate);
            stmt.setString(2, newStatus);
            stmt.setInt(3, id);

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
