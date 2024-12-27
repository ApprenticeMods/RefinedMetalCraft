package com.apprenticemods.refinedmetalcraft.datagen;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.setup.ModItems;
import com.apprenticemods.refinedmetalcraft.setup.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class Items extends ItemTagsProvider {
	public Items(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockLookup, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockLookup, RefinedMetalCraft.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(ModTags.JEWELING_TOOL_TAG).add(
			ModItems.CUTTERS_ITEM.get(),
			ModItems.FILE_ITEM.get(),
			ModItems.HANDDRILL_ITEM.get(),
			ModItems.HANDGRINDER_ITEM.get(),
			ModItems.MINIHAMMER_ITEM.get(),
			ModItems.PLIERS_ITEM.get(),
			ModItems.SANDINGBELT_ITEM.get()
		);

		tag(ModTags.JEWELING_TOOL_CUTTERS_TAG).add(ModItems.CUTTERS_ITEM.get());
		tag(ModTags.JEWELING_TOOL_FILES_TAG).add(ModItems.FILE_ITEM.get());
		tag(ModTags.JEWELING_TOOL_DRILLS_TAG).add(ModItems.HANDDRILL_ITEM.get());
		tag(ModTags.JEWELING_TOOL_GRINDERS_TAG).add(ModItems.HANDGRINDER_ITEM.get());
		tag(ModTags.JEWELING_TOOL_HAMMERS_TAG).add(ModItems.MINIHAMMER_ITEM.get());
		tag(ModTags.JEWELING_TOOL_PLIERS_TAG).add(ModItems.PLIERS_ITEM.get());
		tag(ModTags.JEWELING_TOOL_POLISHERS_TAG).add(ModItems.SANDINGBELT_ITEM.get());
	}
}