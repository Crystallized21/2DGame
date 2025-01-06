package main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Main {
    public static JFrame window;

    public static void main(String[] args) {
        // Initialize the graphics context
        // TODO: Tabbing out of the game causes the game to have high FPS
        initializeGraphics();

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Blue Boy Adventure");
        new Main().setIcon();

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        gamePanel.config.loadConfig();
        if (gamePanel.fullScreenOn) {
            window.setUndecorated(true);
        }
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }

    private void setIcon() {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("player/boy_down_1.png")));
        window.setIconImage(icon.getImage());
    }

    private static void initializeGraphics() {
        // Create a dummy BufferedImage to initialise the graphics context
        BufferedImage dummyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dummyImage.createGraphics();

        // Set default rendering hints
        RenderingHints defaultHints = new RenderingHints(null);
        g2.setRenderingHints(defaultHints);

        AffineTransform defaultTransform = new AffineTransform();
        g2.setTransform(defaultTransform);

        // Draw a line to force the graphics context to initialise
        g2.drawLine(0, 0, 1, 1);
        g2.drawString("Debug", 0, 0);

        // Dispose of the graphics context
        g2.dispose();
    }
}