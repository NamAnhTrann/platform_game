package main;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.Graphics;

//make stuff appear on GameWindow
public class GamePanel extends JPanel {
    private int xDelta = 100;
    private int yDelta = 100;

    private MouseInputs mouseInputs;

    public GamePanel(){
        mouseInputs = new MouseInputs(this); //this here basically means when something happens, call methods on this GamePanel instance
        setFocusable(true);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    //
    public void changeXDelta (int value) {
        this.xDelta += value;
        repaint(); //repaint the new changes
    }
    public void changeYDelta (int value) {
        this.yDelta += value;
        repaint();
    }

    public void setRecPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }

    // this is a method in JPanel, it is call when we press a play button.
    //so we call the Graphics object that allow us to draw
    public void paintComponent(Graphics g){
        //calling the super class in Jpanel
        super.paintComponent(g);
        g.fillRect(xDelta, yDelta, 200, 50);
    }
}
