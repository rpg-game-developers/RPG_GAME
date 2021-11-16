package com.rpggame.rpggame.component.input;

import com.rpggame.rpggame.component.Component;

public interface TouchComp extends Component {

    boolean touchDown(int x, int y, int pointer, int button);

    boolean touchUp(int x, int y, int pointer, int button);

    boolean touchDragged(int x, int y, int pointer);

    boolean mouseMoved(int x, int y);

    boolean scrolled(float deltaX, float deltaY);

}
