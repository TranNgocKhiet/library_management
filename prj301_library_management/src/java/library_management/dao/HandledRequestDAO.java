/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import library_management.dto.HandledRequestDTO;
import library_management.utils.DBUtils;

/**
 *
 * @author quang
 */
public class HandledRequestDAO {

    public boolean addHandledRequest(HandledRequestDTO request) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO handled_requests (request_id, user_id, book_id, request_type, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, request.getRequestId());
            ps.setInt(2, request.getUserId());
            ps.setInt(3, request.getBookId());
            ps.setString(4, request.getRequestType());
            ps.setString(5, request.getStatus());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public ArrayList<HandledRequestDTO> getAllHandledRequest() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM handled_requests";
        ArrayList<HandledRequestDTO> list = new ArrayList<>();

        try ( Connection con = DBUtils.getConnection();  Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                HandledRequestDTO request = new HandledRequestDTO();
                request.setHandledRequestId(rs.getInt("handled_request_id"));
                request.setRequestId(rs.getInt("request_id"));
                request.setUserId(rs.getInt("user_id"));
                request.setBookId(rs.getInt("book_id"));
                request.setRequestType(rs.getString("request_type"));
                request.setStatus(rs.getString("status"));
                list.add(request);
            }
        }

        return list;
    }

     public boolean setHandledRequestStatusById(int id, String status) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();

            String sql = "UPDATE handled_requests SET status = ? WHERE handled_request_id = ?";

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
