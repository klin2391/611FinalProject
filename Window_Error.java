/*
 * Window_Error.java
 * by Kevin Lin (lin2391@bu.edu)
 * 17APR2023
 * 
 * This is a simple error window that closes window
 */

import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.awt.*;

public class Window_Error implements ActionListener{
    private JFrame f;
    private JLabel l_errorMessage;
    private JButton b_ok;
    
    public Window_Error(String errorMessage){
        f = new JFrame("Error!");
        l_errorMessage= new JLabel(errorMessage + "\n");
        l_errorMessage.setBounds(0, 50, 200, 50);
        b_ok = new JButton("OK");
        b_ok.setBounds(0, 100, 100, 30);
        f.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        f.add(l_errorMessage, gbc);
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
