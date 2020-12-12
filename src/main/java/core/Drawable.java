package core;

import java.awt.*;

public interface Drawable {
    /**
     * <p>The draw method is called every frame after the update by the master object on each object. Everything
     * about drawing should be handled here in this method.</p>
     * <p>No general calculations should be made in this method. The game should be able to work just
     * fine even without this method being called.</p>
     * <p>This function is <i>NOT</i> intended to be called manually.</p>
     *
     * @param g2d    The {@code Graphics2D} object given by the master
     * @param w      The width of the screen
     * @param master The master object itself
     */
    void draw(Graphics2D g2d, int w, Master master);
}
