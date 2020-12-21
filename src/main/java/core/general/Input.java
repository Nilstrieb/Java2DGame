package core.general;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Handles easily accessible input for the objects
 */
public class Input {

    public static final int AXIS_HORIZONTAL = 0;
    public static final int AXIS_VERTICAL = 1;

    public static final int KEY_HORIZONTAL_HIGH = KeyEvent.VK_D;
    public static final int KEY_HORIZONTAL_LOW = KeyEvent.VK_A;
    public static final int KEY_VERTICAL_HIGH = KeyEvent.VK_S;
    public static final int KEY_VERTICAL_LOW = KeyEvent.VK_W;

    /**
     * Array of all possible pressed keys (not best way to do this but ok way)
     */
    private static boolean[] presses = new boolean[0x0FFF];

    private static boolean mousePressed;

    private static double horizontalAxis = 0;
    private static double verticalAxis = 0;

    public static boolean keyPressed(int keyKey) {
        return presses[keyKey];
    }

    public static void addPressedKey(int keyCode) {
        if (keyCode < presses.length) {
            presses[keyCode] = true;
        }
    }

    public static void removePressKey(int keyCode) {
        if (keyCode < presses.length) {
            presses[keyCode] = false;
        }
    }

    /**
     * To be called before the frames are calculated
     */
    public static void calculate() {
        if (presses[KEY_HORIZONTAL_HIGH]) {
            horizontalAxis = 1;
        } else if (presses[KEY_HORIZONTAL_LOW]) {
            horizontalAxis = -1;
        } else {
            horizontalAxis = 0;
        }

        if (presses[KEY_VERTICAL_HIGH]) {
            verticalAxis = 1;
        } else if (presses[KEY_VERTICAL_LOW]) {
            verticalAxis = -1;
        } else {
            verticalAxis = 0;
        }
    }

    /**
     * Is called after every frame
     */
    public static void frameReset() {
        mousePressed = false;
    }

    public static double getHorizontalAxis() {
        return horizontalAxis;
    }

    public static double getVerticalAxis() {
        return verticalAxis;
    }

    public static boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Call this method when the user pressed the left mouse button
     */
    public static void mousePressed() {
        mousePressed = true;
    }
}
