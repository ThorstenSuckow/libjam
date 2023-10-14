package libjam.demo.bounce;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class BounceController implements ComponentListener {

    private BounceDemo dialog;

    public BounceController(BounceDemo dialog) {

        this.dialog = dialog;
        dialog.addComponentListener(this);
    }


    @Override
    public void componentShown(ComponentEvent e) {
        BounceCanvas bounceCanvas = new BounceCanvas();
        dialog.getCanvasContainer().add(bounceCanvas);

        bounceCanvas.setBounds(0, 0, dialog.getCanvasContainer().getWidth(),  dialog.getCanvasContainer().getHeight());

        (new Thread(bounceCanvas)).start();
    }

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

}
