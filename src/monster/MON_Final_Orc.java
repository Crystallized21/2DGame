package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Key_Final;

public class MON_Final_Orc extends Entity {

    final GamePanel gp;

    public MON_Final_Orc(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Final Orc";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 160;
        life = maxLife;
        attack = 8;
        defense = 3;
        exp = 160;
        knockBackPower = 5;

        // Solid Area
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("monster/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/orc_right_2", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage() {
        attackUp1 = setup("monster/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("monster/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("monster/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("monster/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("monster/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("monster/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("monster/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("monster/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    @Override
    public void setAction() {
        if (onPath) {
            stopChasingPlayer(gp.player, 15, 100);
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        } else {
            startChasingPlayer(gp.player, 5, 100);
            getRandomDirection(120);
        }

        if (!attacking) {
            checkAttack(30, gp.tileSize * 4, gp.tileSize);
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
    }

    @Override
    public void checkDrop() {
        dropItem(new OBJ_Key_Final(gp));
    }
}
