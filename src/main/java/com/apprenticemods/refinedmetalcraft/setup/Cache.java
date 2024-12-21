package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.base.util.CacheStorage;
import com.apprenticemods.refinedmetalcraft.base.util.TagUtils;
import com.apprenticemods.refinedmetalcraft.recipes.JewelingStationRecipe;
import com.apprenticemods.refinedmetalcraft.recipes.JewelingStationRecipeInputNoTools;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesUpdatedEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;

import java.util.*;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = RefinedMetalCraft.MODID)
public class Cache {
	public static CacheStorage<Set<Item>> JEWELING_TOOLS = new CacheStorage<>(() -> TagUtils.getTags(BuiltInRegistries.ITEM, ModTags.JEWELING_TOOL_TAG));
	public static CacheStorage<List<RecipeHolder<JewelingStationRecipe>>> JEWELING_RECIPES = new CacheStorage<>(Collections.emptyList());
	public static Map<JewelingStationRecipeInputNoTools, Set<RecipeHolder<JewelingStationRecipe>>> JEWELING_RECIPE_LOOKUP = new HashMap<>();
	public static Map<Direction, Map<JewelingStationRecipeInputNoTools, Set<Ingredient>>> JEWELING_INGREDIENT_LOOKUP = new HashMap<>();

	public static Set<Ingredient> getValidIngredients(JewelingStationRecipeInputNoTools input, Direction side) {
		if(!JEWELING_INGREDIENT_LOOKUP.containsKey(side)) {
			JEWELING_INGREDIENT_LOOKUP.put(side, new HashMap<>());
		}
		if(!JEWELING_INGREDIENT_LOOKUP.get(side).containsKey(input)) {
			var possibleRecipes = getPossibleRecipes(input);
			var validIngredients = possibleRecipes.stream().map(RecipeHolder::value).map(recipe -> {
				switch(side) {
					case SOUTH:
						return recipe.getFront();
					case WEST:
						return recipe.getLeft();
					case EAST:
						return recipe.getRight();
					case NORTH:
						return recipe.getBack();
					default:
						return Ingredient.EMPTY;
				}
			}).collect(Collectors.toSet());
			JEWELING_INGREDIENT_LOOKUP.get(side).put(input, validIngredients);
		}

		return JEWELING_INGREDIENT_LOOKUP.get(side).get(input);
	}

	public static Set<RecipeHolder<JewelingStationRecipe>> getPossibleRecipes(JewelingStationRecipeInputNoTools input) {
		if(!JEWELING_RECIPE_LOOKUP.containsKey(input)) {
			Set<RecipeHolder<JewelingStationRecipe>> recipes = JEWELING_RECIPES.get().stream().filter(recipeHolder -> {
				JewelingStationRecipe recipe = recipeHolder.value();
				if(!input.front().isEmpty()) {
					if(!recipe.getFront().test(input.front())) {
						return false;
					}
				}
				if(!input.left().isEmpty()) {
					if(!recipe.getLeft().test(input.left())) {
						return false;
					}
				}
				if(!input.right().isEmpty()) {
					if(!recipe.getRight().test(input.right())) {
						return false;
					}
				}
				if(!input.back().isEmpty()) {
					if(!recipe.getBack().test(input.back())) {
						return false;
					}
				}
				return true;
			}).collect(Collectors.toSet());

			JEWELING_RECIPE_LOOKUP.put(input, recipes);
		}

		return JEWELING_RECIPE_LOOKUP.get(input);
	}

	@SubscribeEvent
	public static void onRecipesLoaded(RecipesUpdatedEvent event) {
		JEWELING_RECIPES.update(
			event.getRecipeManager().getAllRecipesFor(ModRecipes.JEWELING_STATION_RECIPE.get())
		);

		JEWELING_RECIPES.get().forEach(recipeHolder -> {
			JewelingStationRecipe recipe = recipeHolder.value();
			RefinedMetalCraft.LOGGER.info("Loaded jeweling station recipe: {}", recipeHolder.id());
		});

		JEWELING_RECIPE_LOOKUP.clear();
		JEWELING_INGREDIENT_LOOKUP.clear();
	}

	@SubscribeEvent
	public static void onTagsLoaded(TagsUpdatedEvent event) {
		JEWELING_TOOLS.invalidate();

	}

}