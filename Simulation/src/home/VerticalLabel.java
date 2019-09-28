package home;
import java.awt.*;
import javax.swing.*;
public class VerticalLabel extends JComponent{
  private String text;

  public VerticalLabel(String s){
    text = s;
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;

    g2d.rotate(Math.toRadians(270.0)); 
    g2d.drawString(text, 100, 100);
  }
}