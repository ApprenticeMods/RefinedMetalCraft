package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.items.SpringBootsItem;
import com.apprenticemods.refinedmetalcraft.items.SpringItem;
import com.apprenticemods.refinedmetalcraft.items.HandDrillItem;
import com.apprenticemods.refinedmetalcraft.items.HandGrinderItem;
import com.apprenticemods.refinedmetalcraft.items.PliersItem;
import com.apprenticemods.refinedmetalcraft.items.CuttersItem;
import com.apprenticemods.refinedmetalcraft.items.MiniHammerItem;
import com.apprenticemods.refinedmetalcraft.items.FileItem;
import com.apprenticemods.refinedmetalcraft.items.SandingBeltItem;
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
	public static final DeferredItem<Item> HANDDRILL_ITEM = ITEMS.register("handdrill", HandDrillItem::new);
	public static final DeferredItem<Item> HANDGRINDER_ITEM = ITEMS.register("handgrinder", HandGrinderItem::new);
	public static final DeferredItem<Item> PLIERS_ITEM = ITEMS.register("pliers", PliersItem::new);
	public static final DeferredItem<Item> CUTTERS_ITEM = ITEMS.register("cutters", CuttersItem::new);
	public static final DeferredItem<Item> MINIHAMMER_ITEM = ITEMS.register("minihammer", MiniHammerItem::new);
	public static final DeferredItem<Item> FILE_ITEM = ITEMS.register("file", FileItem::new);
	public static final DeferredItem<Item> SANDINGBELT_ITEM = ITEMS.register("sandingbelt", SandingBeltItem::new);

	// Block Items
	public static final DeferredItem<BlockItem> JEWELINGSTATION_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
			"jewelingstation", ModBlocks.JEWELINGSTATION_BLOCK
	);
}