package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

//abstract class means you cannot create an object of this class
public abstract class Entity {
    // protected means only this class that extend this entity use x and y
    protected float x, y;
    protected int height, width;
    protected Rectangle2D.Float hitbox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
     
    }

    protected void drawHitbox(Graphics g) {
        //for debugging hitbox
        g.setColor(Color.PINK);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }

    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float( x,  y, width, height);
    }

    // protected void updatedHitbox() {
    //     hitbox.x = (int)x;
    //     hitbox.y = (int)y;
    // }

    public Rectangle2D.Float RectangleGetHitbox(){
        return hitbox;
    }

}
