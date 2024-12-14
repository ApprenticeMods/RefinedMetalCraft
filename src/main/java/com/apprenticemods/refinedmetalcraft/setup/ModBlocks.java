package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStation;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStationEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RefinedMetalCraft.MODID);
	public static final DeferredRegister<BlockEntityType<?>>BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, RefinedMetalCraft.MODID);

	public static final DeferredBlock<Block> JEWELINGSTATION_BLOCK = BLOCKS.register("jewelingstation",
			() -> new JewelingStation(BlockBehaviour.Properties.of().mapColor(MapColor.STONE))
	);

	public static final Supplier<BlockEntityType<JewelingStationEntity>> JEWELINGSTATION_ENTITY = BLOCK_ENTITIES.register("jewelingstation",
			() -> BlockEntityType.Builder.of(JewelingStationEntity::new, JEWELINGSTATION_BLOCK.get()).build(null)
	);
}