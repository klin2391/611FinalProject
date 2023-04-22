import java.util.*;
public class Manager extends Person{
    private ArrayList<User> pendingApproval;
    private ArrayList<User> approvedUsers;

    public Manager(){
        username = "admin";
        password = "admin";
        email = "admin@mail.com";
        pendingApproval = new ArrayList<User>();
        approvedUsers = new ArrayList<User>();
    }

    public Manager(String email, String username, String password){
        this.username = username;
        this.password = password;
        this.email = email;
        SQL sql = new SQL();
        approvedUsers = sql.getAllCustomers();
        pendingApproval = sql.getAllPendingCustomers();
//        pendingApproval = new ArrayList<User>();
//        approvedUsers = new ArrayList<User>();
    }
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }

    public ArrayList<User> getPendingApproval(){
        SQL sql = new SQL();
        pendingApproval = sql.getAllPendingCustomers();
        return pendingApproval;

    }
    public ArrayList<User> getApprovedUsers(){
        return approvedUsers;
    }
    public void updatePendingApproval(){
        // Get from table
    }

    public void addPendingApproval(User user){
        pendingApproval.add(user);
    }

    public void updateApprovedUsers(){
        // Get from table
    }



    public void approveUser(User user){
        SQL sql = new SQL();
        approvedUsers.add(user);

        sql.insertCustomer(sql.getNextID("Customers"), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword(),(int) user.getBalance());
        pendingApproval.remove(user);
        sql.removeUserFromPending(user.getUsername());

        new Window_EmailNotification("User " + user.getFirstName() + " has been approved!", user.getEmail());
    }

    public void denyUser(User user){
        pendingApproval.remove(user);
        SQL sql = new SQL();
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
}
