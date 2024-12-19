package com.apprenticemods.refinedmetalcraft.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record JewelingStationRecipeInput(ItemStack front, ItemStack left, ItemStack right, ItemStack back, List<ItemStack> tools) implements RecipeInput {

	@Override
	public ItemStack getItem(int i) {
		switch(i) {
			case 0:
				return front;
			case 1:
				return left;
			case 2:
				return right;
			case 3:
				return back;
		}
		if(i >= 4 && i < 4 + tools.size()) {
			return tools.get(i - 4);
		}
		return ItemStack.EMPTY;
	}

	@Override
	public int size() {
		return 4 + tools.size();
	}

	public boolean hasTool(Ingredient tool) {
		return tools.stream().anyMatch(tool);
	}
}