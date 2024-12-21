package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStationContainer;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStationScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = RefinedMetalCraft.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModContainers {
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(Registries.MENU, "refinedmetalcraft");

	public static final Supplier<MenuType<JewelingStationContainer>> JEWELING_STATION = CONTAINERS.register(
			"jeweling_station", resourceLocation -> IMenuTypeExtension.create(
					(i, inventory, registryFriendlyByteBuf) -> new JewelingStationContainer(i, inventory, inventory.player.level(), registryFriendlyByteBuf.readBlockPos(), inventory.player)
			)
	);

	@SubscribeEvent
	public static void attachScreens(RegisterMenuScreensEvent event) {
		event.register(JEWELING_STATION.get(), JewelingStationScreen::new);
	}
}