package com.rpggame.rpggame.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Interface for components that can be rendered on the screen.
 * All entities with this component will automatically be rendered by the RenderingSystem.
 */
public interface RenderingComponent extends Component {

    void render(SpriteBatch batch, double x, double y);

}
