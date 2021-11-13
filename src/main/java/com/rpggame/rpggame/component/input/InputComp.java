package com.rpggame.rpggame.component.input;

import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.entity.Entity;

public interface InputComp extends Component {

    void handleInput(Entity entity);

}
