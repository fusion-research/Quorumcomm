package home;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class FrameTest {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Demo");
        Container cp = jf.getContentPane();
        cp.add(new JComponent() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(2));
                g2.draw(new Rectangle2D.Float(30, 20, 80, 90));
            }
        });
        jf.setSize(300, 200);
        jf.setVisible(true);
    }
}