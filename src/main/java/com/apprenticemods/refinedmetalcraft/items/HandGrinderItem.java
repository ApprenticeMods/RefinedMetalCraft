package com.apprenticemods.refinedmetalcraft.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class HandGrinderItem extends Item {


    public HandGrinderItem() {
        super(new Item.Properties().fireResistant().stacksTo(64).rarity(Rarity.COMMON));
    }
}
