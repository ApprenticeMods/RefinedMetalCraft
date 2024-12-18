package com.apprenticemods.refinedmetalcraft.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record JewelingStationRecipeInput(ItemStack front, ItemStack left, ItemStack right, ItemStack back, ItemStack tool) implements RecipeInput {

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
			case 4:
				return tool;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public int size() {
		return 4;
	}
}