package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = RefinedMetalCraft.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

	public static double dampeningFactor;
	public static final ModConfigSpec.DoubleValue DAMPENING_FACTOR = BUILDER
			.comment("Dampening factor to be applied when using spring boots")
			.defineInRange("dampeningFactor", 0.8, 0, 10);

	
	public static final ModConfigSpec SPEC = BUILDER.build();


	@SubscribeEvent
	static void onLoad(final ModConfigEvent event) {
		dampeningFactor = DAMPENING_FACTOR.get();
	}
}