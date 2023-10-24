package component;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class TextField extends JTextField {

    private boolean mouseOve = false;
    private Animator animator;
    private boolean animatorHinText = true;
    private float location;
    private boolean show;
    private String labelText = "label";
    private Color lineColor = new Color(3, 155, 216);

    public TextField() {
        setBorder(new EmptyBorder(20, 3, 10, 3));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOve = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOve = false;
            }

        });

        addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                showing(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

                showing(true);
            }

        });

        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                super.begin(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void timingEvent(float fraction) {
                location = fraction;
                //System.out.println("posicion es: " + location);
                repaint();

            }

        };

        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);

    }

    private void showing(boolean action) {
        if (animator.isRunning()) {

            animator.stop();

        } else {
            location = 1;
        }
        animator.setStartFraction(1f - location);
        show = action;
        location = 1f - location;
        animator.start();
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
            g2.setColor(lineColor);
        } else {
            g2.setColor(new Color(150, 150, 150));
        }
        g2.fillRect(2, height - 1, width - 4, 1);
        createHinText(g2);
        repaint();

    }

    public void createHinText(Graphics2D g2) {

        Insets in = getInsets();
        g2.setColor(new Color(150, 150, 150));
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(labelText, g2);
        double heigth = getHeight() - in.top - in.bottom;
        double textY = (heigth - r2.getHeight()) / 2;
        double size;

        if (animatorHinText) {
            if (show) {
                size = 18 * (1 - location);
            } else {
                size = 18 - location;
            }
        } else {
            size = 18;
        }

        g2.drawString(labelText, in.right, (int) (in.top + textY + ft.getAscent() - size));
    }

}
