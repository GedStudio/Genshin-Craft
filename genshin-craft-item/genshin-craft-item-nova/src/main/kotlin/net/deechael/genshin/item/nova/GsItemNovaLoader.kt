package net.deechael.genshin.item.nova

import xyz.xenondevs.nova.addon.Addon

object GsItemNovaLoader : Addon() {

    override fun init() {
        Weapons.Bows.init()
    }

    override fun onEnable() {

    }

    override fun onDisable() {

    }

}