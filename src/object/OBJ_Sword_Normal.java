package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        name = "Normal Sword";
        description = "[ " + name + " ]\nA normal sword.\nGifted by the elder gods?";
        down1 = setup("objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
    }
}
