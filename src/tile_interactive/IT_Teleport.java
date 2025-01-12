package tile_interactive;

import main.GamePanel;

public class IT_Teleport extends InteractiveTile{

    final GamePanel gp;

    public IT_Teleport(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("tiles_interactive/teleport", gp.tileSize, gp.tileSize);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public InteractiveTile getDestroyedForm() {
        return this;
    }
}
