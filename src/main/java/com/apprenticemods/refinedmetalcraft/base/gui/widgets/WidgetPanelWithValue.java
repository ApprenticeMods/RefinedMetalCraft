package com.apprenticemods.refinedmetalcraft.base.gui.widgets;


import net.minecraft.resources.ResourceLocation;

public abstract class WidgetPanelWithValue<T> extends WidgetPanel implements IValueProvider<T> {
	private ResourceLocation id;

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public void setId(ResourceLocation location) {
		this.id = location;
	}
}