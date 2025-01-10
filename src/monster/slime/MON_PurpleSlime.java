package monster.slime;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;
import object.coin.OBJ_Coin_Gold;

import java.util.Random;

public class MON_PurpleSlime extends Entity {

    final GamePanel gp;

    public MON_PurpleSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Red Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 15;
        life = maxLife;
        attack = 1;
        defense = 1;
        exp = 15;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("monster/purpleslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/purpleslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/purpleslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/purpleslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/purpleslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/purpleslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/purpleslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/purpleslime_down_2", gp.tileSize, gp.tileSize);
    }

    @Override
    public void setAction() {
        if (onPath) {
            stopChasingPlayer(gp.player, 15, 100);
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            checkProjectile(200, 30);
        } else {
            startChasingPlayer(gp.player, 5, 100);
            getRandomDirection(120);
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
    }

    @Override
    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;

        // Set drops
        if (i < 50) {
            dropItem(new OBJ_Coin_Gold(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
