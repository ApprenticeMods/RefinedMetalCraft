package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStation;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStationEntity;
import com.apprenticemods.refinedmetalcraft.items.SpringItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.*;

import java.util.function.Supplier;

public class Registration {

	public static void register(IEventBus modbus) {
		BLOCKS.register(modbus);
		ITEMS.register(modbus);
		CREATIVE_MODE_TABS.register(modbus);
		BLOCK_ENTITIES.register(modbus);
		modbus.addListener(Registration::addCreative);
	}

	// Add the example block item to the building blocks tab
	private static void addCreative(BuildCreativeModeTabContentsEvent event) {
		if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			event.accept(Registration.JEWELINGSTATION_BLOCK_ITEM);
		}
	}

	// Create Deferred Registers to hold our Blocks and Items
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RefinedMetalCraft.MODID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RefinedMetalCraft.MODID);
	public static final DeferredRegister<BlockEntityType<?>>BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, RefinedMetalCraft.MODID);

	public static final DeferredBlock<Block> JEWELINGSTATION_BLOCK = BLOCKS.register("jewelingstation",
			() -> new JewelingStation(BlockBehaviour.Properties.of().mapColor(MapColor.STONE))
	);

	public static final Supplier<BlockEntityType<JewelingStationEntity>> JEWELINGSTATION_ENTITY = BLOCK_ENTITIES.register("jewelingstation",
			() -> BlockEntityType.Builder.of(JewelingStationEntity::new, JEWELINGSTATION_BLOCK.get()).build(null)
	);

	public static final DeferredItem<BlockItem> JEWELINGSTATION_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
			"jewelingstation", JEWELINGSTATION_BLOCK
	);


	// Creates a new food item with the id "refinedmetalcraft:example_id", nutrition 1 and saturation 2
	public static final DeferredItem<Item> SPRING_ITEM = ITEMS.register("spring", SpringItem::new);



	// Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "refinedmetalcraft" namespace
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
			RefinedMetalCraft.MODID);

	// Creates a creative tab with the id "refinedmetalcraft:example_tab" for the example item, that is placed after the combat tab
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(
			"example_tab",
			() -> CreativeModeTab.builder()
					.title(Component.translatable("itemGroup.refinedmetalcraft"))
					.withTabsBefore(CreativeModeTabs.COMBAT)
					.icon(() -> SPRING_ITEM.get().getDefaultInstance())
					.displayItems((parameters, output) -> {
						output.accept(SPRING_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
						output.accept(JEWELINGSTATION_BLOCK_ITEM.get());
					})
					.build());


}