package com.rpggame.rpggame.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface RenderingComponent extends Component {
    void render(SpriteBatch batch, double x, double y);
}
