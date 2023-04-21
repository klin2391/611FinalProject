/*
 * Window_EmailNotification.java
 * by Kevin Lin (lin2391@bu.edu)
 * 17APR2023
 * 
 * This is a simple error window that closes window
 */

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.awt.*;

public class Window_EmailNotification implements ActionListener{
    private JFrame f;
    private JLabel l_alertMessage;
    private JButton b_ok;
    
    public Window_EmailNotification(String alertMessage, String email){

        f = new JFrame(email);
        l_alertMessage= new JLabel(alertMessage + "\n");
        l_alertMessage.setBounds(0, 50, 200, 50);
        b_ok = new JButton("OK");
        b_ok.setBounds(0, 100, 100, 30);
        f.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        f.add(l_alertMessage, gbc);
        f.add(b_ok, gbc);
        b_ok.addActionListener(this);
        f.setSize(250, 150);
        f.setVisible(true);
    }

    // IF button is clicked, close window
    public void actionPerformed(ActionEvent e){
        f.dispose();
    }
}
