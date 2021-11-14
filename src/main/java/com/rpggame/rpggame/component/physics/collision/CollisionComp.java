package com.rpggame.rpggame.component.physics.collision;

import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.component.physics.TransformComp;

public interface CollisionComp extends Component {
    boolean checkCollision(TransformComp transform, CollisionComp other, TransformComp otherTransform);
}
