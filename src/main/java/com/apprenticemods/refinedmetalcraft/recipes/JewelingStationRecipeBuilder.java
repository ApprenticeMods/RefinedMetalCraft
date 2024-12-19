package com.apprenticemods.refinedmetalcraft.recipes;

import com.apprenticemods.refinedmetalcraft.base.SimpleRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class JewelingStationRecipeBuilder extends SimpleRecipeBuilder {
	private Ingredient front;
	private Ingredient left;
	private Ingredient right;
	private Ingredient back;
	private List<Ingredient> tools;

	public JewelingStationRecipeBuilder(ItemStack result, Ingredient front, Ingredient left, Ingredient right, Ingredient back, List<Ingredient> tools) {
		super(result);
		this.front = front;
		this.left = left;
		this.right = right;
		this.back = back;
		this.tools = tools;
	}

	public JewelingStationRecipeBuilder(ItemStack result) {
		super(result);
		this.tools = new ArrayList<>();
	}

	public JewelingStationRecipeBuilder setBack(Ingredient back) {
		this.back = back;
		return this;
	}

	public JewelingStationRecipeBuilder setFront(Ingredient front) {
		this.front = front;
		return this;
	}

	public JewelingStationRecipeBuilder setLeft(Ingredient left) {
		this.left = left;
		return this;
	}

	public JewelingStationRecipeBuilder setRight(Ingredient right) {
		this.right = right;
		return this;
	}

	public JewelingStationRecipeBuilder addTool(Ingredient tool) {
		this.tools.add(tool);
		return this;
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceLocation id) {
		JewelingStationRecipe recipe = new JewelingStationRecipe(this.front, this.left, this.right, this.back, this.tools, this.result);
		recipeOutput.accept(id, recipe, this.getAdvancementOutput(recipeOutput, id));
	}
}