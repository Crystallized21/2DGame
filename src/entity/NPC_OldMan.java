package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_OldMan extends Entity {
    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        dialogueSet = -1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0][0] = "Hello, lad. How are you doing?";
        dialogues[0][1] = "So, you've come to this island \nin search of the treasure, have you?";
        dialogues[0][2] = "I'm sorry to say, but I used to be \na wizard but now... \nI'm a bit too old for taking an adventure.";
        dialogues[0][3] = "Well, I wish you the best of luck. \nI hope you find what you're looking for.";

        dialogues[1][0] = "If you feel that you need a rest from all the \nadventuring, you can rest at the pond.";
        dialogues[1][1] = "But in doing so, the monsters reappear again.\nI do not know why but there is a phenomenon\nhappening with them I guess.";
        dialogues[1][2] = "In any case, please do not push yourself too hard.";

        dialogues[2][0] = "I wonder what that door is all about.";
    }

    @Override
    public void setAction() {
        if (onPath) {
            int goalCol = 12;
            int goalRow = 9;
            // Use this code to get the player's current position if you want npc to follow the player


            searchPath(goalCol, goalRow);
        }
        else {
            actionLockCounter++;
            if (actionLockCounter == 120) {
                Random random = new Random();
                // Pick a random number between 1 and 100
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75) {
                    direction = "right";
                }

                actionLockCounter = 0;
            }
        }
    }

    @Override
    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;

        if (dialogues[dialogueSet][0] == null) {
            // Repeats the final dialogue
            dialogueSet--;
        }
    }
}
