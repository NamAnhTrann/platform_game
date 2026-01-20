package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import static utils.Constants.PlayerConstants.*;


public class Player extends Entity {
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;

    // move this later
    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private float playerSSpeed = 2.0f;
    private boolean moving = false, attacking=false;

    public Player(float x, float y) {
        super(x, y);
        loadAnimation();

    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 250, 160, null);

    }

    private void setAnimation() {
        int startAni = playerAction;

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
        if(attacking){
            playerAction = ATTACK_1;
        }
        if(startAni != playerAction){
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void updatePos() {

        moving = false;

        if (left && !right) {
            x -= playerSSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSSpeed;
            moving = true;
        }
    }

    private void loadAnimation() {
        InputStream is = getClass().getResourceAsStream("/res/player_sprites.png");
        System.out.println(is);

        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[9][6];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isLeft() {
        return left;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isDown() {
        return down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirBooleans() {
        left = false;
        up = false;
        right = false;
        down = false;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }



}
