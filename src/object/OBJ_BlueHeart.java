package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity {

    final GamePanel gp;
    public static final String objName = "Blue Heart";

    public OBJ_BlueHeart(GamePanel gp) {
        super(gp);
        this.gp = gp;


        type = type_pickUps;
        name = objName;
        down1 = setup("objects/blueheart", gp.tileSize, gp.tileSize);

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You picked up the beautiful blue heart!";
        dialogues[0][1] = "You've found the blue heart, the legendary treasure!";
    }

    @Override
    public boolean use(Entity entity) {
        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
}
