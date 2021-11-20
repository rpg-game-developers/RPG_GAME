package com.rpggame.rpggame.component.physics.collision;

import com.badlogic.gdx.math.Vector2;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.component.physics.TransformComp;

public class RectangleCollisionComp implements CollisionComp {
    private Vector2 size;

    public RectangleCollisionComp() {
        this.size = new Vector2();
    }

    public RectangleCollisionComp(float x, float y) {
        this.size = new Vector2(x, y);
    }

    public RectangleCollisionComp(Vector2 size) {
        this.size = size;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    @Override
    public boolean checkCollision(TransformComp transform, CollisionComp other, TransformComp otherTransform) {
        if (other instanceof RectangleCollisionComp) {
            RectangleCollisionComp rect = (RectangleCollisionComp) other;
            Vector2 tl1 = transform.getPosition();
            Vector2 br1 = new Vector2(tl1).add(size);
            Vector2 tl2 = otherTransform.getPosition();
            Vector2 br2 = new Vector2(tl2).add(rect.getSize());

            return  ((tl1.x >= tl2.x && tl1.x < br2.x) || (tl2.x >= tl1.x && tl2.x < br1.x)) &&
                    ((tl1.y >= tl2.y && tl1.y < br2.y) || (tl2.y >= tl1.y && tl2.y < br1.y));
        }
        return false;
    }

    @Override
    public Component clone() {
        return new RectangleCollisionComp(size.x, size.y);
    }

}
