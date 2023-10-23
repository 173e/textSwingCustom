package component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class TextField extends JTextField{

    public TextField() {
        setBorder(new EmptyBorder(20,3,10,3));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 =(Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        int width= getWidth();
        int height= getHeight();
        g2.fillRect(2, height-1,width-4,1);
        repaint();
        
    }
    
    
    
}
