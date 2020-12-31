package custom;

import core.general.Master;
import core.math.Vector2D;
import core.objects.base.Camera;
import core.objects.core.GameObject;
import custom.objects.MoveCamera;
import custom.objects.ships.BattleShip;
import custom.objects.ships.Submarine;
import custom.objects.ships.Turret;
import custom.objects.world.Grid;
import custom.objects.world.Wall;

import java.awt.*;

/**
 * This class contains everything about custom GameObject initialization
 */
public class Init {

    public static final boolean DEBUG_MODE = false;

    /**
     * Create a new GameObject
     * @param o The GameObject
     */
    public static <T extends GameObject> T create(T o){
        return Master.getMaster().create(o);
    }

    /**
     * This method is called once at the start of the game
     */
    public static void init(){

        //INIT GOES HERE

        Master.getMaster().getRenderEngine().setCurrentCamera(create(new MoveCamera(Vector2D.zero(), new Vector2D(1, 1))));

        create(new Grid());

        BattleShip battleShip = create(new BattleShip());

        battleShip.addTurret(create(new Turret(battleShip, new Vector2D(2.5, 7), 5, 3)));
        battleShip.addTurret(create(new Turret(battleShip, new Vector2D(2.5, 15), 5, 3)));
        battleShip.addTurret(create(new Turret(battleShip, new Vector2D(2.5, 25), 5, 3)));

        BattleShip bs = create(new BattleShip(new Vector2D(140, 10), new Vector2D(10, 80), Color.GREEN));

        for (int i = 0; i < 8; i++) {
            bs.addTurret(create(new Turret(bs, new Vector2D(2.5, 10 * i + 1), 5, (i % 5 )+ 1)));
        }

        create(new Submarine(new Vector2D(), new Vector2D(5, 5)));
        create(new Wall(new Vector2D(20, 80), new Vector2D(50, 2)));
    }

}
