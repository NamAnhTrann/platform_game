package main;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.Color;
import java.awt.Graphics;

//make stuff appear on GameWindow
public class GamePanel extends JPanel {
    private float xDelta = 100;
    private float yDelta = 100;

    // private float xDir = 1f;
    // private float yDir =1f;

    private MouseInputs mouseInputs;

    // private Random random;

    private Color color = new Color(150,20,90);

    public GamePanel(){
        // random = new Random();
        mouseInputs = new MouseInputs(this); //this here basically means when something happens, call methods on this GamePanel instance
        setFocusable(true);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    //
    public void changeXDelta (int value) {
        this.xDelta += value;
    }
    public void changeYDelta (int value) {
        this.yDelta += value;
    }

    public void setRecPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }

    // this is a method in JPanel, it is call when we press a play button.
    //so we call the Graphics object that allow us to draw
    public void paintComponent(Graphics g){
        //calling the super class in Jpanel
        super.paintComponent(g);
        // updateRectangle();
        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 200, 50);
    }

    // private void updateRectangle(){
   
    //     if(xDelta > 400 || xDelta < 0){
    //         xDir *= -1;
    //         color = getRandomColor();
    //     }
   
    //     if(yDelta > 400 || yDelta <0){
    //         yDir *= -1;
    //         color = getRandomColor();
    //     }

    // }

    // private Color getRandomColor() {
    //     int r = random.nextInt(255);
    //     int g = random.nextInt(50);
    //     int b = random.nextInt(100);

    //     return new Color(r,g,b);
    // }
}
