import javax.swing.*;
public class Window {
    private JFrame f;
    private JPanel p;

    public Window(){
        this("Window", 500, 500);
    }
    public Window(String title, int width, int height){
        f = new JFrame(title);
        p = new JPanel();
        f.add(p);
        f.setSize(width, height);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void update(JPanel p){
        this.p.setVisible(false);
        f.remove(this.p);
        this.p = p;
        f.add(p);
    }

    public JFrame getFrame(){
        return f;
    }

    public void dispose(){
        f.dispose();
    }

    public void setTitle(String title){
        f.setTitle(title);
    }
}
