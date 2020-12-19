package core.general;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    @Test
    void simpleTest(){
        Input.addPressedKey(10);
        assertTrue(Input.keyPressed(10));
    }

}