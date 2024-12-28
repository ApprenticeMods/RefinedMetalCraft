package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.setup.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class JewelingStation extends Block implements EntityBlock {
	public static final VoxelShape SHAPE = Shapes.box(0, 0, 0, 1, 0.875, 1);

	public JewelingStation(Properties properties) {
		super(properties
				.requiresCorrectToolForDrops()
				.strength(3.5F, 9.0F)
				.instrument(NoteBlockInstrument.PLING)
		);

		this.registerDefaultState(this.getStateDefinition().any()
				.setValue(BlockStateProperties.FACING, Direction.NORTH)
				.setValue(BlockStateProperties.TRIGGERED, false)
		);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if(level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		if(level.getBlockEntity(pos) instanceof JewelingStationEntity jewelingStation) {
			player.openMenu(state.getMenuProvider(level, pos), pos);
			return InteractionResult.CONSUME;
		}

		return super.useWithoutItem(state, level, pos, player, hitResult);
	}

	@Override
	protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
		if(level.isClientSide() || state.is(newState.getBlock())) {
			super.onRemove(state, level, pos, newState, movedByPiston);
			return;
		}

		if(level.getBlockEntity(pos) instanceof JewelingStationEntity jewelingStation) {
			jewelingStation.dropContentsInWorld();
		}

		super.onRemove(state, level, pos, newState, movedByPiston);
	}

	@Override
	protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return new SimpleMenuProvider((id, inv, player) -> new JewelingStationContainer(id, inv, level, pos, player), Component.translatable("container.refinedmetalcraft.jewelingstation"));
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState original = super.getStateForPlacement(context);

		Direction facing = context.getHorizontalDirection();
		original = original.setValue(BlockStateProperties.FACING, facing);
		original = original.setValue(BlockStateProperties.TRIGGERED, false);
		return original;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(BlockStateProperties.FACING);
		builder.add(BlockStateProperties.TRIGGERED);
	}

	@Override
	protected VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
		return SHAPE;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new JewelingStationEntity(blockPos, blockState);
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
		return true;
	}

	@Override
	protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
		boolean hasRedstoneSignal = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
		boolean isTriggered = state.getValue(BlockStateProperties.TRIGGERED);
		if (hasRedstoneSignal && !isTriggered) {
			level.setBlock(pos, state.setValue(BlockStateProperties.TRIGGERED, true), 2);
			if(level.getBlockEntity(pos) instanceof JewelingStationEntity jewelingStation) {
				jewelingStation.redstonePulse();
			}
		} else if (!hasRedstoneSignal && isTriggered) {
			level.setBlock(pos, state.setValue(BlockStateProperties.TRIGGERED, false), 2);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
		if(blockEntityType != ModBlocks.JEWELINGSTATION_ENTITY.get()) {
			return null;
		}

		return (level1, blockPos, blockState, t) -> {
			if(t instanceof JewelingStationEntity jewelingStation) {
				jewelingStation.tick();
			}
		};
	}
}