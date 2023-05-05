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
        Window_Root wr = new Window_Root();


    }
}
