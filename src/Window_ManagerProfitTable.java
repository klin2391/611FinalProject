
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Window_ManagerProfitTable {// JTable of profit of all users

    // frame
    private JFrame f;
    // Table
    private JTable j;

    // Constructor
    Window_ManagerProfitTable(Manager m) {
        // Frame initialization
        f = new JFrame();

        // Frame Title
        f.setTitle("Profit Report");

        // Data to be displayed in the JTable
        String[][] data = m.trackProfit();

        // Column Names
        String[] columnNames = {"User Name", "Realized Profit", "Unrealized Profit"};

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }
}
