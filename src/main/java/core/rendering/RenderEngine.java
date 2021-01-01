package core.rendering;

import core.general.Master;
import core.math.Coordinates;
import core.math.Vector2D;
import core.objects.base.Camera;
import core.rendering.renderer.Renderer;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code RenderEngine} handles the rendering of all {@code Renderers}
 * c<p>The {@code RenderEngine} extends JPanel and can be integrated into a JFrame. It gets the drawcall from resizing/moving the JPanel
 * or from the {@code Master}</p>
 */
public class RenderEngine extends JPanel {

    private final Master master = Master.getMaster();

    private Camera currentCamera;

    private int w, h;

    private final LayerManager layerManager;

    private Graphics2D g2d;
    private ShapeDrawContainer sdc;

    /**
     * Construct a new {@code RenderEngine}
     */
    public RenderEngine() {
        layerManager = new LayerManager();
    }

    /**
     * The mein drawing method, handles everything about drawing
     *
     * @param g The {@code Graphics} object
     */
    private void doDrawing(Graphics g) {

        if (getWidth() * 9 > getHeight() * 16) {
            h = getHeight();
            w = h / 9 * 16;
        } else {
            w = getWidth();
            h = w / 16 * 9;
        }

        g2d = (Graphics2D) g.create();
        layerManager.drawAll(g2d);
        g2d = null; //making sure that nothing can be drawn between frames
    }


    /**
     * The paintComponent method is called from Swing.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Add a {@code Renderer} to the scene. Gets added to the buffer
     *
     * @param d The {@code Renderer}
     */
    public void addRenderer(Renderer d) {
        layerManager.addToRendererBuffer(d);
    }

    /**
     * Remove a {@code Renderer} from the engine because it won't be needed anymore.
     * <p>If the object should only temporarily be invisible, change its {@code isVisible} field</p>
     *
     * @param d
     */
    public void removeRenderer(Renderer d) {
        layerManager.removeRenderer(d);
    }

    /**
     * Get the true width of the current screen
     *
     * @return The width in Java2D coordinates
     */
    public int getW() {
        return w;
    }

    /**
     * Get the true height of the current screen
     *
     * @return The height in Java2D coordinates
     */
    public int getH() {
        return h;
    }

    /**
     * Flush the buffer
     */
    public void flush() {
        layerManager.flush();
    }

    public Camera getCurrentCamera() {
        return currentCamera;
    }

    public void setCurrentCamera(Camera currentCamera) {
        this.currentCamera = currentCamera;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    //--------------------------------------

    public Vector2D shiftPoint(Vector2D point) {
        Vector2D newPosShifted = Vector2D.subtract(point, currentCamera.getPosition());
        return Vector2D.divide(newPosShifted, currentCamera.getSize());
    }

    public Vector2D scaleSize(Vector2D size) {
        return Vector2D.divide(size, currentCamera.getSize());
    }


    //Drawing methods---------------------------------------------------------------------------------------------------------------

    public void fillRect(Vector2D position, Vector2D size, Color color, double rotation) {
        drawSetup(position, size, color, rotation);
        g2d.fillRect(
                (int) sdc.pos.x, (int) sdc.pos.y,
                (int) sdc.size.x, (int) sdc.size.y);
        drawTearDown();
    }

    public void fillRoundRect(Vector2D position, Vector2D size, Vector2D arcFactors, Color color, double rotation) {
        drawSetup(position, size, color, rotation);
        g2d.fillRoundRect(
                (int) sdc.pos.x, (int) sdc.pos.y,
                (int) sdc.size.x, (int) sdc.size.y,
                (int) (master.getW() / arcFactors.x), (int) (master.getH() / arcFactors.y));
        drawTearDown();
    }

    public void fillOval(Vector2D position, Vector2D size, Color color, double rotation) {
        drawSetup(position, size, color, rotation);
        g2d.fillOval(
                (int) sdc.pos.x, (int) sdc.pos.y,
                (int) sdc.size.x, (int) sdc.size.y);
        drawTearDown();
    }

    public void drawRect(Vector2D position, Vector2D size, Color color, int rotation) {
        drawSetup(position, size, color, rotation);
        g2d.drawRect(
                (int) sdc.pos.x, (int) sdc.pos.y,
                (int) sdc.size.x, (int) sdc.size.y
        );
        drawTearDown();
    }

    public void drawLine(Vector2D position1, Vector2D position2, Vector2D size, Color color, double rotation) {
        drawLine(position1, position2, size, color, rotation, null);
    }

    public void drawLine(Vector2D position1, Vector2D position2, Vector2D size, Color color, double rotation, Vector2D rotationCenter) {
        drawSetup(position1, size, color, rotation, rotationCenter);
        g2d.setStroke(new BasicStroke((int) sdc.size.x, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));

        Vector2D absPos2 = Coordinates.getWorldCoordinates(shiftPoint(position2));
        g2d.drawLine((int) sdc.pos.x, (int) sdc.pos.y, (int) absPos2.x, (int) absPos2.y);
        g2d.setStroke(new BasicStroke());
        drawTearDown();
    }

    /**
     * Called before every J2D draw method
     */
    private void drawSetup(Vector2D position, Vector2D size, Color color, double rotation, Object... args) {
        drawSetup(position, size, color, rotation, null, args);
    }

    private void drawSetup(Vector2D position, Vector2D size, Color color, double rotation, Vector2D rotationCenter, Object... args) {
        Vector2D abs = Coordinates.getWorldCoordinates(shiftPoint(position));
        Vector2D sizeAbs = Coordinates.getWorldCoordinates(scaleSize(size));
        Vector2D centerAbs = new Vector2D((abs.x + sizeAbs.x / 2), (abs.y + sizeAbs.y / 2));
        Vector2D rotCentAbs = null;
        if(rotationCenter != null){
            rotCentAbs = Coordinates.getWorldCoordinates(shiftPoint(rotationCenter));
        }

        sdc = new ShapeDrawContainer(abs, sizeAbs, centerAbs, color, rotation, rotCentAbs, args);

        g2d.setPaint(sdc.color);
        g2d.rotate(sdc.rotation, sdc.rotationCenter.x, sdc.rotationCenter.y);
    }

    /**
     * Called after every J2D draw method
     */
    private void drawTearDown() {
        g2d.rotate(-sdc.rotation, sdc.rotationCenter.x, sdc.rotationCenter.y);
        sdc = null; //to avoid any drawing errors, might be changed at some point
    }


    /**
     * Holds all information about a shape to be drawn
     */
    private static class ShapeDrawContainer {
        public Vector2D pos;
        public Vector2D size;
        public Vector2D center;
        public Color color;
        public double rotation;
        public Vector2D rotationCenter;
        public Object[] args;

        public ShapeDrawContainer(Vector2D pos, Vector2D size, Vector2D center, Color color, double rotation, Vector2D rotationCenter, Object... args) {
            this.pos = pos;
            this.size = size;
            this.center = center;
            this.color = color;
            this.rotation = rotation;
            this.rotationCenter = (rotationCenter == null) ? center : rotationCenter;
            this.args = args;
        }
    }
}
