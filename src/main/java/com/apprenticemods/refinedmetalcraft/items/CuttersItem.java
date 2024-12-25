package com.apprenticemods.refinedmetalcraft.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CuttersItem extends Item {


    public CuttersItem() {
        super(new Item.Properties().fireResistant().stacksTo(64).rarity(Rarity.COMMON).durability(16));
    }


}