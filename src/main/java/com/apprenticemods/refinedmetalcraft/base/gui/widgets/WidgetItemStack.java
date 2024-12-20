package com.apprenticemods.refinedmetalcraft.base.gui.widgets;

import com.apprenticemods.refinedmetalcraft.base.gui.GUI;
import com.apprenticemods.refinedmetalcraft.base.gui.GUIHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.Collections;

public class WidgetItemStack extends WidgetWithValue<ItemStack> {
	boolean drawSlot = false;

	public WidgetItemStack(ItemStack stack) {
		this.setSize(16, 16);
		this.setValue(stack);
	}

	public WidgetItemStack(ItemStack stack, boolean drawSlot) {
		this(stack);
		this.drawSlot = drawSlot;
	}

	public void setValue(ItemStack stack) {
		if(!stack.isEmpty()) {

			var tooltipFlag = Minecraft.getInstance().options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL;
			this.setTooltipLines(stack.getTooltipLines(Item.TooltipContext.EMPTY, Minecraft.getInstance().player, tooltipFlag));
		} else {
			this.setTooltipLines(Collections.emptyList());
		}

		super.setValue(stack);
	}

	@Override
	public void draw(GuiGraphics pGuiGraphics, Screen screen) {
		super.draw(pGuiGraphics, screen);

		if(drawSlot) {
			this.drawSlot(pGuiGraphics, screen);
		}

		if(this.value == null || this.value.isEmpty()) {
			return;
		}

		GUIHelper.renderGuiItem(pGuiGraphics, this.value, getActualX(), getActualY(), !this.enabled);
	}

	private void drawSlot(GuiGraphics pGuiGraphics, Screen screen) {
		RenderSystem.setShaderTexture(0, GUI.tabIcons);

		int texOffsetY = 84;
		int texOffsetX = 84;
		pGuiGraphics.blit(GUI.tabIcons, -1, -1, texOffsetX, texOffsetY, 18, 18);
	}
}