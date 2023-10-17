package libjam.demo.bounce;

import libjam.util.Logger;
import libjam.util.event.LogEvent;
import libjam.util.event.LogListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class BounceController implements ComponentListener, LogListener {

    private BounceDemo dialog;

    private BounceCanvas bounceCanvas;

    private JList<String> logList;

    public BounceController(final BounceDemo dialog) {

        this.dialog = dialog;
        dialog.addComponentListener(this);
    }

    void installLogger() {
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        logList = dialog.getLogList();

        logList.setModel(listModel);
        logList.setFont(new Font("Courier New", Font.PLAIN,12));

        Logger.addLogListener(this);
    }


    void buttonActionPerformed(ActionEvent e) {

        if (e.getSource() == dialog.upButton) {
             bounceCanvas.getCamera().addY(0.1);
        }

        if (e.getSource() ==  dialog.downButton) {
                bounceCanvas.getCamera().addY(-0.1);
        }

        if (e.getSource() ==  dialog.leftButton) {
            bounceCanvas.getCamera().addX(-0.1);
        }

        if (e.getSource() ==  dialog.rightButton) {
            bounceCanvas.getCamera().addX(0.1);
        }
    }

    void install() {

        installLogger();

        dialog.upButton.addActionListener(this::buttonActionPerformed);
        dialog.downButton.addActionListener(this::buttonActionPerformed);
        dialog.leftButton.addActionListener(this::buttonActionPerformed);
        dialog.rightButton.addActionListener(this::buttonActionPerformed);


        //install BounceCanvas
        bounceCanvas = new BounceCanvas(
                dialog.getCanvasContainer().getWidth(),
                dialog.getCanvasContainer().getHeight()
        );
        dialog.getCanvasContainer().add(bounceCanvas);

        bounceCanvas.setBounds(0, 0, dialog.getCanvasContainer().getWidth(),  dialog.getCanvasContainer().getHeight());

        bounceCanvas.render();

        bounceCanvas.addComponentListener(bounceCanvas);

        Logger.log("BounceCanvas started...");


    }


    @Override
    public void componentShown(ComponentEvent e) {
        install();
    }

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {

        Dimension size = dialog.getCanvasContainer().getSize();

        //System.out.

        if (bounceCanvas != null) {

            bounceCanvas.setSize(size);

        }

    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void onLogEvent(LogEvent logEvt) {
        if (logList != null) {
            ((DefaultListModel<String>) logList.getModel()).addElement(logEvt.getText());
        }
    }
}
