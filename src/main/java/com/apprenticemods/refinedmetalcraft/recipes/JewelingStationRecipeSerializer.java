package com.apprenticemods.refinedmetalcraft.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class JewelingStationRecipeSerializer implements RecipeSerializer<JewelingStationRecipe> {
	public static final MapCodec<JewelingStationRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
		return instance.group(
				Ingredient.CODEC.fieldOf("front").forGetter(JewelingStationRecipe::getFront),
				Ingredient.CODEC.fieldOf("left").forGetter(JewelingStationRecipe::getLeft),
				Ingredient.CODEC.fieldOf("right").forGetter(JewelingStationRecipe::getRight),
				Ingredient.CODEC.fieldOf("back").forGetter(JewelingStationRecipe::getBack),
				Ingredient.CODEC.listOf().fieldOf("tools").forGetter(JewelingStationRecipe::getToolSteps),
				ItemStack.CODEC.fieldOf("output").forGetter(JewelingStationRecipe::getResultItem)
		).apply(instance, JewelingStationRecipe::new);

	});

	public static final StreamCodec<RegistryFriendlyByteBuf, JewelingStationRecipe> STREAM_CODEC =
			StreamCodec.composite(
					Ingredient.CONTENTS_STREAM_CODEC, JewelingStationRecipe::getFront,
					Ingredient.CONTENTS_STREAM_CODEC, JewelingStationRecipe::getLeft,
					Ingredient.CONTENTS_STREAM_CODEC, JewelingStationRecipe::getRight,
					Ingredient.CONTENTS_STREAM_CODEC, JewelingStationRecipe::getBack,
					Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), JewelingStationRecipe::getToolSteps,
					ItemStack.STREAM_CODEC, JewelingStationRecipe::getResultItem,
					JewelingStationRecipe::new
			);

	@Override
	public MapCodec<JewelingStationRecipe> codec() {
		return CODEC;
	}

	@Override
	public StreamCodec<RegistryFriendlyByteBuf, JewelingStationRecipe> streamCodec() {
		return STREAM_CODEC;
	}
}