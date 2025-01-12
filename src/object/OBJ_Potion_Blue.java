package object;

import entity.Entity;
import main.GamePanel;
import org.jetbrains.annotations.NotNull;

public class OBJ_Potion_Blue extends Entity {

    final GamePanel gp;
    public static final String objName = "Blue Potion";

    public OBJ_Potion_Blue(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 4;
        down1 = setup("objects/potion_blue", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA blue potion. It can\nrestore " + value + " MP.";
        price = 40;
        stackable = true;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + ".\nYour mana has been restored by " + value + ".";
    }

    @Override
    public boolean use(@NotNull Entity entity) {
        startDialogue(this, 0);
        entity.mana += value;
        gp.playSE(2);
        return true;
    }
}
