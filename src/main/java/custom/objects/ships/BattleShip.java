package custom.objects.ships;

import core.general.Input;
import core.math.Vector2D;
import core.objects.core.GameObject;
import core.rendering.renderer.RoundRectRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * A Battleship that can have several turrets
 */
public class BattleShip extends GameObject {

    public static final double SPPED = 1;
    private static final double TURN_RATE = 0.05;

    private final ArrayList<Turret> turrets;

    private boolean playerControlled = false;

    public BattleShip() {
        this(new Vector2D(20, 20), new Vector2D(10, 40), Color.DARK_GRAY);
        this.playerControlled = true;
    }

    public BattleShip(Vector2D position, Vector2D size, Color mainColor) {
        super(position, size);
        turrets = new ArrayList<>();
        this.mainColor = mainColor;
        this.doesDespawn = false;
        setRenderer(new RoundRectRenderer(mainColor, this, size, 10, 10));
        //setRenderer(new RectRenderer(mainColor, this, size));
    }

    @Override
    public void update() {

        if (playerControlled) {

            moveTo(Vector2D.add(position, getV2DRotation().multiply(Input.getVerticalAxis() * SPPED)));

            rotation += Input.getHorizontalAxis() * TURN_RATE;
        }
    }

    public void addTurret(Turret turret) {
        turrets.add(turret);
    }

    public boolean isPlayerControlled() {
        return playerControlled;
    }
}