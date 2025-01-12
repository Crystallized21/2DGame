package main;

import data.Progress;
import entity.Entity;
import entity.NPC_BigRock;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.*;
import monster.slime.*;
import object.*;
import object.OBJ_Door_Dungeon;
import tile_interactive.*;

public class AssetSetter {
    final GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;

        addObject(mapNum, i++, new OBJ_Axe(gp), 38, 9);
        addObject(mapNum, i++, new OBJ_Lantern(gp), 18, 20);
        addObject(mapNum, i++, new OBJ_Tent(gp), 19, 20);
        addObject(mapNum, i++, new OBJ_Door(gp), 14, 28);
        addObject(mapNum, i++, new OBJ_Door(gp), 12, 12);

        addChest(mapNum, i++, new OBJ_Chest(gp), 30, 29, new OBJ_Key(gp));

        mapNum = 2;
        i = 0;

        addChest(mapNum, i++, new OBJ_Chest(gp), 40, 41, new OBJ_Pickaxe(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 13, 16, new OBJ_Potion_Blue(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 26, 34, new OBJ_Potion_Red(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 27, 15, new OBJ_Potion_Red(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 27, 15, new OBJ_Potion_Blue(gp));

        addObject(mapNum, i++, new OBJ_Door_Iron(gp), 18, 23);

        mapNum = 3;
        i = 0;
        addObject(mapNum, i++, new OBJ_Door_Iron(gp), 25, 15);
        addObject(mapNum, i++, new OBJ_BlueHeart(gp), 25, 8);

        mapNum = 4;
        i = 0;
        addObject(mapNum, i++, new OBJ_Door(gp), 19, 46);

        addObject(mapNum, i++, new OBJ_Door_Dungeon(gp), 23 , 13);

        addChest(mapNum, i++, new OBJ_Chest(gp), 19, 48, new OBJ_Key(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 24, 47, new OBJ_Key(gp));

        mapNum = 5;
        i = 0;

        addObject(mapNum, i++, new OBJ_Door_Iron(gp), 20, 46);
        addObject(mapNum, i++, new OBJ_Door(gp), 23, 12);
        addObject(mapNum, i++, new OBJ_Key(gp), 29, 12);
        addObject(mapNum, i++, new OBJ_Key(gp), 35, 31);
        addObject(mapNum, i++, new OBJ_Door(gp), 44, 10);
        addObject(mapNum, i++, new OBJ_Door(gp), 31, 3);

        addChest(mapNum, i++, new OBJ_Chest(gp), 16, 45, new OBJ_Potion_Red(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 31, 44, new OBJ_Key(gp));

        mapNum = 6;
        i = 0;

        addChest(mapNum, i++, new OBJ_Chest(gp), 48, 1, new OBJ_Potion_Red(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 5, 36, new OBJ_Potion_Blue(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 11, 7, new OBJ_Potion_Red(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 12, 7, new OBJ_Potion_Blue(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 13, 7, new OBJ_Potion_Blue(gp));

        addObject(mapNum, i++, new OBJ_Door_Dungeon(gp), 34, 5);
        addObject(mapNum, i++, new OBJ_Door_Iron(gp), 15, 4);

        mapNum = 8;
        i = 0;

        addObject(mapNum, i++, new OBJ_Door_Dungeon(gp), 28, 25);
        addChest(mapNum, i++, new OBJ_Chest(gp), 30, 23, new OBJ_Sword_Dungeon(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 31, 23, new OBJ_Shield_Dungeon(gp));

        addChest(mapNum, i++, new OBJ_Chest(gp), 35, 35, new OBJ_Potion_Blue(gp));

        addChest(mapNum, i++, new OBJ_Chest(gp), 14, 31, new OBJ_Potion_Red(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 19, 38, new OBJ_Potion_Blue(gp));

        addChest(mapNum, i++, new OBJ_Chest(gp), 32, 30, new OBJ_Key(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 48, 18, new OBJ_Potion_Red(gp));

        addObject(mapNum, i++, new OBJ_Door_Iron(gp), 9, 22);

        addObject(mapNum, i++, new OBJ_Door(gp), 3, 30);

        addChest(mapNum, i++, new OBJ_Chest(gp), 27, 3, new OBJ_Potion_Red(gp));
        addChest(mapNum, i++, new OBJ_Chest(gp), 24, 17, new OBJ_Potion_Red(gp));
    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        addNPC(mapNum, i++, new NPC_OldMan(gp), 21, 21);

        mapNum = 1;
        i = 0;

        addNPC(mapNum, i++, new NPC_Merchant(gp), 12, 7);

        mapNum = 2;
        i = 0;

        addNPC(mapNum, i++, new NPC_BigRock(gp), 20, 25);
        addNPC(mapNum, i++, new NPC_BigRock(gp), 11, 18);
        addNPC(mapNum, i++, new NPC_BigRock(gp), 23, 14);

        mapNum = 7;
        i = 0;

        addNPC(mapNum, i++, new NPC_Merchant(gp), 12, 7);
    }

    public void setMonster() {
        int mapNum = 0;
        int i = 0;

        addMonster(mapNum, i++, new MON_GreenSlime(gp), 21, 38);
        addMonster(mapNum, i++, new MON_GreenSlime(gp), 23, 42);
        addMonster(mapNum, i++, new MON_GreenSlime(gp), 24, 37);
        addMonster(mapNum, i++, new MON_GreenSlime(gp), 34, 42);
        addMonster(mapNum, i++, new MON_GreenSlime(gp), 38, 42);
        addMonster(mapNum, i++, new MON_Orc(gp), 12, 33);
        addMonster(mapNum, i++, new MON_RedSlime(gp), 36, 8);
        addMonster(mapNum, i++, new MON_RedSlime(gp), 40, 8);
        addMonster(mapNum, i++, new MON_RedSlime(gp), 40, 10);

        mapNum = 2;
        i = 0;

        addMonster(mapNum, i++, new MON_Bat(gp), 34, 39);
        addMonster(mapNum, i++, new MON_Bat(gp), 36, 25);
        addMonster(mapNum, i++, new MON_Bat(gp), 39, 26);
        addMonster(mapNum, i++, new MON_Bat(gp), 28, 11);
        addMonster(mapNum, i++, new MON_Bat(gp), 10, 19);

        mapNum = 3;
        i = 0;

        if (!Progress.skeletonLordDefeated) {
            addMonster(mapNum, i++, new MON_SkeletonLord(gp), 23, 16);
        }

        mapNum = 4;
        i = 0;

        addMonster(mapNum, i++, new MON_BlueSlime(gp), 28, 44);
        addMonster(mapNum, i++, new MON_BlueSlime(gp), 27, 44);
        addMonster(mapNum, i++, new MON_BlueSlime(gp), 29, 44);

        addMonster(mapNum, i++, new MON_YellowSlime(gp), 37, 6);
        addMonster(mapNum, i++, new MON_YellowSlime(gp), 43, 6);
        addMonster(mapNum, i++, new MON_YellowSlime(gp), 43, 9);
        addMonster(mapNum, i++, new MON_YellowSlime(gp), 36, 9);

        mapNum = 5;
        i = 0;

        addMonster(mapNum, i++, new MON_Bat(gp), 27, 31);
        addMonster(mapNum, i++, new MON_Bat(gp), 31, 31);
        addMonster(mapNum, i++, new MON_Bat(gp), 14, 46);
        addMonster(mapNum, i++, new MON_Bat(gp), 26, 9);
        addMonster(mapNum, i++, new MON_Bat(gp), 26, 14);

        // TODO: Add dungeon monster that drop a dungeon key to loots
        addMonster(mapNum, i++, new MON_Orc(gp), 37, 18);
        addMonster(mapNum, i++, new MON_Orc(gp), 43, 18);
        addMonster(mapNum, i++, new MON_Bat(gp), 32, 11);
        addMonster(mapNum, i++, new MON_Bat(gp), 32, 27);
        addMonster(mapNum, i++, new MON_Bat(gp), 47, 27);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 22, 36);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 26, 36);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 25, 38);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 39, 45);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 35, 45);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 18, 4);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 14, 4);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 35, 15);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 35, 22);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 43, 45);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 43, 16);

        mapNum = 6;
        i = 0;

        addMonster(mapNum, i++, new MON_Orc(gp), 5, 40);

        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 38, 44);
        addMonster(mapNum, i++, new MON_YellowSlime(gp), 43, 44);
        addMonster(mapNum, i++, new MON_BlueSlime(gp), 41, 47);

        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 38, 11);
        addMonster(mapNum, i++, new MON_YellowSlime(gp), 42, 11);
        addMonster(mapNum, i++, new MON_BlueSlime(gp), 44, 11);
        addMonster(mapNum, i++, new MON_PurpleSlime(gp), 44, 16);
        addMonster(mapNum, i++, new MON_YellowSlime(gp), 41, 16);
        addMonster(mapNum, i++, new MON_BlueSlime(gp), 38, 16);

