package objects.ships;

import core.math.ExMath;
import core.Master;
import core.math.Vector2D;
import objects.GameObject;

import java.awt.*;

/**
 * A Turret that can shoot projectiles
 */
public class Turret extends GameObject {

    private static final double ROTATION_SPEED = 0.05;
    private static final int SHOT_EFFECT_TIME = 300;
    private static final int SHELL_SPEED = 10;

    BattleShip battleShip;

    private int barrelAmount = 3;

    private double rotation;

    private final Color mainColor;

    private long lastShot = 0;

    //private double rotation;

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
        h = w / 16 * 9;
        g2d.setPaint(mainColor);
        Vector2D abs = battleShip.getWorldCoordsFromLocal(position);
        int sizeAbs = (int) battleShip.getWorldCoordsFromLocalSize(size).x;
        int xCenterAbs = (int) (abs.x + sizeAbs / 2);
        int yCenterAbs = (int) (abs.y + sizeAbs / 2);

        g2d.fillOval((int) abs.x, (int) abs.y, sizeAbs, sizeAbs);

        g2d.setStroke(new BasicStroke((int) battleShip.getWorldCoordsFromLocalSize(new Vector2D(10, 0)).x, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));

        //BARRELS---------------------------------------

        g2d.setPaint(Color.BLACK);
        int barrelSpacing = sizeAbs / (barrelAmount + 1);
        g2d.rotate(rotation, xCenterAbs, yCenterAbs);

        for (int i = 0; i < barrelAmount; i++) {
            int barrelX = (int) (abs.x + (i + 1) * barrelSpacing);
            int frontPosY = (int) (abs.y - sizeAbs / 2);
            g2d.drawLine(barrelX, yCenterAbs, barrelX, frontPosY);

            if (lastShot + SHOT_EFFECT_TIME > System.currentTimeMillis()) {
                g2d.setPaint(Color.YELLOW);
                g2d.fillOval(barrelX - 5, frontPosY - 5, 10, 10);
                g2d.setPaint(Color.BLACK);
            }
        }
        g2d.rotate(-rotation, xCenterAbs, yCenterAbs);

        g2d.setStroke(new BasicStroke());
    }

    @Override
    public void update() {

        Vector2D abs = battleShip.getWorldCoordsFromLocal(position);
        int sizeAbs = (int) battleShip.getWorldCoordsFromLocalSize(size).x;
        int xCenterAbs = (int) (abs.x + sizeAbs / 2);
        int yCenterAbs = (int) (abs.y + sizeAbs / 2);

        Point msLoc = master.getMouseLocation();
        double targetRotation = -Math.atan2(xCenterAbs - msLoc.x, yCenterAbs - msLoc.y);


        rotation = ExMath.angleLerp(rotation, targetRotation, ROTATION_SPEED);

        int barrelSpacing = sizeAbs / (barrelAmount + 1);

        for (int i = 0; i < barrelAmount; i++) {
            int barrelX = (int) (abs.x + (i + 1) * barrelSpacing);
            int frontPosY = (int) (abs.y - sizeAbs / 2);

            if (master.isMousePressed()) {
                lastShot = System.currentTimeMillis();

                Vector2D shellVel = Vector2D.getUnitVector(rotation).negative().multiply(SHELL_SPEED);
                Vector2D pos = Vector2D.rotateAround(new Vector2D(xCenterAbs, yCenterAbs), new Vector2D(barrelX, frontPosY), rotation, Vector2D.COUNTERCLOCKWISE);

                master.create(new Shell(pos, new Vector2D(10, 10), shellVel));
            }
        }
    }
}
