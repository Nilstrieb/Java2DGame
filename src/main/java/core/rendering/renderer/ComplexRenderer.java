package core.rendering.renderer;

import core.objects.core.GameObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code ComplexRenderer} consists of multiple {@code Renderers}
 * <p>The {@code Renderer} at index 0 gets rendered first</p>
 */
public class ComplexRenderer extends Renderer{

    private final ArrayList<Renderer> renderers;

    public ComplexRenderer (GameObject object, Renderer ... renderers){
        super(null, object);
        this.renderers = new ArrayList<>(Arrays.asList(renderers));
    }

    @Override
    public void draw() {
        renderers.forEach(e -> e.draw());
    }
}
