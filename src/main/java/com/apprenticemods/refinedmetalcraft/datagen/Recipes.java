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
				.addTool(Ingredient.of(ModItems.PLIERS_ITEM.get()))
				.addTool(Ingredient.of(ModItems.MINIHAMMER_ITEM.get()))
				.unlockedBy("spring", has(ModItems.SPRING_ITEM.get())).save(recipeOutput);

		new JewelingStationRecipeBuilder(Items.GILDED_BLACKSTONE.getDefaultInstance())
				.setFront(Ingredient.of(Items.BLACKSTONE))
				.setLeft(Ingredient.of(Items.GOLD_NUGGET))
				.setRight(Ingredient.of(Items.GOLD_NUGGET))
				.setBack(Ingredient.of(Items.GOLD_NUGGET))
				.addTool(Ingredient.of(ModItems.HANDDRILL_ITEM.get()))
				.addTool(Ingredient.of(ModItems.MINIHAMMER_ITEM.get()))
				.addTool(Ingredient.of(ModItems.HANDGRINDER_ITEM.get()))
				.unlockedBy("blackstone_and_gold", has(Items.BLACKSTONE)).save(recipeOutput);

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