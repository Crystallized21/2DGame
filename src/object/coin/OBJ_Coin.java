package object.coin;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {

    final GamePanel gp;
    public int value;

    public OBJ_Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickUps;
    }

    @Override
    public boolean use(Entity entity) {
        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
        return true;
    }
}