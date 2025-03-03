package com.apprenticemods.refinedmetalcraft.recipes;

import com.apprenticemods.refinedmetalcraft.setup.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

public class JewelingStationRecipe implements Recipe<JewelingStationRecipeInput> {

	private final Ingredient front;
	private final Ingredient left;
	private final Ingredient right;
	private final Ingredient back;
	private final List<Ingredient> toolSteps;
	private final ItemStack output;

	public JewelingStationRecipe(Ingredient front, Ingredient left, Ingredient right, Ingredient back, List<Ingredient> toolSteps, ItemStack output) {
		this.front = front;
		this.left = left;
		this.right = right;
		this.back = back;
		this.toolSteps = toolSteps;
		this.output = output;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> list = NonNullList.create();
		list.add(front);
		list.add(left);
		list.add(right);
		list.add(back);
		list.addAll(toolSteps);
		return list;
	}

	public boolean matches(JewelingStationRecipeInputNoTools jewelingStationRecipeInput, Level level) {
		if(!front.test(jewelingStationRecipeInput.front())) {
			return false;
		}
		if(!left.test(jewelingStationRecipeInput.left())) {
			return false;
		}
		if(!right.test(jewelingStationRecipeInput.right())) {
			return false;
		}
		if(!back.test(jewelingStationRecipeInput.back())) {
			return false;
		}

		return true;
	}

	@Override
	public boolean matches(JewelingStationRecipeInput jewelingStationRecipeInput, Level level) {
		if(!front.test(jewelingStationRecipeInput.front())) {
			return false;
		}
		if(!left.test(jewelingStationRecipeInput.left())) {
			return false;
		}
		if(!right.test(jewelingStationRecipeInput.right())) {
			return false;
		}
		if(!back.test(jewelingStationRecipeInput.back())) {
			return false;
		}

		for(var ingredient : toolSteps) {
			if(!jewelingStationRecipeInput.hasTool(ingredient)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack assemble(JewelingStationRecipeInput jewelingStationRecipeInput, HolderLookup.Provider provider) {
		return this.output.copy();
	}

	@Override
	public boolean canCraftInDimensions(int i, int i1) {
		return true;
	}

	public Ingredient getFront() {
		return front;
	}

	public Ingredient getLeft() {
		return left;
	}

	public Ingredient getRight() {
		return right;
	}

	public Ingredient getBack() {
		return back;
	}

	public List<Ingredient> getToolSteps() {
		return toolSteps;
	}

	public ItemStack getResultItem() {
		return this.output;
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider provider) {
		return this.output;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModRecipes.JEWELING_STATION_RECIPE_SERIALIZER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return ModRecipes.JEWELING_STATION_RECIPE.get();
	}
}