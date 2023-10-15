package libjam.demo.bounce;

import libjam.util.Logger;
import libjam.util.event.LogEvent;
import libjam.util.event.LogListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class BounceController implements ComponentListener, LogListener {

    private BounceDemo dialog;

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

    void install() {

        installLogger();

        //install BounceCanvas
        BounceCanvas bounceCanvas = new BounceCanvas(
                dialog.getCanvasContainer().getWidth(),
                dialog.getCanvasContainer().getHeight()
        );
        dialog.getCanvasContainer().add(bounceCanvas);

        bounceCanvas.setBounds(0, 0, dialog.getCanvasContainer().getWidth(),  dialog.getCanvasContainer().getHeight());

        bounceCanvas.render();

        Logger.log("BounceCanvas started...");


    }


    @Override
    public void componentShown(ComponentEvent e) {
        install();
    }

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void onLogEvent(LogEvent logEvt) {
        if (logList != null) {
            ((DefaultListModel<String>) logList.getModel()).addElement(logEvt.getText());
        }
    }
}
