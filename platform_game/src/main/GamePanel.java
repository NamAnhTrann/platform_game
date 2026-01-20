package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.io.InputStream;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Direction.*;

//make stuff appear on GameWindow
public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;



    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this); // this here basically means when something happens, call methods on this
                                             // GamePanel instance
        this.game = game;
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
    }


    // load animation
    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }



    //this will control what gets updated (look at the methods inside)
    public void updateGame() {
 
    }

    // this is a method in JPanel, it is call when we press a play button.
    // so we call the Graphics object that allow us to draw
    public void paintComponent(Graphics g) {
        // calling the super class in Jpanel
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){
        return game;
    }
    
}