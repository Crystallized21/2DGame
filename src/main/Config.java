package main;

import java.io.*;

public class Config {
    final GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            // Full Screen
            if (gp.fullScreenOn) {
                bw.write("fullScreenOn=true");
            }
            if (!gp.fullScreenOn) {
                bw.write("fullScreenOn=false");
            }
            bw.newLine();

            // Music Volume
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            // Sound Effect Volume
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();
            // Full Screen
            if (s.equals("fullScreenOn=true")) {
                gp.fullScreenOn = true;
            }
            if (s.equals("fullScreenOn=false")) {
                gp.fullScreenOn = false;
            }

            // Music Volume
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            // Sound Effect Volume
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
