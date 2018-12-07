package HalationCode.tools.tetris;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class TetrisLauncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "gdx-tetris";
        cfg.width = 800;
        cfg.height = 480;

        new LwjglApplication(new TetrisGame(), cfg);
    }
}
