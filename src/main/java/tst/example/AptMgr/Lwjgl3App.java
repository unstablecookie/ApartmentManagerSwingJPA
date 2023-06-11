package tst.example.AptMgr;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
public class Lwjgl3App {

    public Lwjgl3App(){
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setIdleFPS(60);
        config.useVsync(true);
        config.setTitle("aptPlan");
        config.setWindowedMode(960,640);
        new Lwjgl3Application(new MyGame(),config);
    }
}
