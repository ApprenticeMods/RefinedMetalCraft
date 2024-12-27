package com.apprenticemods.refinedmetalcraft.compatibility.jade;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.base.gui.GUI;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStationEntity;
import com.apprenticemods.refinedmetalcraft.recipes.JewelingStationRecipeInputNoTools;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.neoforged.neoforge.items.ItemStackHandler;
import snownee.jade.addon.core.ModNameProvider;
import snownee.jade.addon.core.ObjectNameProvider;
import snownee.jade.addon.harvest.HarvestToolProvider;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.ui.IElementHelper;

public class JewelingStationProvider implements IBlockComponentProvider {
	@Override
	public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
		JewelingStationEntity jewelingStationEntity = (JewelingStationEntity) blockAccessor.getBlockEntity();

		iTooltip.clear();
		ObjectNameProvider.getBlock().appendTooltip(iTooltip, blockAccessor, iPluginConfig);
		HarvestToolProvider.INSTANCE.appendTooltip(iTooltip, blockAccessor, iPluginConfig);
		var inputs = jewelingStationEntity.getInputs();
		if(!inputs.isEmpty() || !jewelingStationEntity.outputInventoryHandler.getStackInSlot(0).isEmpty()) {
			iTooltip.add(IElementHelper.get().spacer(0, 36));
			for(Direction direction : Direction.Plane.HORIZONTAL) {
				var stack = inputs.getItem(direction.get2DDataValue());

				iTooltip.append(new AtlasSpriteElement(GUI.tabIcons, 18, 18, 84, 84)
						.setOpacity(0.5f)
						.size(new Vec2(0,0))
						.translate(JewelingStationRecipeInputNoTools.getOffset(direction)));
				iTooltip.append(IElementHelper.get().item(stack)
						.size(new Vec2(0,0))
						.translate(JewelingStationRecipeInputNoTools.getOffset(direction)));
			}

			var progressArrow = IElementHelper.get().progress(jewelingStationEntity.getProgressRatio());
			iTooltip.append(progressArrow.translate(new Vec2(60, 0)));
			iTooltip.append(new AtlasSpriteElement(GUI.tabIcons, 18, 18, 84, 84)
					.setOpacity(0.5f)
					.translate(new Vec2(66, 9)));

			iTooltip.append(IElementHelper.get().item(jewelingStationEntity.outputInventoryHandler.getStackInSlot(0))
					.translate(new Vec2(48, 9)));
			iTooltip.add(IElementHelper.get().spacer(110, 2));
		}

		appendTools(iTooltip, jewelingStationEntity.toolInventoryHandler);
		ModNameProvider.getBlock().appendTooltip(iTooltip, blockAccessor, iPluginConfig);
	}

	@Override
	public int getDefaultPriority() {
		return 90000;
	}

	private void appendItem(ITooltip iTooltip, String side, ItemStackHandler inventory) {
		appendItem(iTooltip, side, inventory, 0);
	}

	private void appendItem(ITooltip iTooltip, String side, ItemStackHandler inventory, int slot) {
		ItemStack item = inventory.getStackInSlot(slot);
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