package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.io.InputStream;


import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

//make stuff appear on GameWindow
public class GamePanel extends JPanel {
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage img, subImg;
    private MouseInputs mouseInputs;
    private Color color = new Color(150,20,90);

    public GamePanel(){
        mouseInputs = new MouseInputs(this); //this here basically means when something happens, call methods on this GamePanel instance
        importImg();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
    }

    //import images
private void importImg() {
    InputStream is =
        getClass().getResourceAsStream("/res/player_sprites.png");
        System.out.println(is);

    try {
        img = ImageIO.read(is);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }
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
        subImg = img.getSubimage(1*64, 8*40, 64, 40);
        g.drawImage(subImg, (int)xDelta, (int)yDelta, 128,80, null);
    }

    
}
