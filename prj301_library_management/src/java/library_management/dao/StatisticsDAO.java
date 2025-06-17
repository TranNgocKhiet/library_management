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
import library_management.dto.MonthlyBorrowingStatistics;
import library_management.utils.DBUtils;

/**
 *
 * @author quang
 */
public class StatisticsDAO {

    public int getTotalBooks() throws SQLException, ClassNotFoundException {
        int total = 0;
        Connection conn = DBUtils.getConnection();

        String sql = "SELECT COUNT(id) AS total FROM books";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getInt("total");

        }

        rs.close();
        ps.close();
        conn.close();

        return total;
    }

    public int getTotalUsers() throws SQLException, ClassNotFoundException {
        int total = 0;
        Connection conn = DBUtils.getConnection();

        String sql = "SELECT COUNT(id) AS total FROM users";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getInt("total");

        }

        rs.close();
        ps.close();
        conn.close();

        return total;
    }

    public int getCurrentlyBorrowedBooks()
            throws SQLException, ClassNotFoundException {
        int total = 0;
        Connection conn = DBUtils.getConnection();

        String sql = "SELECT COUNT(id) AS total FROM borrow_records WHERE return_date IS NULL";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getInt("total");

        }

        rs.close();
        ps.close();
        conn.close();

        return total;
    }

    public ArrayList<MonthlyBorrowingStatistics> getMonthlyBorrowingStats()
            throws SQLException, ClassNotFoundException {
        ArrayList<MonthlyBorrowingStatistics> list = new ArrayList<>();
        Connection conn = DBUtils.getConnection();

        String sql = "SELECT YEAR(borrow_date) AS year, MONTH(borrow_date) AS month, COUNT(id) AS total FROM borrow_records GROUP BY YEAR(borrow_date), MONTH(borrow_date)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            MonthlyBorrowingStatistics stat = new MonthlyBorrowingStatistics();
            stat.setYear(rs.getInt("year"));
            stat.setMonth(rs.getInt("month"));
            stat.setTotal(rs.getInt("total"));
            list.add(stat);
        }

        rs.close();
        ps.close();
        conn.close();

        return list;
    }

    public ArrayList<BookDTO> getMostBorrowedBooks()
            throws SQLException, ClassNotFoundException {
        ArrayList<BookDTO> list = new ArrayList<>();
        Connection conn = DBUtils.getConnection();

        String sql = "SELECT TOP 5 book_id, COUNT(id) AS borrow_count FROM borrow_records GROUP BY book_id ORDER BY borrow_count DESC";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int bookId = rs.getInt("book_id");

            BookDTO book = getBookById(bookId);
            book.setBorrowCount(rs.getInt("borrow_count"));
            list.add(book);
        }

        rs.close();
        ps.close();
        conn.close();

        return list;
    }

    private BookDTO getBookById(int bookId) throws SQLException, ClassNotFoundException {
        BookDTO book = new BookDTO();
        Connection conn = DBUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE id = ?");
        ps.setInt(1, bookId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
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
        }

        rs.close();
        ps.close();
        conn.close();

        return book;
    }

    public double getAverageBorrowDuration()
            throws SQLException, ClassNotFoundException {
        double average = 0;
        Connection conn = DBUtils.getConnection();

        String sql = "SELECT AVG(DATEDIFF(day, borrow_date, return_date )) AS average FROM borrow_records WHERE return_date IS NOT NULL";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            average = rs.getDouble("average");

        }

        rs.close();
        ps.close();
        conn.close();

        return average;
    }

}
