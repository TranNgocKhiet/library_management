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
            String sql = "SELECT id, title, author, isbn, category, published_year, total_copies, available_copies,image, status FROM books ORDER BY id ASC";

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
                    book.setImage(rs.getString("image"));
                    list.add(book);
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
        return list;
    }

    public ArrayList<BookDTO> listBook(String searchValue) throws SQLException, ClassNotFoundException {
        ArrayList<BookDTO> list = new ArrayList<BookDTO>();
        Connection con = null;

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT id, title, author, isbn, category, published_year, total_copies, available_copies,image, status FROM books"
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
                    book.setImage(rs.getString("image"));
                    list.add(book);
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
        return list;
    }

    public BookDTO getBookById(int id) throws SQLException, ClassNotFoundException {
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
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    public boolean addBook(String title, String author, String isbn, String category, int publishedYear, int totalCopies, int availableCopies, String image) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            String insert = "INSERT INTO [library_system].[dbo].[books] (title, author, isbn, category, published_year, total_copies, available_copies, image, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'active')";
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, isbn);
            ps.setString(4, category);
            ps.setInt(5, publishedYear);
            ps.setInt(6, totalCopies);
            ps.setInt(7, availableCopies);
            ps.setString(8, image);
            int rowsAffected = ps.executeUpdate();
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

    public boolean editBook(int id, String category, int publishedYear, int totalCopies, int availableCopies, String image, String status) throws SQLException, ClassNotFoundException {
        Connection con = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            String insert = "UPDATE [library_system].[dbo].[books] SET category = ?, published_year = ?, total_copies = ?, available_copies = ?, image = ?, status = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1, category);
            ps.setInt(2, publishedYear);
            ps.setInt(3, totalCopies);
            ps.setInt(4, availableCopies);
            ps.setString(5, image);
            ps.setString(6, status);
            ps.setInt(7, id);
            int rowsAffected = ps.executeUpdate();
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
}
