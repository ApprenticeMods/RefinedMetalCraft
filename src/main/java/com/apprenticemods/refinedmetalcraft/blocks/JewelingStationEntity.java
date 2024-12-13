package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class JewelingStationEntity extends BlockEntity {
	public ItemStackHandler outputInventoryHandler;

	public JewelingStationEntity(BlockPos pos, BlockState blockState) {
		super(Registration.JEWELINGSTATION_ENTITY.get(), pos, blockState);

		this.outputInventoryHandler = createOutputInventory();
	}

	private ItemStackHandler createOutputInventory() {
		return new ItemStackHandler(1) {
			@Override
			protected void onContentsChanged(int slot) {
				setChanged();
			}
		};
	}

	public void tick() {

	}
}