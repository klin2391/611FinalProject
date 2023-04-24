/*
 * Person.java
 * by Kevin Lin (lin2391@bu.edu)
 * 23APR2023
 *
 * This abstract class represents a person
 * who signs into the system. Has a username, password,
 * and email
 *
 */

public abstract class Person {
    protected String username;
    protected String password;
    protected String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        SQL sql = new SQL();
        sql.updatePassword(this.username, this.password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
