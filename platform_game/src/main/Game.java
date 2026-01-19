package main;

//Everything relevant to game class
public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

    //constructor - head method of the class, use to make object
    public Game(){
        gamePanel = new GamePanel(); //create game panel object
        //then put that object inside game Window
        gameWindow = new GameWindow(gamePanel);
        //request that this component get the input focus
        gamePanel.requestFocusInWindow();
        startGameLoop();
    }
    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        //nano-second -> duration each frame should last
        double timePerFrame = 1000000000.0 / FPS_SET; 
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        int frames =0;
        long lastCheck = System.currentTimeMillis();
        //game loop
        while (true){
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame){
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames=0;
            }
        }
    }
}
