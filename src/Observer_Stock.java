/*
 * Observer_Stock.java
 * by Kevin Lin (lin2391@bu.edu)
 * 05MAY2023
 *
 * This interface is used to update listeners
 * when stocks are updated. Used for users and windows
 */

public interface Observer_Stock {
    public void update();
    public void register(Manager m);
}
