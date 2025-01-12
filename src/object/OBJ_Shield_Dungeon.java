package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Dungeon extends Entity {

    public static final String objName = "Dungeon Shield";

    public OBJ_Shield_Dungeon(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        description = "[" + name + "]\nShield from the\ndungeon. It's glowing.";
        down1 = setup("objects/shield_dungeon", gp.tileSize, gp.tileSize);
        defenseValue = 3;
        price = 1500;
    }
}
