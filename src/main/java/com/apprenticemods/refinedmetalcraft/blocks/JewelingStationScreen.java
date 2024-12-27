package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.base.gui.ColorHelper;
import com.apprenticemods.refinedmetalcraft.base.gui.GUI;
import com.apprenticemods.refinedmetalcraft.base.gui.WidgetContainerScreen;
import com.apprenticemods.refinedmetalcraft.base.gui.WidgetSlot;
import com.apprenticemods.refinedmetalcraft.base.gui.event.MouseClickEvent;
import com.apprenticemods.refinedmetalcraft.base.gui.event.UpdateScreenEvent;
import com.apprenticemods.refinedmetalcraft.base.gui.event.WidgetEventResult;
import com.apprenticemods.refinedmetalcraft.base.gui.tooltip.CombinedTooltipComponent;
import com.apprenticemods.refinedmetalcraft.base.gui.tooltip.ItemStackTooltipComponent;
import com.apprenticemods.refinedmetalcraft.base.gui.tooltip.SpriteTooltipComponent;
import com.apprenticemods.refinedmetalcraft.base.gui.widgets.WidgetItemStack;
import com.apprenticemods.refinedmetalcraft.base.gui.widgets.WidgetProgressArrow;
import com.apprenticemods.refinedmetalcraft.base.gui.widgets.WidgetTextBox;
import com.apprenticemods.refinedmetalcraft.networking.BlockTrigger;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Collections;

public class JewelingStationScreen extends WidgetContainerScreen<JewelingStationContainer> {
	public JewelingStationScreen(JewelingStationContainer container, Inventory inv, Component name) {
		super(container, inv, name);
	}

	@Override
	protected GUI createGUI() {
		GUI gui = new GUI(0, 0, JewelingStationContainer.WIDTH, JewelingStationContainer.HEIGHT);
		gui.setContainer(this.menu);

		var toolsLabel = new WidgetTextBox(I18n.get("container.refinedmetalcraft.jeweling_station.tools"));
		toolsLabel.setPosition(8, 75);
		toolsLabel.setTextColor(0x404040);
		gui.add(toolsLabel);

		var progressArrow = new WidgetProgressArrow();
		progressArrow.setValue(0D);
		progressArrow.setPosition(95, 37);
		gui.add(progressArrow);

		var nextToolButton = new WidgetItemStack(ItemStack.EMPTY, false);
		nextToolButton.setPosition(95 + 3, 19);
		nextToolButton.addListener(MouseClickEvent.class, (event, widget) -> {
			PacketDistributor.sendToServer(new BlockTrigger(this.menu.getBlockEntity().getBlockPos()));
			return WidgetEventResult.CONTINUE_PROCESSING;
		});
		gui.add(nextToolButton);

		JewelingStationEntity station = this.menu.getBlockEntity();
		gui.addListener(UpdateScreenEvent.class, (event, widget) -> {
			if(station.currentRecipe == null) {
				progressArrow.setValue(0D);
				nextToolButton.setValue(ItemStack.EMPTY);
				nextToolButton.setSaturation(0.0F);
				nextToolButton.setOpacity(0.4F);
				return WidgetEventResult.CONTINUE_PROCESSING;
			}

			double jewelProgress = 0D;
			var toolSteps = station.currentRecipe.value().getToolSteps();
			if(toolSteps.isEmpty() || station.currentRecipeProgress == toolSteps.size()) {
				jewelProgress = 100D;
			} else {
				jewelProgress = (double) station.currentRecipeProgress / toolSteps.size() * 100D;
			}

			if(!toolSteps.isEmpty()) {
				var nextTool = toolSteps.get(station.currentRecipeProgress);
				nextToolButton.setValue(nextTool.getItems()[0]);
				nextToolButton.setTooltipLines(Collections.emptyList());
				if(station.hasTool(nextTool)) {
					nextToolButton.setSaturation(1.0F);
					nextToolButton.setOpacity(1.0F);
					nextToolButton.addTooltipLine(
							Component.translatable("tooltip.refinedmetalcraft.next_tool")
									.withColor(ColorHelper.COLOR_ENABLED.getRGB()
									)
					);
				} else {
					nextToolButton.setSaturation(0.0F);
					nextToolButton.setOpacity(0.4F);
					nextToolButton.addTooltipLine(
							Component.translatable("tooltip.refinedmetalcraft.next_tool_missing")
									.withColor(ColorHelper.COLOR_ERRORED.getRGB()
									)
					);
				}
				nextToolButton.addTooltipLine(Component.translatable("container.refinedmetalcraft.jeweling_station.required_tools"));
				int step = 0;
				for(Ingredient tool : toolSteps) {
					if(tool.hasNoItems()) {
						continue;
					}
					SpriteTooltipComponent sprite;
					if(station.currentRecipeProgress > step) {
						sprite = new SpriteTooltipComponent(GUI.tabIcons, 14, 11, 76, 0);
					} else {
						if(!station.hasTool(tool)) {
							sprite = new SpriteTooltipComponent(GUI.tabIcons, 14, 11, 118, 0);
						} else {
							sprite = new SpriteTooltipComponent(GUI.tabIcons, 14, 11, 104, 0);
						}
					}

					var stack = new ItemStackTooltipComponent(tool.getItems()[0]);
					var combined = new CombinedTooltipComponent(sprite, stack);
					nextToolButton.addTooltipElement(combined);
					step++;
				}

			}

			progressArrow.setValue(jewelProgress);
			return WidgetEventResult.CONTINUE_PROCESSING;
		});

		for(Slot slot : this.menu.slots) {
			if(slot instanceof WidgetSlot ws) {
				ws.bindToWidget(gui);
			}
		}

		return gui;
	}
}