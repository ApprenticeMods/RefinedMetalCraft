package com.apprenticemods.refinedmetalcraft.base.gui.widgets;


import com.apprenticemods.refinedmetalcraft.base.gui.ISelectable;
import com.apprenticemods.refinedmetalcraft.base.gui.event.ListEntrySelectionEvent;
import com.apprenticemods.refinedmetalcraft.base.gui.event.WidgetEventResult;

public class WidgetListEntry extends WidgetPanel implements ISelectable {
	boolean isSelected = false;

	@Override
	public boolean isSelected() {
		return this.isSelected;
	}

	@Override
	public void setSelected(boolean state) {
		this.isSelected = state;
		this.fireEvent(new ListEntrySelectionEvent(state));
	}

	public void bindTo(Widget boundWidget) {
		this.addListener(ListEntrySelectionEvent.class, (event, widget) -> {
			boundWidget.setVisible(event.selected);
			return WidgetEventResult.CONTINUE_PROCESSING;
		});
	}
}