package main;

import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    public GameWindow(GamePanel gamePanel){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminate window on close
        add(gamePanel); //add gamePanel to gameWindow
        setResizable(false);
        pack(); //tell Jframe look at that component
        setLocationRelativeTo(null);
        setVisible(true); //make window appear
        
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                System.out.println("Window gained focus");
            }

            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {
                gamePanel.getGame().windowFocusLost();

            }
        });
    }
}
