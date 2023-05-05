
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.*;

public class Window_ManagerProfitTable implements Observer_Manager{// JTable of profit of all users

    // frame
    private JFrame f;
    // Table
    private JPanel p;
    private JTable j;

    // Constructor
    Window_ManagerProfitTable(Manager m) {
        // Frame initialization
        f = new JFrame();
        register(m);
        // Frame Title
        f.setTitle("Profit Report");

        // Data to be displayed in the JTable
        String[][] data = m.trackProfit();

        // Column Names
        String[] columnNames = {"User Name", "Realized Profit", "Unrealized Profit"};
        p = new JPanel();
        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        p.add(sp);
        f.add(p);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }

    public void register(Manager m){
        m.registerWindow(this);
    }

    public void update(Manager m){
        String[][] data = m.trackProfit();
        System.out.println("Profit Table Updated");
        String[] columnNames = {"User Name", "Realized Profit", "Unrealized Profit"};
        JTable j1 = new JTable(data, columnNames);
        j1.setBounds(30, 40, 200, 300);
        JScrollPane sp1 = new JScrollPane(j1);
        p.setVisible(false);
        f.remove(p);
        p = new JPanel();
        p.add(sp1);
        f.add(p);
        p.setVisible(true);
    }
}
