package main;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

//Everything relevant to game class
public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

    private final int UPS_SET = 200;

    private Player player;
    private LevelManager levelManager;

    //Tiles stuff
    public final static int TITLES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TITLES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    // constructor - head method of the class, use to make object
    public Game() {
        initClasses();

        gamePanel = new GamePanel(this); // create game panel object
        // then put that object inside game Window
        gameWindow = new GameWindow(gamePanel);
        // request that this component get the input focus
        gamePanel.requestFocusInWindow();
        startGameLoop();

    }

    private void initClasses() {
        player = new Player(250, 160);
        levelManager = new LevelManager(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        final double timePerFrame = 1_000_000_000.0 / FPS_SET;
        final double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();
            long elapsed = currentTime - previousTime;
            previousTime = currentTime;

            deltaU += elapsed / timePerUpdate;
            deltaF += elapsed / timePerFrame;

            while (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    // this will control when update happens, and will call the updateGame() method
    // in game panel
    private void update() {
        player.update();
        levelManager.update();

    }

    public void render(Graphics g) {
        levelManager.draw(g);
        player.render(g);
       
    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }
}