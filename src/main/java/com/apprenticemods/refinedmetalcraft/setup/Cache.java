package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.base.util.CacheStorage;
import com.apprenticemods.refinedmetalcraft.base.util.TagUtils;
import com.apprenticemods.refinedmetalcraft.recipes.JewelingStationRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesUpdatedEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = RefinedMetalCraft.MODID)
public class Cache {
	public static CacheStorage<Set<Item>> JEWELING_TOOLS = new CacheStorage<>(() -> TagUtils.getTags(BuiltInRegistries.ITEM, ModTags.JEWELING_TOOL_TAG));
	public static CacheStorage<List<RecipeHolder<JewelingStationRecipe>>> JEWELING_RECIPES = new CacheStorage<>(Collections.emptyList());


	@SubscribeEvent
	public static void onRecipesLoaded(RecipesUpdatedEvent event) {
		JEWELING_RECIPES.update(
			event.getRecipeManager().getAllRecipesFor(ModRecipes.JEWELING_STATION_RECIPE.get())
		);

		JEWELING_RECIPES.get().forEach(recipeHolder -> {
			JewelingStationRecipe recipe = recipeHolder.value();
			RefinedMetalCraft.LOGGER.info("Loaded jeweling station recipe: {}", recipeHolder.id());
		});
	}

	@SubscribeEvent
	public static void onTagsLoaded(TagsUpdatedEvent event) {
		JEWELING_TOOLS.invalidate();
	}

}