package core.general;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Handles easily accessible input for the objects
 */
public class Input {

    private static ArrayList<Integer> pressedKeys = new ArrayList<>();

    private static boolean mousePressed;

    public static boolean keyPressed(int keyKey){
        return pressedKeys.contains(keyKey);
    }

    public static void addPressedKey(int keyKey){
        pressedKeys.add(keyKey);
    }

    public static void frameReset(){
        pressedKeys.clear();
        mousePressed = false;
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
