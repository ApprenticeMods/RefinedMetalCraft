package com.apprenticemods.refinedmetalcraft.base.gui.event;

import com.apprenticemods.refinedmetalcraft.base.gui.widgets.WidgetPanel;

public class TabChangedEvent extends ValueChangedEvent<WidgetPanel> {
	public TabChangedEvent(WidgetPanel oldValue, WidgetPanel newValue) {
		super(oldValue, newValue);
	}
}