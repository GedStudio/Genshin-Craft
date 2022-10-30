package net.deechael.genshin.api.event;

import net.deechael.genshin.api.item.ItemType;

public interface EventCoin<E extends Event> extends ItemType {

    Event<E, EventCoin<E>> getEvent();

}
