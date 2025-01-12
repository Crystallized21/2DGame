package main;

import data.Progress;
import entity.Entity;

public class EventHandler {
    final GamePanel gp;
    final EventRect[][][] eventRect;
    final Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventMaster = new Entity(gp);

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
        setDialogue();
    }

    public void setDialogue() {
        eventMaster.dialogues[0][0] = "You fell into a pit!";

        eventMaster.dialogues[1][0] = """
                You drank from the pool and feel refreshed!\s
                Your life and mana has been restored!\
                
                (Your progress has been saved)""";
        eventMaster.dialogues[1][1] = "Damn, this is good water my dude.";
    }

    public void checkEvent() {
        // Check if the player character is more than 1 tile away from the previous event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if (hit(0, 27, 16, "right")) damagePit(gp.dialogueState);
            else if (hit(0, 23, 12, "up")) healingPool(gp.dialogueState);
            else if (hit(0, 10, 39, "any")) teleport(1, 12, 13, gp.indoor); // to merchant's house
            else if (hit(1, 12, 13, "any")) teleport(0, 10, 39, gp.outside); // from merchant's to outside
            else if (hit(1, 12, 9, "up")) speak(gp.npc[1][0]);

            else if (hit(0, 12, 9, "any")) teleport(2, 9, 41, gp.dungeon); // to dungeon
            else if (hit(2, 9, 41, "any")) teleport(0, 12, 9, gp.outside); // to outside
            else if (hit(2, 8, 7, "any")) teleport(3, 26, 41, gp.dungeon); // b2
            else if (hit(3, 26, 41, "any")) teleport(2, 8, 7, gp.dungeon); // to dungeon

            else if (hit(0, 37, 7, "any")) teleport(4, 1, 25, gp.outside);

            else if (hit(3, 25, 27, "any")) skeletonLord(); // Boss Cutscene

            else if (hit(4, 37, 29, "any")) healingPool(gp.dialogueState);

            else if (hit(4, 23, 8, "any")) teleport(5, 3, 24, gp.dungeon);
            else if (hit(5, 2, 24, "any")) teleport(4, 23, 9, gp.outside);

            else if (hit(4, 1, 23, "any")) teleport(0, 37, 7, gp.outside);
            else if (hit(4, 1, 24, "any")) teleport(0, 37, 7, gp.outside);
            else if (hit(4, 1, 25, "any")) teleport(0, 37, 7, gp.outside);
            else if (hit(4, 1, 26, "any")) teleport(0, 37, 7, gp.outside);
            else if (hit(4, 1, 27, "any")) teleport(0, 37, 7, gp.outside);


            else if (hit(4, 40, 1, "any")) teleport(6, 25, 48, gp.outside);
            else if (hit(4, 41, 1, "any")) teleport(6, 25, 48, gp.outside);
            else if (hit(4, 42, 1, "any")) teleport(6, 25, 48, gp.outside);

            else if (hit(6, 22, 48, "any")) teleport(4, 40, 2, gp.outside);
            else if (hit(6, 23, 48, "any")) teleport(4, 40, 2, gp.outside);
            else if (hit(6, 24, 48, "any")) teleport(4, 40, 2, gp.outside);
            else if (hit(6, 25, 48, "any")) teleport(4, 40, 2, gp.outside);
            else if (hit(6, 26, 48, "any")) teleport(4, 40, 2, gp.outside);
            else if (hit(6, 27, 48, "any")) teleport(4, 40, 2, gp.outside);

            else if (hit(6, 44, 29, "any")) teleport(7, 12, 13, gp.indoor);
            else if (hit(7, 12, 13, "any")) teleport(6, 44, 29, gp.outside);

            else if (hit(6, 24, 5, "any")) teleport(8, 15, 25, gp.outside);
            else if (hit(6, 24, 6, "any")) teleport(8, 15, 26, gp.outside);

            else if (hit(8, 15, 25, "any")) teleport(6, 24, 5, gp.outside);
            else if (hit(8, 15, 26, "any")) teleport(6, 24, 6, gp.outside);
        }
    }

    public boolean hit(int map, int col, int row, String requireDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.equals(requireDirection) || requireDirection.equals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.playSE(6);
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.life -= 1;
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            gp.saveLoad.save();
            eventMaster.startDialogue(eventMaster, 1);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
        }
    }

    public void teleport(int map, int col, int row, int area) {
        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(13);
    }

    public void speak(Entity entity) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }

    public void skeletonLord() {
        if (!gp.bossBattleOn && !Progress.skeletonLordDefeated) {
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = gp.csManager.skeletonLord;
        }
    }
}
