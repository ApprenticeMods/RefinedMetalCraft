package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

	public static final TagKey<Item> JEWELING_TOOL_TAG = createItemTag("jeweling_tools");
	public static final TagKey<Item> JEWELING_TOOL_CUTTERS_TAG = createItemTag("jeweling_tools/cutters");
	public static final TagKey<Item> JEWELING_TOOL_FILES_TAG = createItemTag("jeweling_tools/files");
	public static final TagKey<Item> JEWELING_TOOL_DRILLS_TAG = createItemTag("jeweling_tools/drills");
	public static final TagKey<Item> JEWELING_TOOL_GRINDERS_TAG = createItemTag("jeweling_tools/grinders");
	public static final TagKey<Item> JEWELING_TOOL_HAMMERS_TAG = createItemTag("jeweling_tools/hammers");
	public static final TagKey<Item> JEWELING_TOOL_PLIERS_TAG = createItemTag("jeweling_tools/pliers");
	public static final TagKey<Item> JEWELING_TOOL_POLISHERS_TAG = createItemTag("jeweling_tools/polishers");


	private static TagKey<Item> createItemTag(String name) {
		return TagKey.create(
			Registries.ITEM,
			ResourceLocation.fromNamespaceAndPath(RefinedMetalCraft.MODID, name)
		);
	}
}