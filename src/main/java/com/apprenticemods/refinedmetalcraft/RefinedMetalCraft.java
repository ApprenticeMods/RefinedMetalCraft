package com.apprenticemods.refinedmetalcraft;

import com.apprenticemods.refinedmetalcraft.setup.Config;
import com.apprenticemods.refinedmetalcraft.setup.Registration;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(RefinedMetalCraft.MODID)
public class RefinedMetalCraft {
	// Define mod id in a common place for everything to reference
	public static final String MODID = "refinedmetalcraft";
	// Directly reference a slf4j logger
	public static final Logger LOGGER = LogUtils.getLogger();

	// The constructor for the mod class is the first code that is run when your mod is loaded.
	// FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
	public RefinedMetalCraft(IEventBus modEventBus, ModContainer modContainer) {

		// Register the Deferred Register to the mod event bus so blocks get registered
		Registration.register(modEventBus);

		// Register our mod's ModConfigSpec so that FML can create and load the config file for us
		modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
	}

}