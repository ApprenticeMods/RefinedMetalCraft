package com.apprenticemods.refinedmetalcraft.base.gui.event;

public class CharTypedEvent implements IEvent {
	public char chr;
	public int scanCode;

	public CharTypedEvent(char chr, int scanCode) {
		this.chr = chr;
		this.scanCode = scanCode;
	}
}