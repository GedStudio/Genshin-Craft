package net.deechael.genshin.item.nova

import net.deechael.genshin.item.nova.NovaUtils.regDefault
import net.deechael.genshin.item.nova.behaviour.BowItemBehaviour

object Weapons {

    object Bows {

        val POLAR_STAR = regDefault("polar_star", BowItemBehaviour)

        fun init() = Unit

    }

}