package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.items.SpringBootsItem;
import com.apprenticemods.refinedmetalcraft.items.SpringItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RefinedMetalCraft.MODID);

	public static final Supplier<ArmorItem> SPRINGBOOTS_ITEM = ITEMS.register("springboots", SpringBootsItem::new);
	public static final DeferredItem<Item> SPRING_ITEM = ITEMS.register("spring", SpringItem::new);


	// Block Items
	public static final DeferredItem<BlockItem> JEWELINGSTATION_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
			"jewelingstation", ModBlocks.JEWELINGSTATION_BLOCK
	);
}