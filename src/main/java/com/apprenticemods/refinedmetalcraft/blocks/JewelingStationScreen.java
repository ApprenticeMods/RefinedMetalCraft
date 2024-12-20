package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.base.gui.GUI;
import com.apprenticemods.refinedmetalcraft.base.gui.WidgetContainerScreen;
import com.apprenticemods.refinedmetalcraft.base.gui.WidgetSlot;
import com.apprenticemods.refinedmetalcraft.base.gui.widgets.WidgetProgressArrow;
import com.apprenticemods.refinedmetalcraft.base.gui.widgets.WidgetTextBox;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

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
		progressArrow.setValue(75D);
		progressArrow.setPosition(95, 37);
		gui.add(progressArrow);

		for(Slot slot : this.menu.slots) {
			if(slot instanceof WidgetSlot ws) {
				ws.bindToWidget(gui);
			}
		}

		return gui;
	}
}