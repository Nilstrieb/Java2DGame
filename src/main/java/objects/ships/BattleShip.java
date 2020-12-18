package objects.ships;

import core.objects.core.GameObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * A Battleship that can have several turrets
 */
public class BattleShip extends GameObject {

    private final ArrayList<Turret> turrets;

    public BattleShip(Color mainColor) {
        this(20, 20, 10, 40, mainColor);
        turrets.add(new Turret(this, 2.5, 7, 5, 3));
        turrets.add(new Turret(this, 2.5, 15, 5, 3));
        turrets.add(new Turret(this, 2.5, 25, 5, 3));
    }

    public BattleShip(double x, double y, double xSize, double ySize, Color mainColor) {
        super(x, y, xSize, ySize);
        turrets = new ArrayList<>();
        this.mainColor = mainColor;
    }

    @Override
    public void draw(Graphics2D g2d) {

        drawRoundRect(g2d, master.getW()/10, master.getW()/10);
        turrets.forEach((turret -> turret.draw(g2d)));
    }

    @Override
    public void update() {
        turrets.forEach(Turret::update);
    }

    public void addTurret(Turret turret){
        turrets.add(turret);
    }
}