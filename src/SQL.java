import java.sql.*;
import java.util.*;

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

    public void insertCustomer(int id, String firstName, String lastName, String email, String username, String password, int balance) {
        String sql = "INSERT INTO Customers(id, firstName, lastName, email, userName, password, balance) VALUES(?,?,?,?,?,?,?)";
        //String sql = "INSERT INTO Customers(id, firstName, lastName, balance, email, userName, password) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setString(5, username);
            pstmt.setString(6, password);
            pstmt.setInt(7, balance);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePassword(String username, String password){
//        String sql = "UPDATE Customers SET userName = ? WHERE email = ?";
        String sql = "UPDATE Customers SET password = ? WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, password);
            pstmt.setString(2, username);
            // update
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        sql = "UPDATE Managers SET password = ? WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, password);
            pstmt.setString(2, username);
            // update
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBalance(String username, int balance){
        String sql = "UPDATE Customers SET balance = ? WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, balance);
            pstmt.setString(2, username);
            // update
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNextID(String table){
        String sql = "SELECT * FROM " + table;
        int id = 0;
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return id + 1;
    }

    public boolean customerExists(String username) {
//        String sql = "SELECT * FROM Customers WHERE email = ?";
        String sql = "SELECT * FROM Customers WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,username);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                System.out.println("Customer Exists!");
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        sql = "SELECT * FROM PendingCustomers WHERE userName = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,username);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                System.out.println("Customer Exists!");
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        sql = "SELECT * FROM Managers WHERE userName = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,username);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                System.out.println("Customer Exists!");
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Customer Does Not Exist!");
        return false;
    }

    public boolean verifyCustomerAccount(String username, String password){
//        String sql = "SELECT * FROM Customers WHERE email = ? AND userName = ?";
        String sql = "SELECT * FROM Customers WHERE userName = ? AND password = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                System.out.println("Login Successful!");
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Login Failed!");
        return false;
    }

    public String recoverPassword(String username, String email){
//        String sql = "SELECT * FROM Customers WHERE email = ? AND balance = ?";
        String sql = "SELECT * FROM Customers WHERE userName = ? AND email = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,username);
            pstmt.setString(2,email);

            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                System.out.println("Password Recovery Successful!");
                return rs.getString("password");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Password Recovery Failed!");
        return null;
    }

    public User getUser(String username){
//            String sql = "SELECT * FROM Customers WHERE email = ?";
        String sql = "SELECT * FROM Customers WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,username);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                return new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("userName"), rs.getString("password"), rs.getInt("balance"), rs.getDouble("profit"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Account not Found!");
        return null; }

    public void queryCustomer(String username){
//        String sql = "SELECT * FROM Customers WHERE email = ?";
        String sql = "SELECT * FROM Customers WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,username);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("firstName") + "\t" +
                        rs.getString("lastName") + "\t" +
                        rs.getString("userName") + "\t" +
                        rs.getString("password") + "\t" +
                        rs.getInt("balance"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<User> getAllCustomers(){
        ArrayList<User> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customers";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                customers.add(new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("userName"), rs.getString("password"), rs.getInt("balance"), rs.getDouble("profit")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    public void insertPending(int id, String firstName, String lastName, String email, String username, String password) {
        String sql = "INSERT INTO PendingCustomers(id, firstName, lastName, email, userName, password) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setString(5, username);
            pstmt.setString(6, password);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getPendingUser(String username){
            String sql = "SELECT * FROM PendingCustomers WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,username);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                return new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("userName"), rs.getString("password"), 0, 0.0);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Account not Found!");
        return null;

    }
    public void removeUserFromPending(String username){
        String sql = "DELETE FROM PendingCustomers WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, username);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<User> getAllPendingCustomers(){
        ArrayList<User> customers = new ArrayList<>();
        String sql = "SELECT * FROM PendingCustomers";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                customers.add(new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("userName"), rs.getString("password"), 0, 0.0));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    public void insertManager(int id, String email, String lastName, String username, String password) {
        String sql = "INSERT INTO Managers(id, email, lastName, userName, password) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, email);
            pstmt.setString(3, lastName);
            pstmt.setString(4, username);
            pstmt.setString(5, password);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean verifyManagerAccount(String username, String password) {
        String sql = "SELECT * FROM Managers WHERE userName = ? AND password = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                System.out.println("Login Successful!");
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Login Failed!");
        return false;
    }

    public Manager getManager(String username){
            String sql = "SELECT * FROM Managers WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,username);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                return new Manager(rs.getString("email"), rs.getString("userName"), rs.getString("password"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Account not Found!");
        return null;
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

    public boolean checkIfUserBlacklisted(String username){
        String sql = "SELECT * FROM Blacklisted WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                System.out.println("User is Blacklisted!");
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("User is not Blacklisted!");
        return false;
    }

    public void blockUser(User u){
        String username = u.getUsername();
        String sql = "INSERT INTO Blacklisted(userName) VALUES(?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<User> getBlockedUsers(){
        ArrayList<User> customers = new ArrayList<>();
        String sql = "SELECT * FROM Blacklisted";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                customers.add(getUser( rs.getString("userName")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    public void unblockUser(User u){
        String username = u.getUsername();
        String sql = "DELETE FROM Blacklisted WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, username);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unblockAllUsers() {
        String sql = "DELETE FROM Blacklisted";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEligible(User u) {
        String username = u.getUsername();
        String sql = "INSERT INTO EligibleSupers(userName) VALUES(?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isEligibleToBeSuper(User u) {
        String username = u.getUsername();
        String sql = "SELECT * FROM EligibleSupers WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                System.out.println("User is Eligible!");
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("User is not Eligible!");
        return false;
    }

    public void updateProfit(String username, int profit) {
        String sql = "UPDATE Customers SET profit = ? WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, profit);
            pstmt.setString(2, username);
            // update
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getMinToBeSuper(){
//        String sql = "SELECT * FROM Customers WHERE email = ?";
        String sql = "SELECT * FROM Managers";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("minToBeSuper"));
                return rs.getInt("minToBeSuper");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void updateMinToBeSuper(int min){
        String sql = "UPDATE Managers SET minToBeSuper = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, min);
            // update
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList <User> getEligibleSupers(){
        ArrayList<User> customers = new ArrayList<>();
        String sql = "SELECT * FROM EligibleSupers WHERE Approved = 0";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                customers.add(getUser( rs.getString("userName")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    public void approveSuperUser(User u){
        String username = u.getUsername();
        String sql = "UPDATE EligibleSupers SET Approved = 1 WHERE userName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, username);
            // update
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isSuperAccount(String username){
        String sql = "SELECT * FROM EligibleSupers WHERE userName = ? and Approved = 1";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                System.out.println("User is Super!");
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("User is not Super!");
        return false;
    }
}
