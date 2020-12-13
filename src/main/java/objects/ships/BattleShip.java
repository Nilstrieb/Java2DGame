package objects.ships;

import objects.core.GameObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * A Battleship that can have several turrets
 */
public class BattleShip extends GameObject {

    private final ArrayList<Turret> turrets;

    public BattleShip(Color mainColor) {
        this(20, 20, 10, 40, mainColor);
        //TODO turret size should use w and h but correct just like with world coords
        turrets.add(new Turret(this, 25, 25, 50, 3));
        //turrets.add(new Turret(this, 25, 10, 50, 2));
        //turrets.add(new Turret(this, 25, 70, 50, 2));
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