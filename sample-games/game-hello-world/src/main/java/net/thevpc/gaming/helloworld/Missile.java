package net.thevpc.gaming.helloworld;

import net.thevpc.gaming.atom.annotations.AtomSprite;
import net.thevpc.gaming.atom.annotations.Inject;
import net.thevpc.gaming.atom.annotations.OnInit;
import net.thevpc.gaming.atom.engine.SceneEngine;
import net.thevpc.gaming.atom.engine.maintasks.MoveSpriteMainTask;
import net.thevpc.gaming.atom.model.Sprite;
import net.thevpc.gaming.atom.presentation.Ball2DefaultSpriteCollisionManager;

import java.util.Locale;

@AtomSprite(
        kind = "Missile",
        name = "Missile1",
        sceneEngine = "hello",
        x=2,
        y=2,

        direction = Math.PI/2,
        speed = 0.4,
        mainTask = MoveSpriteMainTask.class,
        collisionTask = MissileDefaultSpriteCollisionManager.class
)
public class Missile {
    @Inject
    Sprite sprite;
    @Inject
    SceneEngine sceneEngine;

    @OnInit
    private void init(){

    }
}
