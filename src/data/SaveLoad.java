package data;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.io.*;

public class SaveLoad {

    final GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Retrieves an object of type Entity based on the provided item name.
     *
     * @param itemName the name of the item for which an Entity object needs to be created
     * @return an Entity object corresponding to the specified item name, or null if the item name does not match any case
     */
    public Entity getObject(String itemName) {

        return switch (itemName) {
            case "Woodcutter's Axe" -> new OBJ_Axe(gp);
            case "Boots" -> new OBJ_Boots(gp);
            case "Key" -> new OBJ_Key(gp);
            case "Lantern" -> new OBJ_Lantern(gp);
            case "Red Potion" -> new OBJ_Potion_Red(gp);
            case "Blue Shield" -> new OBJ_Shield_Blue(gp);
            case "Wood Shield" -> new OBJ_Shield_Wood(gp);
            case "Normal Sword" -> new OBJ_Sword_Normal(gp);
            case "Tent" -> new OBJ_Tent(gp);
            case "Door" -> new OBJ_Door(gp);
            case "Chest" -> new OBJ_Chest(gp);
            default -> null;
        };
    }

    /**
     * Saves the current game state into a file named "save.dat".
     * The game state includes player statistics, inventory, equipment,
     * and details about objects on the map. The data is serialized using
     * the DataStorage class.
     * <p>
     * If the save operation fails due to an I/O error, an error message
     * is printed, and a RuntimeException is thrown.
     * <p>
     * This method involves the following steps:
     * 1. Serializing the player's current stats such as level, health, mana, etc.
     * 2. Capturing the player's inventory items and respective quantities.
     * 3. Recording the player's current weapon and shield slots.
     * 4. Storing information about objects present on the game map including
     *    their position, names, loot details, and states (e.g., opened or not).
     * <p>
     * Note that this method assumes the `gp` field and its associated objects
     * (like `player` and `obj`) are properly initialized and accessible.
     */
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));

            DataStorage ds = new DataStorage();

            // Player Stats
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            // Player Inventory
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }

            // TODO: Fix Highlighted bug later
            // Player Equipment
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

            // Objects on map
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (gp.obj[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "NA";
                    } else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if (gp.obj[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                    }
                }
            }

            // Write the DataStorage Object
            oos.writeObject(ds);
        } catch (IOException e) {
            System.out.println("Save failed");
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the game state from a file named "save.dat".
     * <p>
     * This method deserializes the game state stored in a `DataStorage` object, restoring:
     * 1. Player statistics, including attributes such as level, health, mana, strength, and experience.
     * 2. Player inventory with item types and quantities.
     * 3. Player's currently equipped weapon and shield, recalculating attack, defense, and attack images.
     * 4. Objects on the map, including their names, positions, loot, and state of interaction (e.g., opened or closed).
     * <p>
     * During the loading process, the player's inventory is cleared and repopulated based on the data
     * in the `DataStorage` object, and the player's current weapon and shield are re-equipped. Each
     * object's specific attributes, such as coordinates, loot, and opened state, are restored.
     * <p>
     * If the loading process fails due to an I/O issue or an incompatibility while deserializing the game state,
     * the method prints an error message ("Load failed") and throws a `RuntimeException`.
     * <p>
     * This method assumes the presence of a valid `DataStorage` object serialized in the correct format.
     */
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));

            // Read the DataStorage object
            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;

            // Player Inventory
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }

            // Player Equipment
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();

            // Objects on map
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (ds.mapObjectNames[mapNum][i].equals("NA")) {
                        gp.obj[mapNum][i] = null;
                    } else {
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if (ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.obj[mapNum][i].loot = getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if (gp.obj[mapNum][i].opened) {
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load failed");
            throw new RuntimeException(e);
        }
    }
}
