package com.apprenticemods.refinedmetalcraft.recipes;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.phys.Vec2;

public record JewelingStationRecipeInputNoTools(ItemStack front, ItemStack left, ItemStack right, ItemStack back) implements RecipeInput {
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

		return ItemStack.EMPTY;
	}

	@Override
	public int size() {
		return 4;
	}

	public static Vec2 getOffset(Direction direction) {
		return new Vec2(getX(direction), getY(direction));
	}

	public static int getX(Direction direction) {
		return switch(direction) {
			case NORTH, SOUTH -> 18;
			case EAST -> 36;
			default -> 0;
		};
	}

	public static int getY(Direction direction) {
		if(direction == Direction.NORTH) {
			return 0;
		}
		return 18;
	}
}