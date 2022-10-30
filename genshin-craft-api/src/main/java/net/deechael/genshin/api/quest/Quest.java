package net.deechael.genshin.api.quest;

import net.deechael.genshin.api.item.GenshinItemStack;

import java.util.List;

public interface Quest {

    List<GenshinItemStack> getRewards();

}
