package object.coin;

import main.GamePanel;

public class OBJ_Coin_Gold extends OBJ_Coin {

    public final static String objName = "Gold Coin";

    public OBJ_Coin_Gold(GamePanel gp) {
        super(gp);

        type = type_pickUps;
        name = objName;
        value = 10;
        down1 = setup("objects/coin_gold", gp.tileSize, gp.tileSize);
    }
}
