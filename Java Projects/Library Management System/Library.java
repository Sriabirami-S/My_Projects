package library;

import java.sql.*;
import java.util.Vector;

public class Library {

    public static Vector<Vector<Object>> getAllBooks() throws SQLException {
        Vector<Vector<Object>> rows = new Vector<>();
        String sql = "SELECT book_id, title, author, status FROM books";

        try (Connection con = DBHelper.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("book_id"));    
                row.add(rs.getString("title"));
                row.add(rs.getString("author"));
                row.add(rs.getString("status"));
                rows.add(row);
            }
        }
        return rows;
    }

    public static void addBook(String title, String author) throws SQLException {
        String sql = "INSERT INTO books (title, author, status) VALUES (?, ?, 'Available')";

        try (Connection con = DBHelper.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, title);
            pst.setString(2, author);
            pst.executeUpdate();
        }
    }

    public static boolean borrowBook(int bookId) throws SQLException {
        String checkSql = "SELECT status FROM books WHERE book_id = ?";
        String updateSql = "UPDATE books SET status = 'Borrowed' WHERE book_id = ?";

        try (Connection con = DBHelper.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkSql);
             PreparedStatement updateStmt = con.prepareStatement(updateSql)) {

            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getString("status").equalsIgnoreCase("Available")) {
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
                return true;
            } else {
                return false; 
            }
        }
    }

    public static boolean returnBook(int bookId) throws SQLException {
        String checkSql = "SELECT status FROM books WHERE book_id = ?";
        String updateSql = "UPDATE books SET status = 'Available' WHERE book_id = ?";

        try (Connection con = DBHelper.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkSql);
             PreparedStatement updateStmt = con.prepareStatement(updateSql)) {

            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getString("status").equalsIgnoreCase("Borrowed")) {
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
                return true;
            } else {
                return false;            
                }
        }
    }

    public static Vector<Vector<Object>> searchBooks(String keyword) throws SQLException {
        Vector<Vector<Object>> rows = new Vector<>();
        String sql = "SELECT book_id, title, author, status FROM books WHERE title LIKE ? OR author LIKE ?";

        try (Connection con = DBHelper.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            String kw = "%" + keyword + "%";
            pst.setString(1, kw);
            pst.setString(2, kw);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("book_id"));  
                row.add(rs.getString("title"));
                row.add(rs.getString("author"));
                row.add(rs.getString("status"));
                rows.add(row);
            }
        }
        return rows;
    }

}
