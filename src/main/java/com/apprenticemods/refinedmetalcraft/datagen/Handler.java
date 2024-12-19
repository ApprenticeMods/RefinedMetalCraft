package com.apprenticemods.refinedmetalcraft.datagen;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = RefinedMetalCraft.MODID)
public class Handler {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		var blockTagsProvider = new Blocks(output, lookupProvider, existingFileHelper);
		generator.addProvider(event.includeClient(), blockTagsProvider);
		generator.addProvider(event.includeServer(), new Items(output, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
		generator.addProvider(event.includeServer(), new Recipes(output, lookupProvider));
	}
}