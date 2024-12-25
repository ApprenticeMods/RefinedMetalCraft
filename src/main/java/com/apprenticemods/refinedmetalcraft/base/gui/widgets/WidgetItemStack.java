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
	float saturation = 1.0F;
	float opacity = 1.0F;

	public WidgetItemStack(ItemStack stack) {
		this.setSize(16, 16);
		this.setValue(stack);
	}

	public WidgetItemStack(ItemStack stack, boolean drawSlot) {
		this(stack);
		this.drawSlot = drawSlot;
	}


	public WidgetItemStack setDrawSlot(boolean drawSlot) {
		this.drawSlot = drawSlot;
		return this;
	}

	public WidgetItemStack setOpacity(float opacity) {
		this.opacity = opacity;
		return this;
	}

	public WidgetItemStack setSaturation(float saturation) {
		this.saturation = saturation;
		return this;
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


		var oldColor = RenderSystem.getShaderColor();
		RenderSystem.setShaderColor(saturation, saturation, saturation, opacity);
		pGuiGraphics.renderItem(this.value, 0, 0);
		RenderSystem.setShaderColor(oldColor[0], oldColor[1], oldColor[2], oldColor[3]);
	}

	private void drawSlot(GuiGraphics pGuiGraphics, Screen screen) {
		RenderSystem.setShaderTexture(0, GUI.tabIcons);

		int texOffsetY = 84;
		int texOffsetX = 84;
		pGuiGraphics.blit(GUI.tabIcons, -1, -1, texOffsetX, texOffsetY, 18, 18);
	}
}