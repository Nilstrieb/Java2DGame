package custom.objects;

import core.general.Input;
import core.math.Vector2D;
import core.objects.base.Camera;

import java.awt.event.KeyEvent;

public class MoveCamera extends Camera {
    public MoveCamera(Vector2D position, Vector2D size) {
        super(position, size);
    }

    @Override
    protected void update() {
        if(Input.keyPressed(KeyEvent.VK_RIGHT)){
            position.x += 1;
        } else if(Input.keyPressed(KeyEvent.VK_LEFT)){
            position.x -= 1;
        }
        if(Input.keyPressed(KeyEvent.VK_UP)){
            position.y -= 1;
        } else if(Input.keyPressed(KeyEvent.VK_DOWN)){
            position.y += 1;
        }

        if(Input.keyPressed(KeyEvent.VK_PAGE_UP)){
            size.multiply(0.9);
        }
        if(Input.keyPressed(KeyEvent.VK_PAGE_DOWN)){
            size.multiply(1.1);
        }
    }
}
