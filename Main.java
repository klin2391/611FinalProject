import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double> history = new ArrayList<Double>();
        history.add(5.0);
        history.add(2.0);
        history.add(11.0);
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

        Market m = new Market(stocks, null);
        // ArrayList <Stock> a = new ArrayList <Stock>();
        // a.add(s);
        // a.add(s1);
        // a.add(s2);
        // Window_Stock w = new Window_Stock(a);
        User u = new User("Kevin", "Lin", "KEV", "password", 10, m.getStocks());
        m.addUser(u);
        // u.addStock(s);
        // u.addStock(s1);
        // u.addStock(s2);
        // u.addStock(s3);

        Window_User wu = new Window_User(u);

        
        // Grapher gs = new Grapher();
        // gs.createAndShowGui();
        
    }
}
