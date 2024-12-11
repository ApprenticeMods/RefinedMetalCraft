package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

public class JewelingStation extends Block {

	public JewelingStation(Properties properties) {
		super(properties);

		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(BlockStateProperties.FACING, Direction.NORTH)
		);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState original = super.getStateForPlacement(context);

		Direction facing = context.getHorizontalDirection().getOpposite();
		original = original.setValue(BlockStateProperties.FACING, facing);
		return original;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(BlockStateProperties.FACING);
	}
}