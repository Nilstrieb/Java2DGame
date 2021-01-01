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
            public void draw() {

                Vector2D mapPos = object.getMapPosition();

                re.fillOval(mapPos, object.getSize(), mainColor, rotation);
                Vector2D barrelSize = new Vector2D(object.getSize().x / barrelAmount / BARREL_THICKNESS, 0);

                double barrelSpacing = size / (barrelAmount + 1);
                double yCenter = mapPos.y + size / 2;
                double xCenter = mapPos.x + size / 2;


                //BARRELS---------------------------------------

                for (int i = 0; i < barrelAmount; i++) {
                    double barrelX = mapPos.x + (i + 1) * barrelSpacing;
                    double frontPosY = mapPos.y - size / 2;

                    re.drawLine(new Vector2D(barrelX, yCenter), new Vector2D(barrelX, frontPosY), barrelSize, Color.BLACK, rotation, new Vector2D(xCenter, yCenter));
                }
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

            if (Input.isMousePressed()) {

                double barrelX = position.x + (i + 1) * barrelSpacing;
                double frontPosY = position.y - size.x / 2;

                Vector2D spawnPosNR = getMapCoords(new Vector2D(barrelX, frontPosY));

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
