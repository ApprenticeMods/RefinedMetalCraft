package com.apprenticemods.refinedmetalcraft.base.gui.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class ItemStackTooltipComponent implements TooltipComponent, ClientTooltipComponent {
	private final ItemStack stack;

	public ItemStackTooltipComponent(ItemStack stack) {
		this.stack = stack;
	}

	public ItemStack getStack() {
		return stack;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public int getWidth(Font font) {
		return 18 + 2 + font.width(stack.getHoverName());
	}

	@Override
	public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
		guiGraphics.renderItem(stack, x, y);
		guiGraphics.renderItemDecorations(font, stack, x, y);
		guiGraphics.drawString(font, stack.getHoverName(), x + 20, y + 5, 0xFFFFFF);
	}

}