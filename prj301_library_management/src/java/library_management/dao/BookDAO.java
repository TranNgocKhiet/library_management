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
import library_management.dto.BookDTO;
import library_management.utils.DBUtils;

/**
 *
 * @author Slayer
 */
public class BookDAO {    
    public ArrayList<BookDTO> listBook() throws SQLException, ClassNotFoundException {
        ArrayList<BookDTO> list = new ArrayList<BookDTO>();
        Connection con = null;
        
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT id, title, author, isbn, category, published_year, total_copies, available_copies, status FROM books ORDER BY id ASC";
            
            BookDTO book = null;
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    book = new BookDTO();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setCategory(rs.getString("category"));
                    book.setPublishedYear(rs.getInt("published_year"));
                    book.setTotalCopies(rs.getInt("total_copies"));
                    book.setAvailableCopies(rs.getInt("available_copies"));
                    book.setStatus(rs.getString("status"));
                    
                    list.add(book);
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
        return list;
    }
    
    public ArrayList<BookDTO> listBook(String searchValue) throws SQLException, ClassNotFoundException {
        ArrayList<BookDTO> list = new ArrayList<BookDTO>();
        Connection con = null;
        
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT id, title, author, isbn, category, published_year, total_copies, available_copies, status FROM books"
                    + " WHERE title LIKE ? OR author LIKE ? OR category LIKE ? ORDER BY id ASC";
            
            BookDTO book = null;
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + searchValue + "%");
            stmt.setString(2, "%" + searchValue + "%");
            stmt.setString(3, "%" + searchValue + "%");
            ResultSet rs = stmt.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    book = new BookDTO();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setCategory(rs.getString("category"));
                    book.setPublishedYear(rs.getInt("published_year"));
                    book.setTotalCopies(rs.getInt("total_copies"));
                    book.setAvailableCopies(rs.getInt("available_copies"));
                    book.setStatus(rs.getString("status"));
                    
                    list.add(book);
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
        return list;
    }
    
    public BookDTO getBookById (int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        BookDTO book = null;
        
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT id, title, author, isbn, category, published_year, total_copies, available_copies, status FROM books WHERE id LIKE ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs != null) {
                if (rs.next()) {
                    book = new BookDTO();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setCategory(rs.getString("category"));
                    book.setPublishedYear(rs.getInt("published_year"));
                    book.setTotalCopies(rs.getInt("total_copies"));
                    book.setAvailableCopies(rs.getInt("available_copies"));
                    book.setStatus(rs.getString("status"));
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
        return book;
    }
}
