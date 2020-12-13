package objects.ships;

import core.Master;
import objects.GameObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * A Battleship that can have several turrets
 */
public class BattleShip extends GameObject {

    private ArrayList<Turret> turrets;

    public BattleShip(Color mainColor) {
        this(20, 20, 5, 40, mainColor);
        //TODO child x coords 100 not 100*16/9
        turrets.add(new Turret(this, 100*16/9d/4, 25, 50, 3));
        //turrets.add(new Turret(this, 25, 10, 50, 2));
        //turrets.add(new Turret(this, 25, 70, 50, 2));
    }

    public BattleShip(double x, double y, double xSize, double ySize, Color mainColor) {
        super(x, y, xSize, ySize);
        turrets = new ArrayList<>();
        this.mainColor = mainColor;
    }

    @Override
    public void draw(Graphics2D g2d, int w, Master master) {

        drawRoundRect(g2d, w, w/10, w/10);
        turrets.forEach((turret -> turret.draw(g2d, w, master)));
    }

    @Override
    public void update() {
        turrets.forEach(Turret::update);
    }

    public void addTurret(Turret turret){
        turrets.add(turret);
    }
}