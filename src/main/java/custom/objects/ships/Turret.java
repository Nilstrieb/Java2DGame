package custom.objects.ships;

import core.general.Input;
import core.math.Coordinates;
import core.math.ExMath;
import core.math.Vector2D;
import core.objects.core.GameObject;
import core.rendering.renderer.CustomRenderer;

import java.awt.*;

/**
 * A Turret that can shoot projectiles
 */
public class Turret extends GameObject {

    private static final double ROTATION_SPEED = 0.05;
    private static final int SHOT_EFFECT_TIME = 300;
    private static final int SHELL_SPEED = 1;
    public static final double SHELL_SIZE = 2;
    private static final double BARREL_THICKNESS = 1.7;
    private final int barrelAmount;

    private final Color mainColor;

    private long lastShot = 0;

    public Turret(BattleShip battleShip, Vector2D position, double size, int barrelAmount) {
        super(position, new Vector2D(size, size));
        this.parent = battleShip;
        this.barrelAmount = barrelAmount;
        mainColor = Color.GRAY;

        setRenderer(new CustomRenderer(mainColor, this) {
            @Override
            public void draw(Graphics2D g2d) {
                g2d.setPaint(mainColor);
                Vector2D abs = getWorldCoordsFromLocal(position);
                int sizeAbs = (int) Coordinates.getWorldCoordinates(object.getSize()).x;
                int xCenterAbs = (int) (abs.x + sizeAbs / 2);
                int yCenterAbs = (int) (abs.y + sizeAbs / 2);

                g2d.rotate(rotation, xCenterAbs, yCenterAbs);

                g2d.fillOval((int) abs.x, (int) abs.y, sizeAbs, sizeAbs);


                //BARRELS---------------------------------------
                g2d.setStroke(new BasicStroke((int) Coordinates.getWorldCoordinates(new Vector2D(object.getSize().x / barrelAmount / BARREL_THICKNESS, 0)).x, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL));

                g2d.setPaint(Color.BLACK);
                int barrelSpacing = sizeAbs / (barrelAmount + 1);

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
            public int getLayer() {
                return object.getLayer();
            }
        });
    }

    @Override
    public void update() {

        //TODO fix everything haha

        Point msLoc = master.getMouseLocation();
        Vector2D mouseRel = Coordinates.getMapCoordinatesFromWorld(Vector2D.fromPoint(msLoc)); //100 correct
        Vector2D centerMap = getMapCoords(getCenterPosition());
        double targetRotation = -Math.atan2(centerMap.x - mouseRel.x, centerMap.y - mouseRel.y);

        rotation = ExMath.angleLerp(rotation, targetRotation, ROTATION_SPEED);

        int barrelSpacing = (int) (size.x / (barrelAmount + 1));

        for (int i = 0; i < barrelAmount; i++) {
            int barrelX = (int) (position.x + (i + 1) * barrelSpacing);
            int frontPosY = (int) (position.y - size.x / 2);

            Vector2D spawnPosNR = getMapCoords(new Vector2D(barrelX, frontPosY));

            if (Input.isMousePressed()) {
                lastShot = System.currentTimeMillis();

                Vector2D shellVel = Vector2D.getUnitVector(rotation).negative().multiply(SHELL_SPEED);
                Vector2D pos = Vector2D.rotateAround(
                        centerMap,
                        spawnPosNR,
                        rotation);

                master.create(new Shell(pos, new Vector2D(SHELL_SIZE, SHELL_SIZE), shellVel));
            }
        }
    }
}
