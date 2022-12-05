package net.thevpc.gaming.atom.presentation;

import net.thevpc.gaming.atom.engine.collisiontasks.BorderCollision;
import net.thevpc.gaming.atom.engine.collisiontasks.DefaultSpriteCollisionTask;
import net.thevpc.gaming.atom.engine.collisiontasks.SpriteCollision;
import net.thevpc.gaming.atom.model.CollisionSides;
import net.thevpc.gaming.atom.model.Direction;
import net.thevpc.gaming.atom.model.DirectionTransform;
import net.thevpc.gaming.atom.model.Sprite;

public class Ball2DefaultSpriteCollisionManager extends DefaultSpriteCollisionTask {
    @Override
    public void collideWithBorder(BorderCollision borderCollision) {
        super.collideWithBorder(borderCollision);
        borderCollision.adjustSpritePosition();
        Sprite sprite = borderCollision.getSprite();
        CollisionSides sides = borderCollision.getSpriteCollisionSides();
        if (sides.isNorth()) {
            sprite.setDirection(DirectionTransform.HORIZONTAL_MIRROR);
        } else if (sides.isSouth()) {
            sprite.setDirection(DirectionTransform.HORIZONTAL_MIRROR);
        } else if (sides.isEast()) {
            sprite.setDirection(DirectionTransform.VERTICAL_MIRROR);
        } else if (sides.isWest()) {
            sprite.setDirection(DirectionTransform.VERTICAL_MIRROR);
        } else {
            sprite.setDirection(DirectionTransform.BACKWARD);
        }
    }

    @Override
    public void collideWithSprite(SpriteCollision spriteCollision) {
        super.collideWithSprite(spriteCollision);
        spriteCollision.adjustSpritePosition();
        Sprite sprite = spriteCollision.getSprite();
        CollisionSides sides = spriteCollision.getSpriteCollisionSides();
        if(sides!= null){
            sprite.setDirection(DirectionTransform.BACKWARD);
        }
    }
}
