package net.deechael.genshin.item.nova.item

import net.deechael.genshin.item.nova.NovaUtils

object Development {

    object CharacterExp {

        val WANDERERS_ADVICE = NovaUtils.regDefault("wanderers_advice")
        val ADVENTURERS_EXPERIENCE = NovaUtils.regDefault("adventurers_experience")
        val HEROS_WIT = NovaUtils.regDefault("heros_wit")

        fun init() = Unit

    }

    object WeaponExp {

        val ENHANCEMENT_ORE = NovaUtils.regDefault("enhancement_ore")
        val FINE_ENHANCEMENT_ORE = NovaUtils.regDefault("fine_enhancement_ore")
        val MYTHIC_ENHANCEMENT_ORE = NovaUtils.regDefault("mythic_enhancement_ore")

        fun init() = Unit

    }

    object CharacterAscensionMaterial {

        object AscensionGem {



        }

    }

}