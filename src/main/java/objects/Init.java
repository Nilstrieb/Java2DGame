package objects;

import core.general.Master;
import core.math.Vector2D;
import core.objects.core.GameObject;
import objects.ships.BattleShip;
import objects.ships.Submarine;
import objects.ships.Turret;
import objects.world.Grid;
import objects.world.Wall;

import java.awt.*;

/**
 * This class contains everything about custom GameObject initialization
 */
public class Init {

    public static final boolean DEBUG_MODE = true;

    /**
     * Create a new GameObject
     * @param o The GameObject
     */
    public static void create(GameObject o){
        Master.getMaster().create(o);
    }

    /**
     * This method is called once at the start of the game
     */
    public static void init(){

        //INIT GOES HERE
        create(new Grid());

        BattleShip battleShip = new BattleShip(Color.DARK_GRAY);
        BattleShip bs = new BattleShip(140, 10, 10, 80, Color.GREEN);

        for (int i = 0; i < 8; i++) {
            bs.addTurret(new Turret(bs, 2.5, 10 * i + 1, 5, (i % 5 )+ 1));
        }
        create(bs);
        create(battleShip);

        create(new Submarine(new Vector2D(), new Vector2D(5, 5)));
        create(new Wall(20, 80, 50, 2));
    }

}
