import java.sql.*;

public class SQL {
    private Connection connect() throws ClassNotFoundException {
        // SQLite connection string
        String url = "jdbc:sqlite:/Users/kevin/stocktrading.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            System.out.println("Connection Successful!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertCustomer(int id, String firstName, String lastName, String username, String password, int balance) {
        String sql = "INSERT INTO Customers(id, firstName, lastName, userName, password, balance) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, username);
            pstmt.setString(5, password);
            pstmt.setInt(6, balance);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertManager(int id, String firstName, String lastName, String username, String password) {
        String sql = "INSERT INTO Managers(id, firstName, lastName, userName, password) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, username);
            pstmt.setString(5, password);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertStock(int id, String name, int price) {
        String sql = "INSERT INTO Stocks(id, name, price) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, price);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTrade(int stockID, int customerID) {
        String sql = "INSERT INTO Trades(stockID, customerID) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stockID);
            pstmt.setInt(2, customerID);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
