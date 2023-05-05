/*
 * Observer_Manager.java
 * by Kevin Lin (lin2391@bu.edu)
 * 05MAY2023
 *
 * This interface is used to update listeners
 * when the manager is updated. Used for manager windows
 */

public interface Observer_Manager {
    public void update(Manager m);
    public void register(Manager m);
}