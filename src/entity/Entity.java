package entity;

import main.GamePanel;
import main.UtilityTool;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Entity {
    final GamePanel gp;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2, 
            guardUp, guardDown, guardLeft, guardRight;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public final String[][] dialogues = new String[20][20];
    public Entity attacker;
    public String knockBackDirection;
    public Entity linkedEntity;
    public boolean temp = false;

    // State
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;
    public boolean inRage = false;
    public boolean sleep = false;
    public boolean drawing = true;

    // Counters
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    public int hpBarCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;

    // Character Attributes
    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;
    public boolean boss;

    // Item Attributes
    public final ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    // Types
    public int type; // 0 = Player, 1 = NPC, 2 = Monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickUps = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;
    public final int type_pickaxe = 10;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public int getScreenX() {
        return worldX - gp.player.worldX + gp.player.screenX;
    }

    public int getScreenY() {
        return worldY - gp.player.worldY + gp.player.screenY;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol() {
        return (worldX + solidArea.x) / gp.tileSize;
    }

    public int getRow() {
        return (worldY + solidArea.y) / gp.tileSize;
    }

    public int getCenterX() {
        return worldX + left1.getWidth() / 2;
    }

    public int getCenterY() {
        return worldY + up1.getHeight() / 2;
    }

    public int getXDistance(@NotNull Entity target) {
        return Math.abs(getCenterX() - target.worldX);
    }

    public int getYDistance(@NotNull Entity target) {
        return Math.abs(getCenterY() - target.worldY);
    }

    public int getTileDistance(Entity target) {
        return (getXDistance(target) + getYDistance(target)) / gp.tileSize;
    }

    public int getGoalCol(@NotNull Entity target) {
        return (target.worldX + target.solidArea.x) / gp.tileSize;
    }

    public int getGoalRow(@NotNull Entity target) {
        return (target.worldY + target.solidArea.y) / gp.tileSize;
    }

    public void resetCounter() {
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }

    /**
     * Sets the loot associated with this entity.
     *
     * @param loot the entity representing the loot to be associated with this entity
     */
    public void setLoot(Entity loot) {}

    /**
     * Defines the action or behavior of the entity in a specific game context.
     * This method is responsible for determining and setting the current state or
     * sequence of actions the entity should perform. The implementation typically
     * includes logic for deciding the entity's movements, attacks, interactions,
     * or other gameplay-related activities.
     */
    public void setAction() {}

    /**
     * Moves the entity in the specified direction.
     *
     * @param direction the direction in which the entity should move. This can
     *                  typically be "up", "down", "left", or "right" depending
     *                  on the implementation.
     */
    public void move(String direction) {}

    /**
     * Handles the reaction of the entity when it takes damage.
     * This method is responsible for managing behavior, animations, or state changes
     * that occur when the entity is subjected to damage.
     */
    public void damageReaction() {}

    /**
     * Initiates dialogue or sound associated with the entity.
     * This method handles the logic for the entity to speak,
     * which could involve triggering predefined dialogues,
     * sounds, or expressions based on the game's context.
     */
    public void speak() {}

    public void facePlayer() {
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void startDialogue(Entity entity, int setNum) {
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }

    /**
     * Defines the interaction behavior of an entity within the game.
     * This method manages what happens when the entity interacts with
     * another entity, object, or element in its environment. The implementation
     * typically varies based on the entity's type and context within the game,
     * allowing for actions such as picking up items, opening doors, or triggering events.
     */
    public void interact() {}

    /**
     * Executes the use action for the specified entity.
     * This method is typically responsible for defining what occurs when an entity is used,
     * such as consuming an item or activating a particular effect specific to the entity and its context in the game.
     *
     * @param entity the entity to be used
     * @return a boolean indicating whether the use action was successful
     */
    public boolean use(Entity entity) {
        return false;
    }

    /**
     * Checks if the entity should drop an item or loot upon certain conditions.
     * This method determines whether an entity will drop its assigned loot
     * based on its state or internal logic.
     */
    public void checkDrop() {}

    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    /**
     * Retrieves the color of the particle associated with this entity.
     *
     * @return the color of the particle, or null if no color is defined
     */
    public Color getParticleColor() {
        return null;
    }

    /**
     * Retrieves the size of the particle associated with this entity.
     *
     * @return the size of the particle as an integer
     */
    public int getParticleSize() {
        return 0;
    }

    /**
     * Retrieves the speed of the particle associated with this entity.
     *
     * @return the speed of the particle as an integer
     */
    public int getParticleSpeed() {
        return 0;
    }

    /**
     * Retrieves the maximum lifespan of a particle associated with this entity.
     *
     * @return the maximum particle life as an integer
     */
    public int getParticleMaxLife() {
        return 0;
    }

    public void generateParticle(@NotNull Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Random random = new Random();
        int[] xOffsets = {-2, 2, -2, 2};
        int[] yOffsets = {-1, -1, 1, 1};

        for (int i = 0; i < 4; i++) {
            int xOffset = xOffsets[i] + random.nextInt(3) - 1; // Randomise between -3 and 3
            int yOffset = yOffsets[i] + random.nextInt(3) - 1; // Randomise between -2 and 2
            Particle p = new Particle(gp, target, color, size, speed, maxLife, xOffset, yOffset);
            gp.particleList.add(p);
        }
    }

    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer) {
            damagePlayer(attack);
        }
    }

    /**
     * Updates the state and behavior of the entity during each game cycle.
     * <p>
     * This method is responsible for managing the entity's movement, actions,
     * and animations based on its current state and conditions. It handles
     * several behaviors, including knockback, attacking, collision checking,
     * invincibility, and other status effects like off-balance.
     * <p>
     * Key behaviors include:
     * - Handling knockback mechanics: movement and the resolution of collision during knockback.
     * - Managing attacking actions through the `attacking` method.
     * - Setting and executing the entity's actions when not in a special state.
     * - Updating sprite frames for animations based on a counter.
     * - Managing temporary effects such as invincibility, shot availability, and off-balance states.
     */
    public void update() {
        if (!sleep) {
            if (knockBack) {
                checkCollision();

                if (collisionOn) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                } else
                {
                switch (knockBackDirection) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

                knockBackCounter++;
                if (knockBackCounter == 10) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
            } else if (attacking) {
                attacking();
            } else {
                setAction();
                checkCollision();

                if (!collisionOn) {
                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }

                spriteCounter++;
                if (spriteCounter > 24) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 40) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }

            if (shotAvailableCounter < 30) {
                shotAvailableCounter++;
            }

            if (offBalance) {
                offBalanceCounter++;
                if (offBalanceCounter > 60) {
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
        }
    }
    
    // TODO: Prob rename this to something better
    public void checkStartChasing(Entity target, int distance, int rate) {
        // Check if the player is near
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);

            if (i == 0) {
                onPath = true;
            }
        }
    }
    
    // TODO: Prob rename this to something better
    public void checkStopChasing(Entity target, int distance, int rate) {
        // If the monster is on the path and the player are far away, stop following the player
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);

            if (i == 0) {
                onPath = false;
            }
        }
    }
    
    public void getRandomDirection(int interval) {
        // Get a random direction if the monster is not on the path
        actionLockCounter++;
        if (actionLockCounter > interval) {
            Random random = new Random();
            // Pick a random number between 1 and 100
            int i = random.nextInt(100) + 1;

            if (i <= 25) direction = "up";
            if (i > 25 && i <= 50) direction = "down";
            if (i > 50 && i <= 75) direction = "left";
            if (i > 75) direction = "right";

            actionLockCounter = 0;
        }
    }

    public void moveTowardPlayer(int interval) {
        actionLockCounter++;

        if (actionLockCounter > interval) {
            if (getXDistance(gp.player) > getYDistance(gp.player)) {
                if (gp.player.getCenterX() < getCenterX()) {
                    direction = "left";
                } else {
                    direction = "right";
                }
            } else if (getXDistance(gp.player) < getYDistance(gp.player)) {
                if (gp.player.getCenterY() < getCenterY()) {
                    direction = "up";
                } else {
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }
    }

    public String getOppositeDirection(@NotNull String direction) {
        return switch (direction) {
            case "up" -> "down";
            case "down" -> "up";
            case "left" -> "right";
            case "right" -> "left";
            default -> "";
        };
    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;

            // Save the current world position
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust the players world position for the attack area
            switch (direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            // Attack area is now the solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == type_monster) {
                if (gp.cChecker.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            } else {
                // If the type is player,
                // Check for collision with the updated world position and solid area
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            // Reset the player's world position and solid area after the collision check
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    
    public void checkAttack(int rate, int straight, int horizontal) {
        boolean targetInRange = false;
        int xDistance = getXDistance(gp.player);
        int yDistance = getYDistance(gp.player);
        
        switch (direction) {
            case "up":
                if (gp.player.getCenterY() < getCenterY() && yDistance < straight && xDistance < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (gp.player.getCenterY() > getCenterY() && yDistance < straight && xDistance < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (gp.player.getCenterX() < getCenterX() && xDistance < straight && yDistance < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gp.player.getCenterX() > getCenterX() && xDistance < straight && yDistance < horizontal) {
                    targetInRange = true;
                }
                break;
        }

        if (targetInRange) {
            // Check if it can initiate an attack
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }

    // TODO: Prob rename this to something better
    public void checkProjectile(int rate, int shotInterval) {
        // Check if it can it shoot a projectile
        int i = new Random().nextInt(rate);
        if (i == 0 && !projectile.alive && shotAvailableCounter == shotInterval) {
            projectile.set(worldX, worldY, direction, true, this);

            // Check Vacancy
            for (int j = 0; j < gp.projectile[1].length; j++) {
                if (gp.projectile[gp.currentMap][j] == null) {
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }
    }

    public void damagePlayer(int attack) {
        if (!gp.player.invincible) {
            int damage = attack - gp.player.defense;
            
            // Get the opposite direction of the attacker
            String canGuardDirection = getOppositeDirection(direction);

            if (gp.player.guarding && gp.player.direction.equals(canGuardDirection)) {
                // Parry
                if (gp.player.guardCounter < 10) {
                    damage = 0;
                    gp.playSE(16);
                    setKnockBack(this, gp.player, knockBackPower);
                    offBalance = true;
                    spriteCounter = -60;
                } else {
                    damage /= 3;
                    gp.playSE(15);
                }
            } else {
                // Not guarding, so give damage to the player
                gp.playSE(6);
                if (damage < 1) {
                    damage = 1;
                }
            }
            
            if (damage != 0) {
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower);
            }
            
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void setKnockBack(@NotNull Entity target, @NotNull Entity attacker, int knockBackPower) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public boolean inCamera() {

        // 5 times cause the skeleton boss is big

        return worldX + gp.tileSize * 5 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize * 5 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY;
    }

    /**
     * Draws the character or object on the screen based on its position, direction,
     * movement, and actions such as attacking or being invincible. The method also
     * handles optimizations to only render elements visible within the screen bounds
     * and displays additional visual elements like health bars or dying animations.
     *
     * @param g2 the {@code Graphics2D} object used for rendering images and shapes
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Optimisation to only draw tiles that are visible on the screen
        if(inCamera()) {
            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();

            switch (direction) {
                case "up":
                    if (!attacking) {
                        if (spriteNum == 1) {image = up1;}
                        if (spriteNum == 2) {image = up2;}
                    }
                    if (attacking) {
                        tempScreenY = getScreenY() - up1.getHeight();
                        if (spriteNum == 1) {image = attackUp1;}
                        if (spriteNum == 2) {image = attackUp2;}
                    }
                    break;
                case "down":
                    if (!attacking) {
                        if (spriteNum == 1) {image = down1;}
                        if (spriteNum == 2) {image = down2;}
                    }
                    if (attacking) {
                        if (spriteNum == 1) {image = attackDown1;}
                        if (spriteNum == 2) {image = attackDown2;}
                    }
                    break;
                case "left":
                    if (!attacking) {
                        if (spriteNum == 1) {image = left1;}
                        if (spriteNum == 2) {image = left2;}
                    }
                    if (attacking) {
                        tempScreenX = getScreenX() - left1.getWidth();
                        if (spriteNum == 1) {image = attackLeft1;}
                        if (spriteNum == 2) {image = attackLeft2;}
                    }
                    break;
                case "right":
                    if (!attacking) {
                        if (spriteNum == 1) {image = right1;}
                        if (spriteNum == 2) {image = right2;}
                    }
                    if (attacking) {
                        if (spriteNum == 1) {image = attackRight1;}
                        if (spriteNum == 2) {image = attackRight2;}
                    }
                    break;
            }

            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4F);
            }

            if (dying) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY, null);
            changeAlpha(g2, 1f);
        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 5;

        if (dyingCounter <= i) changeAlpha(g2, 0f);
        if (dyingCounter > i && dyingCounter <= i * 2) changeAlpha(g2, 1f);
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) changeAlpha(g2, 0f);
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) changeAlpha(g2, 1f);
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) changeAlpha(g2, 0f);
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) changeAlpha(g2, 1f);
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) changeAlpha(g2, 0f);
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) changeAlpha(g2, 1f);
        if (dyingCounter > i * 8) {
            alive = false;
        }
    }

    public void changeAlpha(@NotNull Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search()) {
            // Next worldX and worldY
            int nextX = gp.pFinder.pathList.getFirst().col * gp.tileSize;
            int nextY = gp.pFinder.pathList.getFirst().row * gp.tileSize;

            // Entity's solid area position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                // Left or Right
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                // Up or Left
                direction = "up";
                checkCollision();
                if (collisionOn) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                // Up or Right
                direction = "up";
                checkCollision();
                if (collisionOn) {
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                // Down or Left
                direction = "down";
                checkCollision();
                if (collisionOn) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                // Down or Right
                direction = "down";
                checkCollision();
                if (collisionOn) {
                    direction = "right";
                }
            }

            // If th entity reaches the goal, stop the path
            int nextCol = gp.pFinder.pathList.getFirst().col;
            int nextRow = gp.pFinder.pathList.getFirst().row;
            if (nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }
        }
    }

    public int getDetected(@NotNull Entity user, Entity[][] target, String targetName) {
        int index = 999;
        // Check surrounding objects
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up":
                nextWorldY = user.getTopY() - gp.player.speed;
                break;
            case "down":
                nextWorldY = user.getBottomY() + gp.player.speed;
                break;
            case "left":
                nextWorldX = user.getLeftX() - gp.player.speed;
                break;
            case "right":
                nextWorldX = user.getRightX() + gp.player.speed;
                break;
        }

        int col = nextWorldX / gp.tileSize;
        int row = nextWorldY / gp.tileSize;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getCol() == col &&
                        target[gp.currentMap][i].getRow() == row &&
                        target[gp.currentMap][i].name.equals(targetName)) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }
}
