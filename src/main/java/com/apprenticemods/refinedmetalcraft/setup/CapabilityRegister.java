package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStationEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = RefinedMetalCraft.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CapabilityRegister {

	@SubscribeEvent
	public static void onCapabilityRegister(RegisterCapabilitiesEvent event) {

		event.registerBlock(
			Capabilities.ItemHandler.BLOCK,
			JewelingStationEntity::getCapability,
			ModBlocks.JEWELINGSTATION_BLOCK.get()
		);
	}

}