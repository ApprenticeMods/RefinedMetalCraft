package com.apprenticemods.refinedmetalcraft.datagen;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.setup.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTags extends BlockTagsProvider {
	public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, RefinedMetalCraft.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE).add(
				ModBlocks.JEWELINGSTATION_BLOCK.get()
		);
	}
}