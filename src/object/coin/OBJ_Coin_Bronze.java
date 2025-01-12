package object.coin;

import main.GamePanel;

public class OBJ_Coin_Bronze extends OBJ_Coin {

    public static final String objName = "Bronze Coin";

    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);

        type = type_pickUps;
        name = objName;
        value = 1;
        down1 = setup("objects/coin_bronze", gp.tileSize, gp.tileSize);
    }
}
