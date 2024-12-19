package com.apprenticemods.refinedmetalcraft.compatibility.jade;

import com.apprenticemods.refinedmetalcraft.blocks.JewelingStationEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.neoforged.neoforge.items.ItemStackHandler;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;

public class JewelingStationProvider implements IBlockComponentProvider {
	@Override
	public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
		JewelingStationEntity jewelingStationEntity = (JewelingStationEntity) blockAccessor.getBlockEntity();

		appendItem(iTooltip, "front", jewelingStationEntity.frontInventoryHandler);
		appendItem(iTooltip, "back", jewelingStationEntity.backInventoryHandler);
		appendItem(iTooltip, "left", jewelingStationEntity.leftInventoryHandler);
		appendItem(iTooltip, "right", jewelingStationEntity.rightInventoryHandler);
		appendItem(iTooltip, "output", jewelingStationEntity.outputInventoryHandler);
		appendTools(iTooltip, jewelingStationEntity.toolInventoryHandler);
	}

	private void appendItem(ITooltip iTooltip, String side, ItemStackHandler inventory) {
		ItemStack item = inventory.getStackInSlot(0);
		if(!item.isEmpty()) {
			iTooltip.add(Component.translatable("tooltip.refinedmetalcraft." + side));
			iTooltip.append(IElementHelper.get().item(item, 0.5f).size(new Vec2(8, 8)).translate(new Vec2(0, -1)));
		}
	}

	private void appendTools(ITooltip iTooltip, ItemStackHandler inventory) {
		boolean first = true;
		for(int slot = 0; slot < inventory.getSlots(); slot++) {
			ItemStack item = inventory.getStackInSlot(slot);
			if(!item.isEmpty()) {
				if(first) {
					iTooltip.add(Component.translatable("tooltip.refinedmetalcraft.tools"));
					first = false;
				}
				iTooltip.append(IElementHelper.get().item(item, 0.5f).size(new Vec2(8, 8)).translate(new Vec2(0, -1)));
			}
		}
	}

	@Override
	public ResourceLocation getUid() {
		return JadePlugin.JEWELINGSTATION;
	}
}