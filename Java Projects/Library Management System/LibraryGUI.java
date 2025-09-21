package library;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

public class LibraryGUI extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addBtn, borrowBtn, returnBtn, refreshBtn, searchBtn;
    private JTextField searchField;
    private JLabel titleLabel;

    public LibraryGUI() {
        setTitle("Library Management System");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        titleLabel = new JLabel("Library Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(148, 0, 211));
        add(titleLabel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        addBtn = new JButton("Add Book");
        borrowBtn = new JButton("Borrow Book");
        returnBtn = new JButton("Return Book");
        refreshBtn = new JButton("Refresh");

        searchField = new JTextField(15);
        searchField.setHorizontalAlignment(JTextField.CENTER);
        searchBtn = new JButton("Search");

        JButton[] buttons = {addBtn, borrowBtn, returnBtn, refreshBtn, searchBtn};
        for (JButton b : buttons) {
            b.setBackground(new Color(0, 102, 204));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setOpaque(true);
        }

        topPanel.add(addBtn);
        topPanel.add(borrowBtn);
        topPanel.add(returnBtn);
        topPanel.add(refreshBtn);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchBtn);

        add(topPanel, BorderLayout.PAGE_START);

        Vector<String> columns = new Vector<>();
        columns.add("ID");
        columns.add("Title");
        columns.add("Author");
        columns.add("Status");

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(148, 0, 211));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.WHITE); 
                if (column == 3) { 
                    String status = String.valueOf(value);
                    if ("Available".equalsIgnoreCase(status)) c.setForeground(new Color(0, 128, 0)); // green text
                    else if ("Borrowed".equalsIgnoreCase(status)) c.setForeground(Color.RED); // red text
                    else c.setForeground(Color.BLACK);
                } else {
                    c.setForeground(Color.BLACK);
                }
                if (isSelected) c.setBackground(new Color(173, 216, 230));
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 350));
        add(scrollPane, BorderLayout.CENTER);

        loadTableData();

        addBtn.addActionListener(e -> onAddBook());
        borrowBtn.addActionListener(e -> onBorrowBook());
        returnBtn.addActionListener(e -> onReturnBook());
        refreshBtn.addActionListener(e -> loadTableData());
        searchBtn.addActionListener(e -> onSearch());

        setVisible(true);
    }

    private void loadTableData() {
        try {
            Vector<Vector<Object>> rows = Library.getAllBooks();
            tableModel.setRowCount(0);
            for (Vector<Object> row : rows) {
                Object status = row.get(3);
                if ("1".equals(String.valueOf(status))) row.set(3, "Available");
                else if ("0".equals(String.valueOf(status))) row.set(3, "Borrowed");
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data:\n" + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onAddBook() {
        String title = JOptionPane.showInputDialog(this, "Enter book title:");
        if (title == null || title.trim().isEmpty()) { JOptionPane.showMessageDialog(this, "Title is required."); return; }
        String author = JOptionPane.showInputDialog(this, "Enter author name:"); if (author == null) author = "";
        try {
            Library.addBook(title.trim(), author.trim());
            JOptionPane.showMessageDialog(this, "Book added successfully.");
            loadTableData();
        } catch (SQLException ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(this, "Error adding book:\n" + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);}
    }

    private void onBorrowBook() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Book ID to borrow:");
        if (idStr == null || idStr.trim().isEmpty()) return;
        int bookId; try { bookId = Integer.parseInt(idStr.trim()); } catch (NumberFormatException nfe) { JOptionPane.showMessageDialog(this, "Invalid Book ID."); return; }
        try {
            boolean ok = Library.borrowBook(bookId);
            JOptionPane.showMessageDialog(this, ok ? "Book borrowed." : "Book not available or not found.");
            loadTableData();
        } catch (SQLException ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(this, "Error borrowing book:\n" + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);}
    }

    private void onReturnBook() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Book ID to return:");
        if (idStr == null || idStr.trim().isEmpty()) return;
        int bookId; try { bookId = Integer.parseInt(idStr.trim()); } catch (NumberFormatException nfe) { JOptionPane.showMessageDialog(this, "Invalid Book ID."); return; }
        try {
            boolean ok = Library.returnBook(bookId);
            JOptionPane.showMessageDialog(this, ok ? "Book returned." : "Book not found.");
            loadTableData();
        } catch (SQLException ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(this, "Error returning book:\n" + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);}
    }

    private void onSearch() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) { loadTableData(); return; }
        try {
            Vector<Vector<Object>> rows = Library.searchBooks(keyword);
            tableModel.setRowCount(0);
            for (Vector<Object> row : rows) {
                Object status = row.get(3);
                if ("1".equals(String.valueOf(status))) row.set(3, "Available");
                else if ("0".equals(String.valueOf(status))) row.set(3, "Borrowed");
                tableModel.addRow(row);
            }
        } catch (SQLException ex) { ex.printStackTrace(); JOptionPane.showMessageDialog(this, "Error searching:\n" + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI());
    }
}
