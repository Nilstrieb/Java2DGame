package core.physics;

public class Collision {

    Collidable a;
    Collidable b;

    boolean haveCollided;

    public Collision(Collidable a, Collidable b, boolean haveCollided) {
        this.a = a;
        this.b = b;
        this.haveCollided = haveCollided;
    }

    public Collidable getA() {
        return a;
    }

    public Collidable getB() {
        return b;
    }

    public boolean isHaveCollided() {
        return haveCollided;
    }
}
