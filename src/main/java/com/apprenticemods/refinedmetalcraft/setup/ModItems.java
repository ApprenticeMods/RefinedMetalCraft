package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.items.JewelingToolsItem;
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

	// Finished Items
	public static final Supplier<ArmorItem> SPRINGBOOTS_ITEM = ITEMS.register("springboots", SpringBootsItem::new);

	// Production Items
	public static final DeferredItem<Item> SPRING_ITEM = ITEMS.register("spring", SpringItem::new);

	// Jeweling Tools
	public static final DeferredItem<Item> HANDDRILL_ITEM = ITEMS.register("handdrill", JewelingToolsItem::standard);
	public static final DeferredItem<Item> HANDGRINDER_ITEM = ITEMS.register("handgrinder", JewelingToolsItem::standard);
	public static final DeferredItem<Item> PLIERS_ITEM = ITEMS.register("pliers", JewelingToolsItem::standard);
	public static final DeferredItem<Item> CUTTERS_ITEM = ITEMS.register("cutters", JewelingToolsItem::standard);
	public static final DeferredItem<Item> MINIHAMMER_ITEM = ITEMS.register("minihammer", JewelingToolsItem::standard);
	public static final DeferredItem<Item> FILE_ITEM = ITEMS.register("file", JewelingToolsItem::standard);
	public static final DeferredItem<Item> SANDINGBELT_ITEM = ITEMS.register("sandingbelt", JewelingToolsItem::standard);

	// Block Items
	public static final DeferredItem<BlockItem> JEWELINGSTATION_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
			"jewelingstation", ModBlocks.JEWELINGSTATION_BLOCK
	);
}