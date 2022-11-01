package net.deechael.genshin.item.nova

import net.deechael.genshin.item.nova.item.Development
import net.deechael.genshin.item.nova.item.Weapons
import xyz.xenondevs.nova.addon.Addon

object GsItemNovaLoader : Addon() {

    override fun init() {
        Development.CharacterExp.init()
        Development.WeaponExp.init()
        Weapons.Bows.init()
        Weapons.Catalysts.init()
    }

    override fun onEnable() {

    }

    override fun onDisable() {

    }

}