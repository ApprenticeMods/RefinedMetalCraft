package com.apprenticemods.refinedmetalcraft.base.gui.event;


import com.apprenticemods.refinedmetalcraft.base.gui.widgets.Widget;

public interface IWidgetListener<T extends IEvent> {
	WidgetEventResult call(T event, Widget widget);
}