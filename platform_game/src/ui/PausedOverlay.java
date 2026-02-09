package ui;

import static utils.Constants.UI.PauseButtons.SOUND_SIZE;
import static utils.Constants.UI.URMButtons.URM_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;

import java.awt.event.MouseEvent;

import main.Game;
import utils.LoadSave;
import static utils.Constants.UI.VolumeButtons.*;

public class PausedOverlay {
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private UrmButton UrmButton, menuB, replayB, unpauseB;
    private Playing playing;

    private SoundButton musicButton, sfxButton;
    private VolumeButton volumeButton;

    public PausedOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        createSoundButtons();
        createUrmButtons();
        createVolumeButtons();
    }

    private void createVolumeButtons() {
        int vX = (int) (309 * Game.SCALE);
        int vy = (int) (278 * Game.SCALE);
        volumeButton = new VolumeButton(vX, vy, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void createUrmButtons() {
        int menuX = (int) (313 * Game.SCALE);
        int replaceX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int bY = (int) (325 * Game.SCALE);

        menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replaceX, bY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    public void update() {
        musicButton.update();
        sfxButton.update();
        menuB.update();
        replayB.update();
        unpauseB.update();

        volumeButton.update();
    }

    public void draw(Graphics g) {
        // background
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        // sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);

        // urm Buttons
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);
        // volume slider
        volumeButton.draw(g);

    }

    // vol slider
    public void mouseDragged(MouseEvent e) {
        if(volumeButton.isMousePressed()){
            volumeButton.changeX(e.getX());
        }
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isIn(e, musicButton)) {
            musicButton.setMouseOver(true);
        } else if (isIn(e, sfxButton)) {
            sfxButton.setMouseOver(true);
        } else if (isIn(e, menuB)) {
            menuB.setMouseOver(true);
        } else if (isIn(e, replayB)) {
            replayB.setMouseOver(true);
        } else if (isIn(e, unpauseB)) {
            unpauseB.setMouseOver(true);
        } else if (isIn(e, volumeButton)) {
            volumeButton.setMouseOver(true);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton)) {
            musicButton.setMousePressed(true);
        }

        if (isIn(e, sfxButton)) {
            sfxButton.setMousePressed(true);
        }
        if (isIn(e, menuB)) {
            menuB.setMousePressed(true);
        }
        if (isIn(e, replayB)) {
            replayB.setMousePressed(true);
        }
        if (isIn(e, unpauseB)) {
            unpauseB.setMousePressed(true);
        }
        if (isIn(e, volumeButton)) {
            volumeButton.setMousePressed(true);
        }

    }

    public void mouseReleased(MouseEvent e) {

        if (isIn(e, musicButton) && musicButton.isMousePressed()) {
            musicButton.setMuted(!musicButton.isMuted());
        }

        if (isIn(e, sfxButton) && sfxButton.isMousePressed()) {
            sfxButton.setMuted(!sfxButton.isMuted());
        }

        if (isIn(e, menuB) && menuB.isMousePressed()) {
            Gamestate.state = Gamestate.MENU;
            playing.unpauseGame();
        }

        if (isIn(e, replayB) && replayB.isMousePressed()) {
            System.out.println("Replay lvl for now");
        }

        if (isIn(e, unpauseB) && unpauseB.isMousePressed()) {
            playing.unpauseGame();
        }

        musicButton.resetBools();
        sfxButton.resetBools();
        menuB.resetBools();
        unpauseB.resetBools();
        replayB.resetBools();
        volumeButton.resetBools();
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());

    }
}
