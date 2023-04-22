import javax.swing.*;
import java.awt.event.*;

public class Window_ManagerApprove implements ActionListener{
    private JFrame f;
    private Manager m;
    private JComboBox<String> cb_users;
    private JButton b_approve;
    private JButton b_approveAll;
    private JButton b_reject;
    private JButton b_rejectAll;
    private JButton b_cancel;
    private String selectedUser;

    public Window_ManagerApprove(Manager m){
        f = new JFrame("Approve Users");
        this.m = m;
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

        cb_users.setBounds(50, 50, 200, 30);
        b_approve.setBounds(50, 100, 200, 30);
        b_approveAll.setBounds(50, 150, 200, 30);
        b_reject.setBounds(50, 200, 200, 30);
        b_rejectAll.setBounds(50, 250, 200, 30);
        b_cancel.setBounds(50, 300, 200, 30);

        f.add(cb_users);
        f.add(b_approve);
        f.add(b_approveAll);
        f.add(b_reject);
        f.add(b_rejectAll);
        f.add(b_cancel);

        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);


    }

    public void updateComboBox(){
        cb_users.removeAllItems();
        for (int i = 0; i < m.getPendingApproval().size(); i++){
            cb_users.addItem(m.getPendingApproval().get(i).getUsername());
        }
    }

    public void actionPerformed(ActionEvent e) {
        SQL sql = new SQL();
        if (e.getSource() == b_approve) {
            User u = sql.getPendingUser((String)cb_users.getSelectedItem());
            m.approveUser(u);
            System.out.println("Approve " + u.getFirstName());
            updateComboBox();

        }
        else if (e.getSource() == b_approveAll) {
            while (cb_users.getSelectedItem() != null){
                User u = sql.getPendingUser((String)cb_users.getSelectedItem());
                m.approveUser(u);
                System.out.println("Approve " + u.getFirstName());
                updateComboBox();
            }
        }
        else if (e.getSource() == b_reject) {
            User u = sql.getPendingUser((String)cb_users.getSelectedItem());
            m.denyUser(u);
            System.out.println("deny " + u.getFirstName());
            updateComboBox();
            System.out.println("Reject");
        }
        else if (e.getSource() == b_rejectAll) {
            while (cb_users.getSelectedItem() != null){
                User u = sql.getPendingUser((String)cb_users.getSelectedItem());
                m.denyUser(u);
                System.out.println("deny " + u.getFirstName());
                updateComboBox();
            }
            System.out.println("Reject All");
        } else if (e.getSource() == b_cancel) {
            f.dispose();
        }
        else{
            JComboBox cb = (JComboBox)e.getSource();                                // If dropdown is changed
            selectedUser = (String)cb.getSelectedItem();
        }
    }

}
