/*
 * Window_ManagerUsers.java
 * by Kevin Lin (lin2391@bu.edu)
 * 23APR2023
 *
 * This is a window for manager to interact with the users
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window_ManagerUsers implements ActionListener{
    private JFrame f;
    private Manager m;
    private JLabel l_welcome;
    private JComboBox cb_users;
    private JButton b_view;
    private JButton b_notify;
    private JButton b_notifyAll;
    private JButton b_block;
    private JTextField tf_notify;
    private JButton b_update;
    private JComboBox <String> cb_blocked;
    private JButton b_unblock;
    private JButton b_unblockAll;
    private JButton b_cancel;
    private JComboBox <String> cb_eligible;
    private SQL sql;

    public Window_ManagerUsers(Manager m){
        f = new JFrame("Manager");
        this.m = m;
        l_welcome = new JLabel("Welcome, " + m.getUsername() + "!");
        cb_users = new JComboBox<String>();
        b_view = new JButton("View");
        b_notify = new JButton("Notify");
        b_notifyAll = new JButton("Notify All");
        b_block = new JButton("Block");
        tf_notify = new JTextField();
        b_update = new JButton("Update Minimum Balance");
        b_cancel = new JButton("Cancel");
        for (int i = 0; i < m.getApprovedUsers().size(); i++){
            cb_users.addItem(m.getApprovedUsers().get(i).getUsername());
        }
        b_unblock = new JButton("Unblock");
        b_unblockAll = new JButton("Unblock All");
        cb_blocked = new JComboBox<String>();
        for (int i = 0; i < m.getBlockedUsers().size(); i++){
            cb_blocked.addItem(m.getBlockedUsers().get(i).getUsername());
        }
        cb_eligible = new JComboBox<String>();
        for (int i = 0; i < m.getEligibleUsers().size(); i++){
            cb_eligible.addItem(m.getEligibleUsers().get(i).getUsername());
        }

        b_view.addActionListener(this);
        b_notify.addActionListener(this);
        b_notifyAll.addActionListener(this);
        b_block.addActionListener(this);
        b_cancel.addActionListener(this);
        b_update.addActionListener(this);
        b_unblock.addActionListener(this);
        b_unblockAll.addActionListener(this);

        l_welcome.setBounds(50, 0, 200, 30);
        cb_users.setBounds(50, 50, 200, 30);
        b_view.setBounds(50, 100, 200, 30);
        b_block.setBounds(50, 150, 200, 30);
        cb_blocked.setBounds(50, 200, 200, 30);
        b_unblock.setBounds(50, 250, 200, 30);
        b_unblockAll.setBounds(50, 300, 200, 30);
        tf_notify.setBounds(50, 350, 200, 30);
        b_update.setBounds(50, 400, 200, 30);
        cb_eligible.setBounds(50, 450, 200, 30);
        b_notify.setBounds(50, 500, 200, 30);
        b_notifyAll.setBounds(50, 550, 200, 30);
        b_cancel.setBounds(50, 600, 200, 30);

        f.add(l_welcome);
        f.add(cb_users);
        f.add(b_view);
        f.add(b_block);
        f.add(cb_blocked);
        f.add(b_unblock);
        f.add(b_unblockAll);
        f.add(tf_notify);
        f.add(b_update);
        f.add(cb_eligible);
        f.add(b_notify);
        f.add(b_notifyAll);
        f.add(b_cancel);

        f.setSize(500, 800);
        f.setLayout(null);
        f.setVisible(true);
        sql = new SQL();
    }
    public void actionPerformed(ActionEvent e) {
        String s = "Hello! We are pleased to inform you that your account has been upgraded to allow trading options!";
        if (e.getSource() == b_view) {                  // View user
            new Window_User(m.getApprovedUsers().get(cb_users.getSelectedIndex()),1);
        }
        else if (e.getSource() == b_block) {            // Block user
            User u = sql.getUser((String)cb_users.getSelectedItem());
            sql.blockUser(u);
            update_frame();
            JOptionPane.showMessageDialog(null, u.getUsername() + " has been blocked.");
        }
        else if (e.getSource() == b_unblock) {          // Unblock user
            User u = sql.getUser((String)cb_blocked.getSelectedItem());
            sql.unblockUser(u);
            update_frame();
            JOptionPane.showMessageDialog(null, u.getUsername() + " has been unblocked.");
        }
        else if (e.getSource() == b_unblockAll) {        // Unblock all users
            sql.unblockAllUsers();
            update_frame();
            JOptionPane.showMessageDialog(null, "All users have been unblocked.");
        }
        else if (e.getSource() == b_update) {             // Update minimum balance
            if (tf_notify.getText().isEmpty()){
                return;
            }
            else{
                int val = Integer.parseInt(tf_notify.getText());
                sql.updateMinToBeSuper(val);
                JOptionPane.showMessageDialog(null, "Minimum balance to be a super user has been updated to " + val);
            }
        }
        else if (e.getSource() == b_notify) {               // Notify user
            if (cb_eligible.getSelectedIndex() == -1){
                return;
            }
            new Window_EmailNotification(s, m.getEligibleUsers().get(cb_eligible.getSelectedIndex()).getEmail());
            sql.approveSuperUser(m.getEligibleUsers().get(cb_eligible.getSelectedIndex()));
        }
        else if (e.getSource() == b_notifyAll) {        // Notify all users
            for (int i = 0; i < m.getEligibleUsers().size(); i++){
                new Window_EmailNotification(s, m.getEligibleUsers().get(i).getEmail());
            }
        }
        else if (e.getSource() == b_cancel) {
            f.dispose();
        }
    }

    public void update_frame(){
        f.dispose();
        new Window_ManagerUsers(m);
    }
}
