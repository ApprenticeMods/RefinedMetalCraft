package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStation;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Registration {

	public static void register(IEventBus modbus) {
		BLOCKS.register(modbus);
		ITEMS.register(modbus);
		CREATIVE_MODE_TABS.register(modbus);
		modbus.addListener(Registration::addCreative);
	}

	// Add the example block item to the building blocks tab
	private static void addCreative(BuildCreativeModeTabContentsEvent event) {
		if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			event.accept(Registration.JEWELINGSTATION_BLOCK_ITEM);
		}
	}

	// Create a Deferred Register to hold Blocks which will all be registered under the "refinedmetalcraft" namespace
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RefinedMetalCraft.MODID);

	// Create a Deferred Register to hold Items which will all be registered under the "refinedmetalcraft" namespace
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RefinedMetalCraft.MODID);


	// Creates a new Block with the id "refinedmetalcraft:example_block", combining the namespace and path
	//public static final DeferredBlock<Block> JEWELINGSTATION_BLOCK = BLOCKS.registerSimpleBlock("jewelingstation", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

	public static final DeferredBlock<Block> JEWELINGSTATION_BLOCK = BLOCKS.register("jewelingstation", () -> new JewelingStation(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));

	// Creates a new BlockItem with the id "refinedmetalcraft:jewelingstation", combining the namespace and path
	public static final DeferredItem<BlockItem> JEWELINGSTATION_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("jewelingstation", JEWELINGSTATION_BLOCK);


	// Creates a new food item with the id "refinedmetalcraft:example_id", nutrition 1 and saturation 2
	public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f)
			.build()));

	// Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "refinedmetalcraft" namespace
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RefinedMetalCraft.MODID);

	// Creates a creative tab with the id "refinedmetalcraft:example_tab" for the example item, that is placed after the combat tab
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.refinedmetalcraft")).withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> EXAMPLE_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
				output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
				output.accept(JEWELINGSTATION_BLOCK_ITEM.get());
			})
			.build());



}