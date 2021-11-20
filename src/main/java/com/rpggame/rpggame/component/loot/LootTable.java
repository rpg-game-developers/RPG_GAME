package com.rpggame.rpggame.component.loot;

import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.entity.Entity;

/**
 * All types of loot tables should implement this interface.
 */
public interface LootTable extends Component {
	Entity[] generateLoot();
}
