package object;

import entity.Entity;
import main.GamePanel;
import org.jetbrains.annotations.NotNull;

public class OBJ_ManaCrystal extends Entity {

    GamePanel gp;
    public static final String objName = "Mana Crystal";

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUps;
        name = objName;
        value = 1;

        down1 = setup("objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setup("objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("objects/manacrystal_blank", gp.tileSize, gp.tileSize);
    }

    @Override
    public boolean use(@NotNull Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Mana + " + value);
        entity.mana += value;
        return true;
    }
}
