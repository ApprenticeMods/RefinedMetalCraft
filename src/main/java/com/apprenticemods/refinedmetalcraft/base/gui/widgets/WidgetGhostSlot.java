package com.apprenticemods.refinedmetalcraft.base.gui.widgets;


import com.apprenticemods.refinedmetalcraft.base.gui.event.MouseClickEvent;
import com.apprenticemods.refinedmetalcraft.base.gui.event.MouseReleasedEvent;
import com.apprenticemods.refinedmetalcraft.base.gui.event.WidgetEventResult;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;


public class WidgetGhostSlot extends WidgetItemStack {
	public WidgetGhostSlot(ItemStack stack) {
		super(stack, true);

		this.addListener(MouseClickEvent.class, (event, widget) -> {
			ItemStack playerStack = Minecraft.getInstance().player.getInventory().getSelected().copy();
			this.setValue(playerStack);
			return WidgetEventResult.CONTINUE_PROCESSING;
		});
		this.addListener(MouseReleasedEvent.class, ((event, widget) -> {
			return WidgetEventResult.CONTINUE_PROCESSING;
		}));
	}
}