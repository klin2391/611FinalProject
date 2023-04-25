/*
 * Manager.java
 * by Kevin Lin (lin2391@bu.edu)
 * 23APR2023
 *
 * This class is used to represent a manager. Will have approved users and
 * pending users. Should have the ability to approve/deny users.
 * View users, and change stocks
 *
 * TODO: Singleton
 */

import java.util.*;
import java.util.Random;
public class Manager extends Person{
    private ArrayList<User> pendingApproval;
    private ArrayList<User> approvedUsers;
    private SQL sql;

    public Manager(){
        username = "admin";
        password = "admin";
        email = "admin@mail.com";
        pendingApproval = new ArrayList<User>();
        approvedUsers = new ArrayList<User>();
        sql = new SQL();
    }

    //Constructor with parameters
    public Manager(String email, String username, String password){
        this.username = username;
        this.password = password;
        this.email = email;
        sql = new SQL();
        approvedUsers = sql.getAllCustomers();
        pendingApproval = sql.getAllPendingCustomers();

    }

    public ArrayList<User> getPendingApproval(){
        pendingApproval = sql.getAllPendingCustomers();
        return pendingApproval;

    }
    public ArrayList<User> getApprovedUsers(){
        return approvedUsers;
    }

    public ArrayList <User> getBlockedUsers(){
        return sql.getBlockedUsers();
    }

    public ArrayList<User> getEligibleUsers(){
        return sql.getEligibleSupers();
    }

    // Approves a single user
    public void approveUser(User user){
        if (sql.customerExists(user.getUsername())){        // If the user already exists, don't add them
            return;
        }
        approvedUsers.add(user);                            // Add the user to the approved users list
        sql.insertCustomer(sql.getNextID("Customers"), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword(),(int) user.getBalance());
        pendingApproval.remove(user);                       // Remove the user from the pending approval list
        sql.removeUserFromPending(user.getUsername());
        new Window_EmailNotification("User " + user.getFirstName() + " has been approved!", user.getEmail());
    }

    public void denyUser(User user){
        pendingApproval.remove(user);
        sql.removeUserFromPending(user.getUsername());
        new Window_EmailNotification("User " + user.getFirstName() + " has been denied.", user.getEmail());
    }

    public void approveAll(ArrayList <User> users){
        for (User user : users){
            approveUser(user);
        }
    }

    public void denyAll(ArrayList <User> users){
        for (User user : users){
            denyUser(user);
        }
    }

    //update value of one stock randomly
    public void randomUpdateStock(Stock stock){
        double price = stock.getCurrentPrice();
        double maxFlunctuation = price * 0.05;
        Random rand = new Random();

        double flunctuation = rand.nextDouble() * maxFlunctuation;

        int flip = rand.nextInt() % 2; //flip 0 decrease; flip 1increase

        if(flip==0){
            price -= flunctuation;
        }
        else{
            price += flunctuation;
        }
        stock.setPrice(price);
    }

    public void randomUpdateAll(ArrayList<Stock> stocks){ //update value of all stock randomly
        for (Stock s : stocks){
            randomUpdateStock(s);
        }
    }

    //update value of one stock manually
    public void updateStock(Stock stock){
        Scanner scanner = new Scanner(System.in);
        double price = 0.0;
        boolean validInput = false;

        while (!validInput) {
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                validInput = true;
            } else {
//                messageToManager = "Invalid input. Please enter a double value.";
                scanner.next();
            }
        }
        stock.setPrice(price);
    }

    public String[][] trackProfit(){ //track profit of all users
        String[][] table = new String[approvedUsers.size()][3];
        int idx = 0;
        for(User user : approvedUsers){
            user.setUnrealizedProfit(); //calculate the unrealized profit
            double unrealizedProfit = user.getUnrealizedProfit();
            double realizedProfit = user.getProfit();
            String name = user.getUsername();

            table[idx][0] = name;
            table[idx][1] = String.valueOf(realizedProfit);
            table[idx][2] = String.valueOf(unrealizedProfit);
            idx+=1;
        }
        return table;
    }

    public ArrayList<User> over10k(){ // return the users who have mare than 10k profit
        ArrayList<User> goodUser = new ArrayList<>();
        for(User user : approvedUsers){
            if (user.getProfit() >= 10000){
                goodUser.add(user);
            }
        }
        return goodUser;
    }
}
