package com.apprenticemods.refinedmetalcraft.base.gui.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinedTooltipComponent implements TooltipComponent, ClientTooltipComponent {
	private final List<TooltipComponent> components = new ArrayList<>();
	private int padding = 2;

	public CombinedTooltipComponent(TooltipComponent... components) {
		this.components.addAll(Arrays.asList(components));
	}

	public CombinedTooltipComponent add(TooltipComponent... component) {
		this.components.addAll(Arrays.asList(component));
		return this;
	}

	@Override
	public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
		int currentX = x;
		for(TooltipComponent component : components) {
			if(component instanceof ClientTooltipComponent clientComponent) {
				int yOffset = (this.getHeight() - clientComponent.getHeight()) / 2;
				clientComponent.renderImage(font, currentX, y + yOffset, guiGraphics);
				currentX += clientComponent.getWidth(font) + padding;
			}
		}
	}

	@Override
	public int getHeight() {
		int max = 0;
		for(TooltipComponent component : components) {
			if(component instanceof ClientTooltipComponent clientComponent) {
				max = Math.max(max, clientComponent.getHeight());
			}
		}
		return max;
	}

	@Override
	public int getWidth(Font font) {
		int sum = 0;
		for(TooltipComponent component : components) {
			if(component instanceof ClientTooltipComponent clientComponent) {
				sum += clientComponent.getWidth(font) + padding;
			}
		}
		return sum;
	}
}