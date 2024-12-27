package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gp;
    public static final String objName = "Key";

    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        description = "[" + name + "]\nIt's a key.\nIt might open something.";
        down1 = setup("objects/key", gp.tileSize, gp.tileSize);
        solidArea.x = 5;
        price = 100;
        stackable = true;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You used the " + name + " to open the door.";
        dialogues[1][0] = "What are you trying to do?";
    }

    public boolean use(Entity entity) {
        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            startDialogue(this, 1);
            return false;
        }
    }
}
