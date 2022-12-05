package net.thevpc.gaming.helloworld;

import net.thevpc.gaming.atom.annotations.*;
import net.thevpc.gaming.atom.debug.AdjustViewController;
import net.thevpc.gaming.atom.engine.SceneEngine;
import net.thevpc.gaming.atom.engine.SpriteFilter;
import net.thevpc.gaming.atom.model.Orientation;
import net.thevpc.gaming.atom.model.Point;
import net.thevpc.gaming.atom.model.Sprite;
import net.thevpc.gaming.atom.presentation.*;
import net.thevpc.gaming.atom.presentation.components.SLabel;
import net.thevpc.gaming.atom.presentation.components.SceneComponent;
import net.thevpc.gaming.atom.presentation.layers.*;

import java.awt.*;

/**
 * Created by vpc on 9/23/16.
 */
@AtomScene(
        id = "hello",
        title = "Hello World",
        tileWidth = 50,
        tileHeight = 50
        
)
@AtomSceneEngine(
        id="hello",
        columns = 10,
        rows = 10,
        fps = 3
)
public class HelloWorldScene {

    @Inject
    private Scene scene;
    @Inject
    private SceneEngine sceneEngine;
    private final SLabel scoreLabel = new SLabel("ScoreLabel","Vies :3");

    @OnNextFrame
    private void aChaqueTic(){
        int life = sceneEngine.findSpriteByName("Ball1").getLife();
        scoreLabel.setText("Vies : "+ life);
    }
    @OnSceneStarted
    private void init() {
        scene.addLayer(Layers.fillBoardGradient(Color.GRAY,Color.CYAN, Orientation.NORTH));
//        scene.addLayer(Layers.fillBoard(Color.GRAY));
        scene.addLayer(Layers.fillBoardImage("/bg.jpg"));
        scene.addComponent(scoreLabel.setLocation(Point.ratio(0f,0.1f)));
        scene.addLayer(Layers.debug());
//            scene.setSpriteView(SpriteFilter.byName("Ball2"),new DefaultSpriteView() {
//                @Override
//                public void draw(SpriteDrawingContext context) {
//                    context.getGraphics().drawRoundRect();
//                    context.getGraphics().setColor(Color.MAGENTA);
//                    context.getGraphics().fill(context.getSpriteShape());
//                }
//            });
        scene.setSpriteView(SpriteFilter.byName("Ball2"),new ImageSpriteView("/img.png",4,4));
//        scene.addLayer(Layers.fillScreen(Color.red));
        scene.addController(new SpriteController(SpriteFilter.byName("Ball1")).setArrowKeysLayout());
        scene.addController(new SpriteController(SpriteFilter.byName("Ball2"))
                .setUp(KeyCode.Z).setDown(KeyCode.S).setLeft(KeyCode.A).setRight(KeyCode.D));
        scene.addController(new SpriteController2());
        scene.addController(new AdjustViewController());
        scene.addComponent(
                new SLabel("Click CTRL-D to switch debug mode, use Arrows to move the ball")
                .setLocation(Point.ratio(0.5f,0.5f))
        );
        scene.addController(new SceneController() {
            @Override
            public void keyPressed(SceneKeyEvent e) {
                SceneController.super.keyPressed(e);
                if (e.getKeyCode() == KeyCode.SPACE){
                    sceneEngine.addSprite((Sprite) new Missile());

                }
            }
        });

//        scene.setSpriteView("Ball1", new DefaultSpriteView() {
//            @Override
//            public void draw(SpriteDrawingContext context) {
//                ViewBox sb = context.getSpriteBounds();
//                context.getGraphics().setPaint(Color.BLUE);
//                context.getGraphics().fillRect(
//                        sb.getX(),sb.getY(),
//                        sb.getWidth(),
//                        sb.getHeight()
//                );
//            }
//        });
//        scene.setSpriteView("Ball2", new DefaultSpriteView() {
//            @Override
//            public void draw(SpriteDrawingContext context) {
//                ViewBox sb = context.getSpriteBounds();
//                context.getGraphics().setPaint(Color.RED);
//                context.getGraphics().fillRect(
//                        sb.getX(),sb.getY(),
//                        sb.getWidth(),
//                        sb.getHeight()
//                );
//            }
//        });
        scene.setSpriteView(SpriteFilter.byKind("Ball"), new ImageSpriteView("/ball.png", 8, 4));
    }

}
