package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = "Wood Shield";
        description = "[" + name + "]\nA shield made of wood.\nIt's better than nothing.";
        down1 = setup("objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        price = 20;
    }
}
