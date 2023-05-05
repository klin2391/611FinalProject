import javax.swing.*;
public class Window {
    private JFrame f;

    public Window(String title, int width, int height){
        f = new JFrame(title);
        f.setSize(width, height);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void update(JPanel p){
        f.add(p);
    }

    public JFrame getFrame(){
        return f;
    }

    public void dispose(){
        f.dispose();
    }
}
