package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Dungeon extends Entity {

    final GamePanel gp;
    public static final String objName = "Dungeon Door";

    public OBJ_Door_Dungeon(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("objects/door_gold", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    private void setDialogue() {
        dialogues[0][0] = "You need a dungeon key to open this door.";
    }

    @Override
    public void interact() {
        startDialogue(this, 0);
    }
}
