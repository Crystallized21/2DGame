package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setup("objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[ " + name + " ]\nA red potion. It can\nrestore " + value + " HP.";
        price = 25;
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "Your drink the " + name + ".\nYour life has been restored by " + value + ".";
        entity.life += value;
        gp.playSE(2);
        return true;
    }
}
