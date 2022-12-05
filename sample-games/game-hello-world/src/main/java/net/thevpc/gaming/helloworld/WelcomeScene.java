package net.thevpc.gaming.helloworld;

import net.thevpc.gaming.atom.annotations.*;
import net.thevpc.gaming.atom.engine.SceneEngine;
import net.thevpc.gaming.atom.model.Point;
import net.thevpc.gaming.atom.model.Sprite;
import net.thevpc.gaming.atom.presentation.*;
import net.thevpc.gaming.atom.presentation.components.SLabel;
import net.thevpc.gaming.atom.presentation.layers.Layers;

@AtomScene(
        id = "Welcome",
        title = "Welcome To The Game",
        tileWidth = 50,
        tileHeight = 50
)
@AtomSceneEngine(
        id="Welcome",
        columns = 10,
        rows = 10,
        fps = 3
)
public class WelcomeScene {
    @Inject
    private Scene scene;

    @Inject
    private SceneEngine sceneEngine;
    private SLabel titleLabel = new SLabel("titleLabel", "title");

    @OnSceneStarted
    private void init() {
        scene.addLayer(Layers.fillBoardImage("/bg.jpg"));
        titleLabel.setText(scene.getTitle());
        scene.addComponent(titleLabel.setLocation(Point.ratio(0.3f, 0.3f)));
        scene.addController(new SceneController() {
            @Override
            public void keyPressed(SceneKeyEvent e) {
                SceneController.super.keyPressed(e);
                if (e.getKeyCode() == KeyCode.SPACE) {
                    sceneEngine.getGameEngine().setActiveSceneEngine("hello");
                }
            }
        });

    }
}
