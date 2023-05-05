/*
 * Window_ManagerApprove.java
 * by Kevin Lin (lin2391@bu.edu)
 * 23APR2023
 *
 * This is a window for manager to approve new account requests
 */

import javax.swing.*;
import java.awt.event.*;

public class Window_ManagerApprove extends JPanel implements ActionListener{
//    private JFrame f;
    private Window w;
    private Manager m;
    private JComboBox<String> cb_users;
    private JButton b_approve;
    private JButton b_approveAll;
    private JButton b_reject;
    private JButton b_rejectAll;
    private JButton b_cancel;
    private String selectedUser;
    private SQL sql;

    // Constructor
    public Window_ManagerApprove(Window w){
//        f = new JFrame("Approve Users");
        this.w = w;
        this.m = Manager.getInstance();
        cb_users = new JComboBox<String>();
        for (int i = 0; i < m.getPendingApproval().size(); i++){
            cb_users.addItem(m.getPendingApproval().get(i).getUsername());
        }
        selectedUser = (String)cb_users.getSelectedItem();
        b_approve = new JButton("Approve");
        b_approveAll = new JButton("Approve All");
        b_reject = new JButton("Reject");
        b_rejectAll = new JButton("Reject All");
        b_cancel = new JButton("Cancel");

        b_approve.addActionListener(this);
        b_approveAll.addActionListener(this);
        b_reject.addActionListener(this);
        b_rejectAll.addActionListener(this);
        b_cancel.addActionListener(this);

        cb_users.setBounds(50, 50, 200, 60);
        b_approve.setBounds(50, 100, 200, 30);
        b_approveAll.setBounds(50, 150, 200, 30);
        b_reject.setBounds(50, 200, 200, 30);
        b_rejectAll.setBounds(50, 250, 200, 30);
        b_cancel.setBounds(50, 300, 200, 30);

        this.add(cb_users);
        this.add(b_approve);
        this.add(b_approveAll);
        this.add(b_reject);
        this.add(b_rejectAll);
        this.add(b_cancel);

        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        sql = new SQL();
    }

    // Updates the combo box fields
    public void updateComboBox(){
        cb_users.removeAllItems();
        for (int i = 0; i < m.getPendingApproval().size(); i++){            // adds the remaining pending users
            cb_users.addItem(m.getPendingApproval().get(i).getUsername());
        }
    }

    // Action Listener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_approve) {
            User u = sql.getPendingUser((String)cb_users.getSelectedItem());
            m.approveUser(u);
            updateComboBox();
        }
        else if (e.getSource() == b_approveAll) {
            while (cb_users.getSelectedItem() != null){
                User u = sql.getPendingUser((String)cb_users.getSelectedItem());
                m.approveUser(u);
                updateComboBox();
            }
        }
        else if (e.getSource() == b_reject) {
            User u = sql.getPendingUser((String)cb_users.getSelectedItem());
            m.denyUser(u);
            updateComboBox();
        }
        else if (e.getSource() == b_rejectAll) {
            while (cb_users.getSelectedItem() != null){
                User u = sql.getPendingUser((String)cb_users.getSelectedItem());
                m.denyUser(u);
                updateComboBox();
            }
        }
        else if (e.getSource() == b_cancel) {
            w.update(new Window_Manager(Manager.getInstance(), w));
            w.setTitle("Manager");
        }
        else{
            JComboBox cb = (JComboBox)e.getSource();                                // If dropdown is changed
            selectedUser = (String)cb.getSelectedItem();
        }
    }

}
