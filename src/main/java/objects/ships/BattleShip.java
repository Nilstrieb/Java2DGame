package objects.ships;

import core.Master;
import objects.GameObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * A Battleship that can have several turrets
 */
public class BattleShip extends GameObject {

    private Color mainColor;
    private ArrayList<Turret> turrets;

    public BattleShip(Color mainColor) {
        super(20, 20, 5, 40);
        turrets = new ArrayList<>();
        turrets.add(new Turret(this, 25, 25, 50, 3));
        turrets.add(new Turret(this, 25, 10, 50, 2));
        turrets.add(new Turret(this, 25, 70, 50, 2));
        this.mainColor = mainColor;
    }

    public BattleShip(double x, double y, double xSize, double ySize, Color mainColor) {
        super(x, y, xSize, ySize);
        turrets = new ArrayList<>();
        this.mainColor = mainColor;
    }

    @Override
    public void draw(Graphics2D g2d, int w, Master master) {
        this.w = w;
        h = w/16*9;
        g2d.setPaint(mainColor);
        int xAbs = (int) getWorldCoords(position.x, true);
        int yAbs = (int) getWorldCoords(position.y, false);
        int sizeXAbs = (int) getWorldCoords(size.x, true);
        int sizeYAbs = (int) getWorldCoords(size.y, false);
        g2d.fillRoundRect(xAbs, yAbs, sizeXAbs, sizeYAbs, w/10, w/10);

        turrets.forEach((turret -> turret.draw(g2d, w, master)));
    }

    @Override
    public void update(Master master) {
        turrets.forEach((turret -> turret.update(master)));
    }

    public void addTurret(Turret turret){
        turrets.add(turret);
    }
}