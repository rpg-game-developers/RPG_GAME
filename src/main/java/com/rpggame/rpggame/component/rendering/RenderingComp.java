package com.rpggame.rpggame.component.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpggame.rpggame.component.Component;

/**
 * Interface for components that can be rendered on the screen.
 * All entities with this component will automatically be rendered by the RenderingSystem.
 */
public interface RenderingComp extends Component {

    void render(SpriteBatch batch, double x, double y);

}
