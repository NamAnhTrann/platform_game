package entities;

//abstract class means you cannot create an object of this class
public abstract class Entity {
    //protected means only this class that extend this entity use x and y
    protected float x,y;
    public Entity(float x, float y){
        this.x = x;
        this.y = y;
    }
}
