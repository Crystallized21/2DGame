package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Dungeon extends Entity {

    public static final String objName = "Dungeon Sword";

    public OBJ_Sword_Dungeon(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = objName;
        down1 = setup("objects/sword_dungeon", gp.tileSize, gp.tileSize);
        attackValue = 6;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nSword from the\ndungeon. It's glowing.";
        knockBackPower = 4;
        motion1_duration = 5;
        motion2_duration = 20;
    }
}
