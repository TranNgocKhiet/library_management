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

    public ArrayList<BorrowRecordDTO> getOverdueBooks() throws SQLException, ClassNotFoundException {
        ArrayList<BorrowRecordDTO> list = new ArrayList<>();
        Connection conn = DBUtils.getConnection();

        String sql = "SELECT * FROM borrow_records WHERE (return_date IS NULL AND due_date < GETDATE()) OR status = 'overdue'";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            BorrowRecordDTO borrow = new BorrowRecordDTO();
            borrow.setId(rs.getInt("id"));
            borrow.setUserId(rs.getInt("user_id"));
            borrow.setBookId(rs.getInt("book_id"));
            borrow.setBorrowDate(rs.getDate("borrow_date"));
            borrow.setDueDate(rs.getDate("due_date"));
            borrow.setReturnDate(rs.getDate("return_date"));
            borrow.setStatus(rs.getString("status"));
            list.add(borrow);
        }
        rs.close();
        ps.close();
        conn.close();

        return list;
    }

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
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public ArrayList<BorrowRecordDTO> getUnreturnedRecords(int userId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        ArrayList<BorrowRecordDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT r.id, user_id, book_id, b.title, borrow_date, due_date, return_date, r.status FROM borrow_records r LEFT JOIN books b ON r.book_id = b.id WHERE user_id = ? AND return_date IS NULL ORDER BY id ASC";

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
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public BorrowRecordDTO getBorrowRecord(int userId, int bookId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        BorrowRecordDTO borrowRecord = null;

        try {
            con = DBUtils.getConnection();

            String sql = "SELECT * FROM borrow_records WHERE user_id = ? AND book_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                borrowRecord = new BorrowRecordDTO();

                borrowRecord.setId(rs.getInt("id"));
                borrowRecord.setUserId(rs.getInt("user_id"));
                borrowRecord.setBookId(rs.getInt("book_id"));
                borrowRecord.setBorrowDate(rs.getDate("borrow_date"));
                borrowRecord.setDueDate(rs.getDate("due_date"));
                borrowRecord.setReturnDate(rs.getDate("return_date"));
                borrowRecord.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return borrowRecord;
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
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean updateReturnStatus(int userId, int bookId, Date returnDate, String newStatus) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE borrow_records SET return_date = ?, status = ? WHERE user_id = ? AND book_id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, returnDate);
            stmt.setString(2, newStatus);
            stmt.setInt(3, userId);
            stmt.setInt(4, bookId);

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
