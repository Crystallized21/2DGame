package main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Main {
    public static JFrame window;

    public static void main(String[] args) {
        // Initialize the graphics context
        initializeGraphics();

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

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

    private static void initializeGraphics() {
        // Create a dummy BufferedImage to initialize the graphics context
        BufferedImage dummyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dummyImage.createGraphics();

        // Set default rendering hints
        RenderingHints defaultHints = new RenderingHints(null);
        g2.setRenderingHints(defaultHints);

        AffineTransform defaultTransform = new AffineTransform();
        g2.setTransform(defaultTransform);

        // Draw a line to force the graphics context to initialize
        g2.drawLine(0, 0, 1, 1);
        g2.drawString("Debug", 0, 0);

        // Dispose of the graphics context
        g2.dispose();

    }
}