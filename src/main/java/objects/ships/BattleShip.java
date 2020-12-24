package objects.ships;

import core.general.Input;
import core.math.Vector2D;
import core.objects.core.CollGameObject;
import core.objects.core.GameObject;
import core.physics.hitboxes.RectHitBox;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * A Battleship that can have several turrets
 */
public class BattleShip extends GameObject {

    public static final double SPPED = 1;
    private static final double TURN_RATE = 0.05;

    private final ArrayList<Turret> turrets;

    private boolean playerControlled = false;

    public BattleShip(Color mainColor) {
        this(20, 20, 10, 40, mainColor);
        turrets.add(new Turret(this, 2.5, 7, 5, 3));
        turrets.add(new Turret(this, 2.5, 15, 5, 3));
        turrets.add(new Turret(this, 2.5, 25, 5, 3));
        this.playerControlled = true;
    }

    public BattleShip(double x, double y, double xSize, double ySize, Color mainColor) {
        super(x, y, xSize, ySize);
        turrets = new ArrayList<>();
        this.mainColor = mainColor;
        this.doesDespawn = false;
    }

    @Override
    public void draw(Graphics2D g2d) {

        drawRoundRect(g2d, master.getW() / 10, master.getW() / 10);
        turrets.forEach((turret -> turret.draw(g2d)));
    }

    @Override
    public void update() {

        if (playerControlled) {

            moveTo(Vector2D.add(position, getV2DRotation().multiply(Input.getVerticalAxis() * SPPED)));

            rotation += Input.getHorizontalAxis() * TURN_RATE;
        }

        turrets.forEach(Turret::update);
    }

    public void addTurret(Turret turret) {
        turrets.add(turret);
    }

    public boolean isPlayerControlled() {
        return playerControlled;
    }
}