package object;

import entity.Entity;
import main.GamePanel;
import org.jetbrains.annotations.NotNull;

public class OBJ_Potion_Red extends Entity {

    final GamePanel gp;
    public static final String objName = "Red Potion";

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 5;
        down1 = setup("objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA red potion. It can\nrestore " + value + " HP.";
        price = 20;
        stackable = true;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + ".\nYour life has been restored by " + value + ".";
    }

    @Override
    public boolean use(@NotNull Entity entity) {
        startDialogue(this, 0);
        entity.life += value;
        gp.playSE(2);
        return true;
    }
}
