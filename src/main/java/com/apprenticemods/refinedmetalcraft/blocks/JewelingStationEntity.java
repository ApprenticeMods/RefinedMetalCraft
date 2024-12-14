package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.setup.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class JewelingStationEntity extends BlockEntity {
	public ItemStackHandler outputInventoryHandler;
	public ItemStackHandler frontInventoryHandler;
	public ItemStackHandler rightInventoryHandler;
	public ItemStackHandler backInventoryHandler;
	public ItemStackHandler leftInventoryHandler;

	public JewelingStationEntity(BlockPos pos, BlockState blockState) {
		super(ModBlocks.JEWELINGSTATION_ENTITY.get(), pos, blockState);

		this.outputInventoryHandler = createOutputInventory();
		this.frontInventoryHandler = createInputInventory(Direction.NORTH);
		this.leftInventoryHandler = createInputInventory(Direction.WEST);
		this.backInventoryHandler = createInputInventory(Direction.SOUTH);
		this.rightInventoryHandler = createInputInventory(Direction.EAST);


	}

	public void notifyClients(boolean all) {
		if (this.level != null) {
			if (!this.level.isClientSide()) {
				this.level.sendBlockUpdated(this.worldPosition, this.level.getBlockState(this.worldPosition), this.level.getBlockState(this.worldPosition), all ? 3 : 2);
			}
		}
	}

	private ItemStackHandler createOutputInventory() {
		return new ItemStackHandler(1) {
			@Override
			protected void onContentsChanged(int slot) {
				setChanged();
				notifyClients(false);
			}
		};
	}

	private ItemStackHandler createInputInventory(Direction side) {
		var handler = new ItemStackHandler(1) {
			Direction side;

			@Override
			protected void onContentsChanged(int slot) {
				setChanged();
				notifyClients(false);
			}

			@Override
			public int getSlotLimit(int slot) {
				return 1;
			}
		};
		handler.side = side;
		return handler;
	}

	public void tick() {

	}

	public void redstonePulse() {

	}

	private void loadFirstStackFromTag(String label, ItemStackHandler handler, CompoundTag tag, HolderLookup.Provider registries) {
		if(tag.contains(label)) {
			handler.setStackInSlot(0, ItemStack.parse(registries, tag.getCompound(label)).orElse(ItemStack.EMPTY));
		}
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		loadFirstStackFromTag("output", outputInventoryHandler, tag, registries);
		loadFirstStackFromTag("front", frontInventoryHandler, tag, registries);
		loadFirstStackFromTag("right", rightInventoryHandler, tag, registries);
		loadFirstStackFromTag("back", backInventoryHandler, tag, registries);
		loadFirstStackFromTag("left", leftInventoryHandler, tag, registries);
	}

	private CompoundTag saveFirstStackToTag(String label, ItemStackHandler handler, CompoundTag tag, HolderLookup.Provider registries) {
		var stack = handler.getStackInSlot(0);
		if(!stack.isEmpty()) {
			tag.put(label, stack.save(registries, new CompoundTag()));
		}
		return tag;
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		saveFirstStackToTag("output", outputInventoryHandler, tag, registries);
		saveFirstStackToTag("front", frontInventoryHandler, tag, registries);
		saveFirstStackToTag("right", rightInventoryHandler, tag, registries);
		saveFirstStackToTag("back", backInventoryHandler, tag, registries);
		saveFirstStackToTag("left", leftInventoryHandler, tag, registries);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag tag = super.getUpdateTag(registries);
		saveAdditional(tag, registries);
		return tag;
	}

	@Override
	public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}


}