package object;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Key extends SuperObject {
    public OBJ_Key() {
        name = "Key";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/key.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        solidArea.x = 5;
    }
}
