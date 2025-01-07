package tile_interactive;

import entity.Entity;
import main.GamePanel;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class IT_DestructibleWall extends InteractiveTile {

    final GamePanel gp;

    public IT_DestructibleWall(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("tiles_interactive/destructiblewall", gp.tileSize, gp.tileSize);
        destructible = true;
        // TODO: Debugging reset life to 3
        life = 1;
    }

    @Override
    public boolean isCorrectItem(@NotNull Entity entity) {

        return entity.currentWeapon.type == type_pickaxe;
    }

    @Override
    public void playSE() {
        gp.playSE(20);
    }

    @Override
    public Color getParticleColor() {
        return new Color(65, 65, 65);
    }

    @Override
    public int getParticleSize() {
        // 6 Pixels
        return 6;
    }

    @Override
    public int getParticleSpeed() {
        return 1;
    }

    @Override
    public int getParticleMaxLife() {
        return 20;
    }
}
