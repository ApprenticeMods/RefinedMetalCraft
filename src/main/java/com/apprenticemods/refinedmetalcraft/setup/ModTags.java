package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

	public static final TagKey<Item> JEWELING_TOOL_TAG = TagKey.create(
			Registries.ITEM,
			ResourceLocation.fromNamespaceAndPath(RefinedMetalCraft.MODID, "jeweling_tools")
	);
}