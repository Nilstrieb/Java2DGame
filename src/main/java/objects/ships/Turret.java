package objects.ships;

import core.Master;
import core.Vector2D;
import objects.GameObject;

import java.awt.*;

/**
 * A Turret that can shoot projectiles
 */
public class Turret extends GameObject {

    BattleShip battleShip;

    private int barrelAmount = 3;

    private Color mainColor;

    private long lastShot = 0;
    private static int SHOT_EFFECT_TIME = 300;
    private static int SHELL_SPEED = 10;
    private double rotation;

    public Turret(BattleShip battleShip, double x, double y, double size, int barrelAmount) {
        super(x, y, size, size);
        this.battleShip = battleShip;
        this.barrelAmount = barrelAmount;
        mainColor = Color.GRAY;
    }

    public Turret(BattleShip battleShip) {
        super(25, 50, 50, 50);
        this.battleShip = battleShip;
        mainColor = Color.GRAY;
    }

    @Override
    public void draw(Graphics2D g2d, int w, Master master) {
        //TODO draw should be draw only for better everything
        h = w / 16 * 9;
        g2d.setPaint(mainColor);
        int xAbs = battleShip.getWorldCoordsFromLocal(position.x, true);
        int yAbs = battleShip.getWorldCoordsFromLocal(position.y, false);
        int sizeAbs = battleShip.getWorldCoordsFromLocalSize(size.x, true);
        int xCenterAbs = xAbs + sizeAbs / 2;
        int yCenterAbs = yAbs + sizeAbs / 2;

        g2d.fillOval(xAbs, yAbs, sizeAbs, sizeAbs);

        g2d.setStroke(new BasicStroke(battleShip.getWorldCoordsFromLocalSize(10, true), BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));

        //BARRELS---------------------------------------

        g2d.setPaint(Color.BLACK);
        int barrelSpacing = sizeAbs / (barrelAmount + 1);
        g2d.rotate(rotation, xCenterAbs, yCenterAbs);
        for (int i = 0; i < barrelAmount; i++) {
            int barrelX = xAbs + (i + 1) * barrelSpacing;
            int frontPosY = yAbs - sizeAbs / 2;
            g2d.drawLine(barrelX, yCenterAbs, barrelX, frontPosY);

            if (lastShot + SHOT_EFFECT_TIME > System.currentTimeMillis()) {
                g2d.setPaint(Color.YELLOW);
                g2d.fillOval(barrelX - 5, frontPosY - 5, 10, 10);
                g2d.setPaint(Color.BLACK);
            }
        }
        g2d.rotate(-rotation, xCenterAbs, yCenterAbs);
    }

    @Override
    public void update(Master master) {

        int xAbs = battleShip.getWorldCoordsFromLocal(position.x, true);
        int yAbs = battleShip.getWorldCoordsFromLocal(position.y, false);
        int sizeAbs = battleShip.getWorldCoordsFromLocalSize(size.x, true);
        int xCenterAbs = xAbs + sizeAbs / 2;
        int yCenterAbs = yAbs + sizeAbs / 2;

        Point msLoc = master.getMouseLocation();
        rotation = -Math.atan2(xCenterAbs - msLoc.x, yCenterAbs - msLoc.y);

        int barrelSpacing = sizeAbs / (barrelAmount + 1);

        for (int i = 0; i < barrelAmount; i++) {
            int barrelX = xAbs + (i + 1) * barrelSpacing;
            int frontPosY = yAbs - sizeAbs / 2;

            if (master.isMousePressed()) {
                lastShot = System.currentTimeMillis();

                Vector2D shellVel = new Vector2D(xCenterAbs - msLoc.x, yCenterAbs - msLoc.y).normalized().negative().multiply(SHELL_SPEED);
                Vector2D pos = Vector2D.rotateAround(new Vector2D(xCenterAbs, yCenterAbs), new Vector2D(barrelX, frontPosY), rotation, Vector2D.CLOCKWISE);

                master.debugPos(pos);

                master.create(new Shell(pos, new Vector2D(10, 10), shellVel));
            }
        }
    }
}
