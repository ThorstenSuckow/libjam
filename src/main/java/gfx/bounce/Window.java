package gfx.bounce;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window extends JFrame implements WindowListener {


    /**
     * Creates a new JFrame.
     *
     * @param width The width for this window.
     * @param height The height for this window.
     * @param bounceCanvas The Canvas that should be rendered to the window.
     */
    public Window(final int width, final int height, final BounceCanvas bounceCanvas) {


        super("bounce");


        setLayout(new GridLayout());
        setSize(width, height);

        setLocationRelativeTo(null);

        add(bounceCanvas);

        addWindowListener(this);

        setVisible(true);
    }


    @Override
    public void windowOpened(final WindowEvent e) {

    }


    /**
     * Closes this window.
     *
     * @param e
     */
    @Override
    public void windowClosing(final WindowEvent e) {
        System.out.println("closing");
        dispose();
    }

    @Override
    public void windowClosed(final WindowEvent e) {

    }

    @Override
    public void windowIconified(final WindowEvent e) {

    }

    @Override
    public void windowDeiconified(final WindowEvent e) {

    }

    @Override
    public void windowActivated(final WindowEvent e) {

    }

    @Override
    public void windowDeactivated(final WindowEvent e) {

    }
}
