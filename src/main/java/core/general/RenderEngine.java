package core.general;

import core.renderer.Drawable;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code RenderEngine} handles the rendering of all {@code Renderers}
 * c<p>The {@code RenderEngine} extends JPanel and can be integrated into a JFrame. It gets the drawcall from resizing/moving the JPanel
 * or from the {@code Master}</p>
 */
public class RenderEngine extends JPanel {

    /**
     * The current width and height of the game area
     */
    private int w, h;

    /**
     * The {@code LayerManager} for the render layers
     */
    private final LayerManager layerManager;

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

        Graphics2D g2d = (Graphics2D) g.create();

        layerManager.drawAll(g2d);
    }


    /**
     * The paintComponent method is called from Swing.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Add a {@code Renderer} to the scene. Gets added to the buffer
     * @param d The {@code Renderer}
     */
    public void addRenderer(Drawable d){
        layerManager.addToRendererBuffer(d);
    }

    /**
     * Remove a {@code Renderer} from the engine because it won't be needed anymore.
     * <p>If the object should only temporarily be invisible, change its {@code isVisible} field</p>
     * @param d
     * @return
     */
    public boolean removeRenderer(Drawable d){
        return layerManager.removeRenderer(d);
    }

    /**
     * Get the true width of the current screen
     * @return The width in Java2D coordinates
     */
    public int getW() {
        return w;
    }

    /**
     * Get the true height of the current screen
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
}
