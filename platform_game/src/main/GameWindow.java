package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    public GameWindow(GamePanel gamePanel){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminate window on close
        add(gamePanel); //add gamePanel to gameWindow
        setLocationRelativeTo(null); //make start in the center
        setResizable(false);
        pack(); //tell Jframe look at that component and make the window big enough to fit panel (jpanel)
        setVisible(true); //make window appear
    }
}
