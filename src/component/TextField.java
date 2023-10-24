package component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TextField extends JTextField {

    private boolean mouseOve = false;

    public TextField() {
        setBorder(new EmptyBorder(20, 3, 10, 3));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOve = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOve= false;
            }

        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        int width = getWidth();
        int height = getHeight();
        if (mouseOve) {
            g2.setColor(Color.yellow);
        }else{
            g2.setColor(new Color(150,150,150));
        }
        g2.fillRect(2, height - 1, width - 4, 1);
        repaint();

    }

}
