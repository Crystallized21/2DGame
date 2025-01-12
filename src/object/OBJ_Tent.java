package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity {
    
    /* TODO: Extend the functionality of this such as inn or houses
    *   Maybe like having the player touch the bed talk to the innkeeper
    *   to sleep.*/
    
    final GamePanel gp;
    public static final String objName = "Tent";
    
    public OBJ_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_consumable;
        name = objName;
        down1 = setup("objects/tent", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n You can sleep until\n the next day.";
        stackable = true;
        price = 50;
    }
    
    @Override
    public boolean use(Entity entity) {
        gp.gameState = gp.sleepState;
        gp.playSE(14);
        // Reset player's life and mana
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;
    }
}
