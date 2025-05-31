/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library_management.dto.FineDTO;
import library_management.utils.DBUtils;

/**
 *
 * @author Slayer
 */
public class FineDAO {
    public ArrayList<FineDTO> listFines() throws SQLException, ClassNotFoundException {
        Connection con = null;
        ArrayList<FineDTO> fines = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT id, borrow_id, fine_amount, paid_status FROM fines";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FineDTO fine = new FineDTO();
                fine.setId(rs.getInt("id"));
                fine.setBorrowId(rs.getInt("borrow_id"));
                fine.setFineAmount(rs.getDouble("fine_amount"));
                fine.setPaidStatus(rs.getString("paid_status"));
                fines.add(fine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return fines;
    }

    public ArrayList<FineDTO> getFinesByBorrowId(int borrowId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        ArrayList<FineDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT id, borrow_id, fine_amount, paid_status FROM fines WHERE borrow_id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, borrowId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FineDTO fine = new FineDTO();
                fine.setId(rs.getInt("id"));
                fine.setBorrowId(rs.getInt("borrow_id"));
                fine.setFineAmount(rs.getDouble("fine_amount"));
                fine.setPaidStatus(rs.getString("paid_status"));
                list.add(fine);
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
        return list;
    }

    public boolean addFine(FineDTO fine) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();
            String sql = "INSERT INTO fines (borrow_id, fine_amount, paid_status) VALUES (?, ?, ?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, fine.getBorrowId());
            stmt.setDouble(2, fine.getFineAmount());
            stmt.setString(3, fine.getPaidStatus());

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

    public boolean updatePaidStatus(int fineId, String newStatus) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE fines SET paid_status = ? WHERE id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, newStatus);
            stmt.setInt(2, fineId);

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
