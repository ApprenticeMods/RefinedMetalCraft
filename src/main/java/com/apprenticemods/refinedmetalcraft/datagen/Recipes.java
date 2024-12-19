package com.apprenticemods.refinedmetalcraft.datagen;

import com.apprenticemods.refinedmetalcraft.recipes.JewelingStationRecipeBuilder;
import com.apprenticemods.refinedmetalcraft.setup.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class Recipes extends RecipeProvider {
	public Recipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {
		new JewelingStationRecipeBuilder(ModItems.SPRINGBOOTS_ITEM.get().getDefaultInstance())
				.setFront(Ingredient.of(ModItems.SPRING_ITEM.get()))
				.setLeft(Ingredient.of(ModItems.SPRING_ITEM.get()))
				.setRight(Ingredient.of(ModItems.SPRING_ITEM.get()))
				.setBack(Ingredient.of(Items.LEATHER_BOOTS))
				.addTool(Ingredient.of(Items.DIAMOND))
				.unlockedBy("spring", has(ModItems.SPRING_ITEM.get())).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JEWELINGSTATION_BLOCK_ITEM.get())
				.pattern("X#X")
				.pattern("X$X")
				.pattern("XXX")
				.define('X', ItemTags.PLANKS)
				.define('$', Items.IRON_BLOCK)
				.define('#', Tags.Items.LEATHERS)
				.unlockedBy("has_iron_block", has(Items.IRON_BLOCK))
				.save(recipeOutput);

	}
}