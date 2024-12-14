package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class JewelingStationEntity extends BlockEntity {
	public ItemStackHandler outputInventoryHandler;
	public ItemStackHandler frontInventoryHandler;
	public ItemStackHandler rightInventoryHandler;
	public ItemStackHandler backInventoryHandler;
	public ItemStackHandler leftInventoryHandler;

	public JewelingStationEntity(BlockPos pos, BlockState blockState) {
		super(Registration.JEWELINGSTATION_ENTITY.get(), pos, blockState);

		this.outputInventoryHandler = createOutputInventory();
		this.frontInventoryHandler = createInputInventory(Direction.NORTH);
		this.leftInventoryHandler = createInputInventory(Direction.WEST);
		this.backInventoryHandler = createInputInventory(Direction.SOUTH);
		this.rightInventoryHandler = createInputInventory(Direction.EAST);


	}

	private ItemStackHandler createOutputInventory() {
		return new ItemStackHandler(1) {
			@Override
			protected void onContentsChanged(int slot) {
				setChanged();
			}
		};
	}

	private ItemStackHandler createInputInventory(Direction side) {
		var handler = new ItemStackHandler(1) {
			Direction side;

			@Override
			protected void onContentsChanged(int slot) {
				setChanged();
			}
		};
		handler.side = side;
		return handler;
	}

	public void tick() {

	}

	public void redstonePulse() {

	}
}