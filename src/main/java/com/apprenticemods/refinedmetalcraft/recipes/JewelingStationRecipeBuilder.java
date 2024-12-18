package com.apprenticemods.refinedmetalcraft.recipes;

import com.apprenticemods.refinedmetalcraft.base.SimpleRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class JewelingStationRecipeBuilder extends SimpleRecipeBuilder {
	private final Ingredient front;
	private final Ingredient left;
	private final Ingredient right;
	private final Ingredient back;
	private final Ingredient tool;

	public JewelingStationRecipeBuilder(ItemStack result, Ingredient front, Ingredient left, Ingredient right, Ingredient back, Ingredient tool) {
		super(result);
		this.front = front;
		this.left = left;
		this.right = right;
		this.back = back;
		this.tool = tool;
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceLocation id) {
		JewelingStationRecipe recipe = new JewelingStationRecipe(this.front, this.left, this.right, this.back, this.tool, this.result);
		recipeOutput.accept(id, recipe, this.getAdvancementOutput(recipeOutput, id));
	}
}