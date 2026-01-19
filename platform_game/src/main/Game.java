package main;

//Everything relevant to game class
public class Game {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
   
    //constructor - head method of the class, use to make object
    public Game(){
        gamePanel = new GamePanel(); //create game panel object
        //then put that object inside game Window
        gameWindow = new GameWindow(gamePanel);
        //request that this component get the input focus
        gamePanel.requestFocusInWindow();

        
    }
}
