package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Final extends Entity {

    final GamePanel gp;
    public static final String objName = "Final Door";

    public OBJ_Door_Final(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("objects/door_final", gp.tileSize, gp.tileSize);
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
        dialogues[0][0] = "You need the final key to open this door.";
        dialogues[0][1] = "Tip: Explore the world and find the final key.";
        dialogues[0][2] = "It's in the last dungeon.";
    }

    @Override
    public void interact() {
        startDialogue(this, 0);
    }
}
