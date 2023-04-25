import java.util.ArrayList;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        String ui = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        String textureUI = "com.jtattoo.plaf.texture.TextureLookAndFeel";
        try {

            UIManager.setLookAndFeel(textureUI);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Double> history = new ArrayList<Double>();
        history.add(5.0);
        history.add(2.0);
        history.add(11.0);
//        System.out.println ("TEST");
        Value v = new Value(9, history);
        // System.out.println(v.getCurrent());
        Stock s = new Stock("Kevin", "KVN", v);
        Stock s1 = new Stock("Kevin", "KVN", v);
        Stock s2 = new Stock("Kevin", "KVN", v);
        Stock s3 = new Stock("Test", "TST", v);
        Stock s4 = new Stock("NEW", "NEW", v);
        ArrayList <Stock> stocks = new ArrayList <Stock>();
        stocks.add(s);
        stocks.add(s3);
        stocks.add(s4);
        // System.out.println(s.getName());
        // System.out.println(s.getCurrentPrice());
        // System.out.println(s.getPurchasePrice());
        // System.out.println(s.getHistory());
        Manager m1 = new Manager();
        Market m = new Market(stocks, null, m1);
        // ArrayList <Stock> a = new ArrayList <Stock>();
        // a.add(s);
        // a.add(s1);
        // a.add(s2);
        // Window_Stock w = new Window_Stock(a);
        User u = new User("Kevin", "Lin", "kev@mail.com" , "KEV", "password", 10, 0);
        m.addUser(u);

        // u.addStock(s);
        // u.addStock(s1);
        // u.addStock(s2);
        // u.addStock(s3);
//        SQL sql = new SQL();
//        sql.insertCustomer(1, u.getFirstName(), u.getLastName(), u.getEmail(), u.getUsername(), u.getPassword(),(int) u.getBalance());
//        sql.insertManager(1, m1.getEmail(), "", m1.getUsername(), m1.getPassword());
//        sql.queryCustomer("KEV");
//        sql.verifyCustomer("EV", "passwo");
//        Window_User wu = new Window_User(sql.getUser("KEV"));

        Window_Root wr = new Window_Root();

//        Window_Manager wm = new Window_Manager(m1);

        
        // Grapher gs = new Grapher();
        // gs.createAndShowGui();
        
    }
}
