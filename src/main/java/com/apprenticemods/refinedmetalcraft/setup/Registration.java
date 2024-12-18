package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Registration {

	public static void register(IEventBus modbus) {
		ModBlocks.BLOCKS.register(modbus);
		ModBlocks.BLOCK_ENTITIES.register(modbus);
		ModItems.ITEMS.register(modbus);
		ModRecipes.RECIPE_TYPES.register(modbus);
		ModRecipes.RECIPE_SERIALIZERS.register(modbus);
		CREATIVE_MODE_TABS.register(modbus);
	}


	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
			Registries.CREATIVE_MODE_TAB,
			RefinedMetalCraft.MODID
	);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> REFINEDMETALCRAFT_TAB = CREATIVE_MODE_TABS.register(
			"refinedmetalcraft",
			() -> CreativeModeTab.builder()
					.title(Component.translatable("itemGroup.refinedmetalcraft"))
					.withTabsBefore(CreativeModeTabs.COMBAT)
					.icon(() -> ModItems.SPRING_ITEM.get().getDefaultInstance())
					.displayItems((parameters, output) -> {
						output.accept(ModItems.SPRING_ITEM.get());
						output.accept(ModItems.JEWELINGSTATION_BLOCK_ITEM.get());
						output.accept(ModItems.SPRINGBOOTS_ITEM.get());
					})
					.build());


}