package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {
    final GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    boolean drawPath = true;
    final ArrayList<String> fileNames = new ArrayList<>();
    final ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gp) {
        this.gp = gp;

        // Read Tile Data file
        InputStream is = getClass().getResourceAsStream("/maps/tiledata.txt");
        assert is != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // Get tile names and collision status from the file
        String line;

        try {
            while ((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Initialise the tile array
        tile = new Tile[fileNames.size()];
        getTileImage();

        // Get the world's max column and row
        is = getClass().getResourceAsStream("/maps/worldmap.txt");
        assert is != null;
        br = new BufferedReader(new InputStreamReader(is));

        try {
            String line2 = br.readLine();
            String[] maxTile = line2.split(" ");

            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;
            mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loadMap("/maps/worldmap.txt", 0);
        loadMap("/maps/indoor01.txt", 1);
        loadMap("/maps/dungeon01.txt", 2);
        loadMap("/maps/dungeon02.txt", 3);
        loadMap("/maps/worldmap2.txt", 4);
    }

    public void getTileImage() {
        for (int i = 0; i < fileNames.size(); i++) {
            String fileName;
            boolean collision;

            // Get the file name and collision status
            fileName = fileNames.get(i);
            collision = collisionStatus.get(i).equals("true");

            setup(i, fileName, collision);
        }
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/" + imageName)));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        // Draw a green background for the entire visible area
        g2.setColor(new Color(0x67a55e));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {
            for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {

                int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                // Optimisation to only draw tiles that are visible on the screen
                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                    // Draw the tile if it is not null
                    if (tile[tileNum] != null) {
                        g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                    } else {
                        System.out.println("Tile " + tileNum + " is null");
                    }
                }
            }
        }
    }
}
