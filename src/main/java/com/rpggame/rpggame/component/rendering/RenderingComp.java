package com.rpggame.rpggame.component.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.system.SpriteManager;

/**
 * Interface for components that can be rendered on the screen.
 * All entities with this component will automatically be rendered by the RenderingSystem.
 */
public interface RenderingComp extends Component {

    void render(SpriteManager sprites, OrthographicCamera camera, SpriteBatch batch, Matrix3 transform);

}
