package net.deechael.genshin.api.event;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Event<E extends Event, C extends EventCoin<E>> {

    List<EventPage<E>> getPages();

    @Nullable
    EventStore<E, C> getStore();

    EventCoin<E> getEventCoinType();

}
