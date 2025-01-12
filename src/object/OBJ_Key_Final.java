package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key_Final extends Entity {

    final GamePanel gp;
    public static final String objName = "Final Key";

    public OBJ_Key_Final(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        description = "[" + name + "]\nThe final key. Go back\nto the beginning.";
        down1 = setup("objects/key_final", gp.tileSize, gp.tileSize);
        solidArea.x = 5;
        price = 100;
        stackable = true;

        setDialogue();
    }

    private void setDialogue() {
        dialogues[0][0] = "You used the " + name + " to open the dungeon door.";
        dialogues[1][0] = "You swing the " + name + " around.";
        dialogues[1][1] = "Does god have hope for you?";
        dialogues[1][2] = "Would hope not.";
    }

    @Override
    public boolean use(Entity entity) {
        int objIndex = getDetected(entity, gp.obj, "Final Door");

        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(21);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            startDialogue(this, 1);
            return false;
        }
    }
}
