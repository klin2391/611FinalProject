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
    private JButton b_cancel;

    public Window_ManagerUsers(Manager m){
        f = new JFrame("Manager");
        this.m = m;
        l_welcome = new JLabel("Welcome, " + m.getUsername() + "!");
        cb_users = new JComboBox<String>();
        b_view = new JButton("View");
        b_notify = new JButton("Notify");
        b_notifyAll = new JButton("Notify All");
        b_block = new JButton("Block");
        b_cancel = new JButton("Cancel");
        for (int i = 0; i < m.getApprovedUsers().size(); i++){
            cb_users.addItem(m.getApprovedUsers().get(i).getFirstName() + " " + m.getApprovedUsers().get(i).getLastName());
        }

        b_view.addActionListener(this);
        b_notify.addActionListener(this);
        b_notifyAll.addActionListener(this);
        b_block.addActionListener(this);
        b_cancel.addActionListener(this);

        l_welcome.setBounds(50, 0, 200, 30);
        cb_users.setBounds(50, 50, 200, 30);
        b_view.setBounds(50, 100, 200, 30);
        b_notify.setBounds(50, 150, 200, 30);
        b_notifyAll.setBounds(50, 200, 200, 30);
        b_block.setBounds(50, 250, 200, 30);
        b_cancel.setBounds(50, 300, 200, 30);

        f.add(l_welcome);
        f.add(cb_users);
        f.add(b_view);
        f.add(b_notify);
        f.add(b_notifyAll);
        f.add(b_block);
        f.add(b_cancel);

        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_view) {
            System.out.println("VIEW");
        }
        else if (e.getSource() == b_notify) {
            System.out.println("NOTIFY");
        }
        else if (e.getSource() == b_notifyAll) {
            System.out.println("NOTIFY ALL");
        }
        else if (e.getSource() == b_block) {
            System.out.println("BLOCK");
        }
        else if (e.getSource() == b_cancel) {
            f.dispose();
        }
    }
}
