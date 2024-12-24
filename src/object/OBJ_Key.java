package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    final GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Key";
        description = "[" + name + "]\nIt's a key.\nIt might open something.";
        down1 = setup("objects/key", gp.tileSize, gp.tileSize);
        solidArea.x = 5;
        price = 100;
        stackable = true;
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != 999) {
            gp.ui.currentDialogue = "You used the " + name + " to open the door.";
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            gp.ui.currentDialogue = "What are you trying to do?";
            return false;
        }
    }
}
