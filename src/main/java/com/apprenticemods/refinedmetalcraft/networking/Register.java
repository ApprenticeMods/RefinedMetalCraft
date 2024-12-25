package com.apprenticemods.refinedmetalcraft.networking;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = RefinedMetalCraft.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Register {
	@SubscribeEvent
	public static void register(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar("1");

		registrar.playToServer(
				BlockTrigger.TYPE,
				BlockTrigger.STREAM_CODEC,
				BlockTrigger::handle
		);
	}
}