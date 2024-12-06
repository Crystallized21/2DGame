package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Key extends Entity {

    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "Key";
        description = "[ " + name + " ]\nIt's a key.\nIt might open something.";
        down1 = setup("objects/key", gp.tileSize, gp.tileSize);
        solidArea.x = 5;
    }
}
