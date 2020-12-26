package core.renderer;

import core.objects.core.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code ComplexRenderer} consists of multiple {@code Renderers}
 * <p>The {@code Renderer} at index 0 gets rendered first</p>
 */
public class ComplexRenderer extends Renderer{

    private ArrayList<Renderer> renderers;

    public ComplexRenderer (GameObject object, Renderer ... renderers){
        super(null, object);
        this.renderers = new ArrayList<>(Arrays.asList(renderers));
    }

    @Override
    public void draw(Graphics2D g2d) {
        renderers.forEach(e -> e.draw(g2d));
    }
}
