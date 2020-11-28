package it.unibo.oop.lab.advanced;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DrawNumberViewImplOnPanel implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";
    private DrawNumberViewObserver observer;
    private JFrame frame;
    private JPanel canvas;
    private  JTextArea log;
    
    @Override
    public void setObserver(DrawNumberViewObserver observer) {
        this.observer=observer;

    }

    @Override
    public void start() {
        frame = new JFrame();
        canvas = new JPanel();
        log = new JTextArea();
        canvas.setLayout(new BorderLayout());
        canvas.add(log, BorderLayout.CENTER);
        log.setEditable(false);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
       
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    @Override
    public void numberIncorrect() {
       this.log.setText(log.getText()+"Incorrect Number!\n");
    }

    @Override
    public void result(DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            this.log.setText(log.getText()+res.getDescription()+"\n");
            return;
        case YOU_WON:
            this.log.setText(log.getText()+res.getDescription() + NEW_GAME+"\n");
            break;
        }

    }

    @Override
    public void limitsReached() {
       this.log.setText(log.getText()+"You lost"+NEW_GAME+"\n");

    }

    @Override
    public void displayError(String message) {
        this.log.setText(log.getText()+message+"\n");

    }

}
