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
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage img;
    private MouseInputs mouseInputs;

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;

    //move this later
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;

    public GamePanel(){
        mouseInputs = new MouseInputs(this); //this here basically means when something happens, call methods on this GamePanel instance
        importImg();
        loadAnimation();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
    }

    private void loadAnimation() {
        animations = new BufferedImage[9][6];

        for(int j = 0; j < animations.length; j++) {
            for(int i = 0; i < animations[j].length; i++){
                animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
            }
        }
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
        }finally{
            try {
                is.close();
                
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //load animation



    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    public void setDirection(int direction){
        this.playerDir = direction;
        moving =true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

        private void setAnimation() {
        if (moving){
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

        private void updatePos() {
    if (moving) {
        switch (playerDir) {
            case LEFT:
                xDelta -= 5;
                break;
            case RIGHT:
                xDelta += 5;
                break;
            case UP:
                yDelta -= 5;
                break;
            case DOWN:
                yDelta += 5;
                break;
        }
    }
}


    



    // this is a method in JPanel, it is call when we press a play button.
    //so we call the Graphics object that allow us to draw
    public void paintComponent(Graphics g){
        //calling the super class in Jpanel
        super.paintComponent(g);
        updateAnimationTick();

        setAnimation();
        updatePos();
        
        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 250,160, null);
    }





    private void updateAnimationTick() {
        aniTick ++;
        if(aniTick >= aniSpeed){
            aniTick =0;
            aniIndex ++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
    }
}
