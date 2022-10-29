package net.deechael.genshin.item.nova

import xyz.xenondevs.nova.item.NovaItem
import xyz.xenondevs.nova.item.behavior.ItemBehavior
import xyz.xenondevs.nova.material.ItemNovaMaterial
import xyz.xenondevs.nova.material.NovaMaterialRegistry

object NovaUtils {

    fun regDefault(name: String, novaItem: NovaItem = NovaItem()): ItemNovaMaterial {
        return NovaMaterialRegistry
                .registerDefaultItem(GsItemNovaLoader,
                        name,
                        novaItem)
    }

    fun regDefault(name: String, vararg itemBehaviors: ItemBehavior): ItemNovaMaterial {
        return NovaMaterialRegistry
                .registerDefaultItem(GsItemNovaLoader,
                        name,
                        itemBehaviors=itemBehaviors)
    }

}