        mapNum = 8;
        i = 0;

        // TODO: make a desperate class for orc dungeon monster
        addMonster(mapNum, i++, new MON_Orc(gp), 40, 7);
    }

    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;

        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 27, 12));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 28, 12));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 29, 12));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 30, 12));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 31, 12));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 32, 12));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 33, 12));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 30, 21));

        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 18, 40));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 17, 40));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 16, 40));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 15, 40));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 14, 40));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 13, 40));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 13, 41));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 12, 41));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 11, 41));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 10, 41));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 10, 40));

        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 25, 27));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 26, 27));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 27, 28));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 27, 29));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 27, 30));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 27, 31));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 28, 31));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 29, 31));

        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 37, 7));

        // Dungeon
        mapNum = 2;
        i = 0;

        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 18, 30));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 17, 31));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 17, 32));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 17, 34));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 18, 34));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 18, 33));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 10, 22));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 10, 24));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 38, 18));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 38, 19));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 38, 20));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 38, 21));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 18, 13));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 18, 14));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 22, 28));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 30, 28));
        addInteractiveTile(mapNum, i++, new IT_DestructibleWall(gp, 32, 28));

        addInteractiveTile(mapNum, i++, new IT_MetalPlate(gp, 20, 22));
        addInteractiveTile(mapNum, i++, new IT_MetalPlate(gp, 8, 17));
        addInteractiveTile(mapNum, i++, new IT_MetalPlate(gp, 39, 31));

        mapNum = 4;
        i = 0;

        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 19, 34));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 19, 35));

        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 16, 33));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 15, 33));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 14, 33));

        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 33, 38));

        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 33, 47));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 34, 47));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 35, 47));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 36, 47));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 37, 47));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 38, 47));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 39, 47));

        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 1, 23));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 1, 24));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 1, 25));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 1, 26));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 1, 27));

        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 40, 1));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 41, 1));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 42, 1));

        mapNum = 6;
        i = 0;

        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 43, 7));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 43, 6));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 43, 5));
        addInteractiveTile(mapNum, i++, new IT_DryTree(gp, 43, 4));

        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 22, 48));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 23, 48));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 24, 48));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 25, 48));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 26, 48));
        addInteractiveTile(mapNum, i++, new IT_Teleport(gp, 27, 48));
    }

    private void addObject(int mapNum, int i, Entity object, int worldX, int worldY) {
        gp.obj[mapNum][i] = object;
        gp.obj[mapNum][i].worldX = gp.tileSize * worldX;
        gp.obj[mapNum][i].worldY = gp.tileSize * worldY;
    }

    private void addChest(int mapNum, int i, Entity obj, int worldX, int worldY, Entity loot) {
        if (obj instanceof OBJ_Chest) {
            obj.setLoot(loot);
        }
        addObject(mapNum, i, obj, worldX, worldY);
    }

    private void addMonster(int mapNum, int i, Entity monster, int worldX, int worldY) {
        gp.monster[mapNum][i] = monster;
        gp.monster[mapNum][i].worldX = gp.tileSize * worldX;
        gp.monster[mapNum][i].worldY = gp.tileSize * worldY;
    }

    private void addNPC(int mapNum, int i, Entity npc, int worldX, int worldY) {
        gp.npc[mapNum][i] = npc;
        gp.npc[mapNum][i].worldX = gp.tileSize * worldX;
        gp.npc[mapNum][i].worldY = gp.tileSize * worldY;
    }

    private void addInteractiveTile(int mapNum, int i, InteractiveTile tile) {
        gp.iTile[mapNum][i] = tile;
    }
}
