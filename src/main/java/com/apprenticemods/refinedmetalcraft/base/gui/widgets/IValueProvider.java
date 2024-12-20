package com.apprenticemods.refinedmetalcraft.base.gui.widgets;


import net.minecraft.resources.ResourceLocation;

public interface IValueProvider<T> {
	ResourceLocation getId();

	void setId(ResourceLocation location);

	T getValue();

	void setValue(T value);
}