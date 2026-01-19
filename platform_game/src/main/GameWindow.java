package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    public GameWindow(GamePanel gamePanel){
        setSize(400,400); //set window  size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminate window on close
        add(gamePanel); //add gamePanel to gameWindow
        setLocationRelativeTo(null); //make start in the center
        setVisible(true); //make window appear
    }
}
