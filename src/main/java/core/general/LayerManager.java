package core.general;

import core.renderer.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code LayerManager} works with multiple render layers that contain the Renderers
 */
public class LayerManager {

    /**
     * A {@code List} of the {@code Lists} which are the layers
     */
    private final List<List<Drawable>> layers;

    /**
     * The buffer of newly added Renderers
     */
    private final List<Drawable> buffer = new ArrayList<>();

    /**
     * Create a new {@code LayerManager}
     */
    public LayerManager() {
        layers = new ArrayList<>();
    }

    /**
     * Draw all {@code Renderers}
     * @param g2d
     */
    public void drawAll(Graphics2D g2d){
        layers.forEach(e -> e.forEach(f -> f.draw(g2d)));
    }

    /**
     * Add a new {@code Renderer} to the buffer
     * @param d
     */
    public void addToRendererBuffer(Drawable d){
        buffer.add(d);
    }

    /**
     * Add a new {@code Renderer} to the render layers. Should only be called between renderings.
     * Creates new render layers if the requested layer doesn't exist
     * @param d The Renderer
     */
    private void addRenderer(Drawable d) {

        int layer = d.getLayer();

        if (layer < 0) {
            throw new IllegalArgumentException("Layer must be at least 0");
        }

        //layer exists check
        int layerDif = layer - (layers.size() - 1);
        if (layerDif > 0) {
            for (int i = 0; i < layerDif; i++) {
                layers.add(new ArrayList<>());
            }
        }

        layers.get(layer).add(d);
    }

    /**
     * Remove a {@code Renderer} from the render layers.
     * <p>If the object should only temporarily be invisible, change its {@code isVisible} field</p>
     * @param d The {@code Renderer}
     * @return {@code true} if the element exists in its layer
     */
    public boolean removeRenderer(Drawable d) {
        return layers.get(d.getLayer()).remove(d);
    }

    /**
     * Flushes the buffer to the render layers
     */
    public void flush(){
        buffer.forEach(this::addRenderer);
        buffer.clear();
    }
}
