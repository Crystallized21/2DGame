package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key_Dungeon extends Entity {

    final GamePanel gp;
    public static final String objName = "Dungeon Key";

    public OBJ_Key_Dungeon(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        description = "[" + name + "]\nA dungeon key. It opens\ndoors to dungeons.";
        down1 = setup("objects/key_dungeon", gp.tileSize, gp.tileSize);
        solidArea.x = 5;
        price = 100;
        stackable = true;

        setDialogue();
    }

    private void setDialogue() {
        dialogues[0][0] = "You used the " + name + " to open the dungeon door.";
        dialogues[1][0] = "You swing the " + name + " around.";
        dialogues[1][1] = "You look like an idiot.";
    }

    @Override
    public boolean use(Entity entity) {
        int objIndex = getDetected(entity, gp.obj, "Dungeon Door");

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
