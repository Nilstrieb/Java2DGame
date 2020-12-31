package core.rendering;

import java.awt.Graphics2D;

/**
 * This interface has to be implemented by every
 */
public interface Drawable {

    /**
     * <p>The draw method is called every frame after the update by the master object on each object. Everything
     * about drawing should be handled here in this method.</p>
     * <p>No general calculations should be made in this method. The game should be able to work just
     * fine even without this method being called.</p>
     * <p>This function is <i>NOT</i> intended to be called manually.</p>
     *
     * @param g2d    The {@code Graphics2D} object given by the master
     */
    void draw(Graphics2D g2d);

    /**
     * Returns the layer of the Drawable
     * @return The render layer
     */
    int getLayer();
}
