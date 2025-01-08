package object.coin;

import main.GamePanel;

public class OBJ_Coin_Sliver extends OBJ_Coin {

    public final static String objName = "Sliver Coin";

    public OBJ_Coin_Sliver(GamePanel gp) {
        super(gp);

        type = type_pickUps;
        name = objName;
        value = 5;
        down1 = setup("placeholder", gp.tileSize, gp.tileSize);
    }
}
