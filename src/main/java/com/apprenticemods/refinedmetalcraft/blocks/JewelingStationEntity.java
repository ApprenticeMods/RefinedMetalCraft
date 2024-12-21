package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.base.util.DirectionUtils;
import com.apprenticemods.refinedmetalcraft.recipes.JewelingStationRecipe;
import com.apprenticemods.refinedmetalcraft.setup.Cache;
import com.apprenticemods.refinedmetalcraft.setup.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import net.neoforged.neoforge.items.wrapper.RangedWrapper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class JewelingStationEntity extends BlockEntity {
	public ItemStackHandler outputInventoryHandler;
	public ItemStackHandler inputInventoryHandler;
	public ItemStackHandler toolInventoryHandler;

	public IItemHandler combinedInventoryHandler;
	public Map<Direction, RangedWrapper> sideInventoryHandlers;

	public JewelingStationEntity(BlockPos pos, BlockState blockState) {
		super(ModBlocks.JEWELINGSTATION_ENTITY.get(), pos, blockState);

		this.outputInventoryHandler = createOutputInventory();
		this.inputInventoryHandler = createInputInventory();
		this.toolInventoryHandler = createToolInventory();
		this.combinedInventoryHandler = createCombinedInventory();

		this.sideInventoryHandlers = new HashMap<>();
		for(Direction direction : Direction.Plane.HORIZONTAL) {
			this.sideInventoryHandlers.put(direction, new RangedWrapper(this.inputInventoryHandler, direction.get2DDataValue(), direction.get2DDataValue()+1));
		}
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

	private ItemStackHandler createInputInventory() {
		return new ItemStackHandler(4) {
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
	}

	private ItemStackHandler createToolInventory() {
		return new ItemStackHandler(9) {
			@Override
			protected void onContentsChanged(int slot) {
				setChanged();
				notifyClients(false);
			}

			@Override
			public int getSlotLimit(int slot) {
				return 1;
			}

			@Override
			public boolean isItemValid(int slot, ItemStack stack) {
				// Do not allow the same tool in multiple slots
				for(int toolSlot = 0; toolSlot < this.getSlots(); toolSlot++) {
					if(toolSlot == slot) {
						continue;
					}
					if(this.getStackInSlot(toolSlot).is(stack.getItem())) {
						return false;
					}
				}

				// TODO: We should check all the jeweling station recipes to get a list of valid tools
				boolean stackHasToolTag = Cache.JEWELING_TOOLS.get().stream().anyMatch(stack::is);
				return stackHasToolTag;
			}

		};
	}

	private IItemHandler createCombinedInventory() {
		return new CombinedInvWrapper(
				outputInventoryHandler,
				inputInventoryHandler,
				toolInventoryHandler
		) {
			@Nonnull
			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate) {
				return ItemStack.EMPTY;
			}

			@Nonnull
			@Override
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
				return stack;
			}
		};
	}

	public static IItemHandler getCapability(Level level, BlockPos pos, BlockState state, BlockEntity entity, Direction side) {
		if(entity instanceof JewelingStationEntity jewelingStation) {
			if(side == null) {
				return jewelingStation.combinedInventoryHandler;
			}

			if(side == Direction.DOWN) {
				return jewelingStation.outputInventoryHandler;
			}

			if(side == Direction.UP) {
				return jewelingStation.toolInventoryHandler;
			}

			Direction faceAdjustedDirection = DirectionUtils.rotatedBlockSide(state.getValue(BlockStateProperties.FACING), side);
			return jewelingStation.sideInventoryHandlers.get(faceAdjustedDirection);
		}

		return null;
	}

	public void tick() {

	}

	public void redstonePulse() {

	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);

		inputInventoryHandler.deserializeNBT(registries, tag.getCompound("input"));
		outputInventoryHandler.deserializeNBT(registries, tag.getCompound("output"));
		toolInventoryHandler.deserializeNBT(registries, tag.getCompound("tools"));
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.put("input", inputInventoryHandler.serializeNBT(registries));
		tag.put("output", outputInventoryHandler.serializeNBT(registries));
		tag.put("tools", toolInventoryHandler.serializeNBT(registries));
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