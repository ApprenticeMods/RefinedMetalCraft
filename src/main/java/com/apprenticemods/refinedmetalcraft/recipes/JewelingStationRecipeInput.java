package com.apprenticemods.refinedmetalcraft.recipes;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record JewelingStationRecipeInput(ItemStack front, ItemStack left, ItemStack right, ItemStack back, List<ItemStack> tools) implements RecipeInput {

	@Override
	public ItemStack getItem(int i) {
		switch(Direction.from2DDataValue(i)) {
			case SOUTH:
				return front;
			case WEST:
				return left;
			case EAST:
				return right;
			case NORTH:
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