package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.recipes.JewelingStationRecipe;
import com.apprenticemods.refinedmetalcraft.recipes.JewelingStationRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
			DeferredRegister.create(Registries.RECIPE_TYPE, RefinedMetalCraft.MODID);

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
			DeferredRegister.create(Registries.RECIPE_SERIALIZER, RefinedMetalCraft.MODID);

	public static final Supplier<RecipeType<JewelingStationRecipe>> JEWELING_STATION_RECIPE = RECIPE_TYPES
			.register("jewelingstation",
			() -> RecipeType.<JewelingStationRecipe>simple(ResourceLocation.fromNamespaceAndPath(RefinedMetalCraft.MODID, "jewelingstation")));

	public static final Supplier<RecipeSerializer<JewelingStationRecipe>> JEWELING_STATION_RECIPE_SERIALIZER = RECIPE_SERIALIZERS
			.register("jewelingstation", JewelingStationRecipeSerializer::new);
}