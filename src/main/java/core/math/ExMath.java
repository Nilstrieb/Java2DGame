package core.math;

/**
 * Extended Math functions for the game engine
 */
public class ExMath {

    private ExMath(){
    }

    /**
     * Lerp an angle in radians
     *
     * @param from The start angle
     * @param to The target angle
     * @param t The value
     * @return The new angle
     *
     * @see <a href="https://gist.github.com/shaunlebron/8832585"angleLerp</a>
     */
    public static double angleLerp(double from, double to, double t) {
        double max = Math.PI * 2;
        double da = (to - from) % max;
        double shortAngleDist = 2 * da % max - da;

        return from + shortAngleDist * t;
    }
}
