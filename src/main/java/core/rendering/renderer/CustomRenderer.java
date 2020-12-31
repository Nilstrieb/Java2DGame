package core.rendering.renderer;

import core.objects.core.GameObject;

import java.awt.*;

public abstract class CustomRenderer extends Renderer{
    public CustomRenderer(Color color, GameObject object) {
        super(color, object);
    }
}
