package net.thevpc.gaming.atom.presentation;

import net.thevpc.gaming.atom.engine.SpriteFilter;
import net.thevpc.gaming.atom.engine.maintasks.MoveSpriteMainTask;
import net.thevpc.gaming.atom.engine.maintasks.MoveToPointSpriteMainTask;
import net.thevpc.gaming.atom.model.ModelPoint;
import net.thevpc.gaming.atom.model.Orientation;
import net.thevpc.gaming.atom.model.Point;
import net.thevpc.gaming.atom.model.Sprite;

/**
 * Created with IntelliJ IDEA.
 * User: vpc
 * Date: 8/15/13
 * Time: 12:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpriteController2 extends DefaultSceneController {
    @Override
    public void mouseClicked(SceneMouseEvent e) {
        Point point = e.getPoint();
        Sprite sprite1 = e.getSceneEngine().findSpriteByName("Ball2");
        e.getSceneEngine().setSpriteMainTask(sprite1, new MoveToPointSpriteMainTask((ModelPoint) point));
    }
}
