package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class JewelingStationEntity extends BlockEntity {
	public JewelingStationEntity(BlockPos pos, BlockState blockState) {
		super(Registration.JEWELINGSTATION_ENTITY.get(), pos, blockState);
	}

	public void tick() {

	}
}