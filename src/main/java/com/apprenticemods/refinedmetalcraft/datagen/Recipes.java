package com.apprenticemods.refinedmetalcraft.datagen;

import com.apprenticemods.refinedmetalcraft.setup.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class Recipes extends RecipeProvider {
	public Recipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {
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