package com.apprenticemods.refinedmetalcraft.base.gui.widgets;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.base.gui.GUI;
import com.apprenticemods.refinedmetalcraft.base.gui.RedstoneMode;
import com.apprenticemods.refinedmetalcraft.base.gui.event.ValueChangedEvent;
import com.apprenticemods.refinedmetalcraft.base.gui.event.WidgetEventResult;
import net.minecraft.network.chat.Component;


public class WidgetRedstoneMode extends WidgetSpriteSelect<RedstoneMode> {
	public static final Component REDSTONE_IGNORE = Component.translatable(RefinedMetalCraft.MODID, "button.redstone.tooltip.ignore");
	public static final Component REDSTONE_REQUIRED = Component.translatable(RefinedMetalCraft.MODID, "button.redstone.tooltip.required");
	public static final Component REDSTONE_REJECTED = Component.translatable(RefinedMetalCraft.MODID, "button.redstone.tooltip.rejected");

	public WidgetRedstoneMode() {
		this(RedstoneMode.IGNORE_POWER);
	}

	public WidgetRedstoneMode(RedstoneMode initial) {
		this.addChoiceWithSprite(RedstoneMode.IGNORE_POWER, new SpriteData(GUI.tabIcons, 26, 84, 10, 10));
		this.addChoiceWithSprite(RedstoneMode.REQUIRE_POWER, new SpriteData(GUI.tabIcons, 36, 84, 4, 11));
		this.addChoiceWithSprite(RedstoneMode.REJECT_POWER, new SpriteData(GUI.tabIcons, 40, 84, 2, 11));
		this.setValue(initial);
		updateToolTips();


		this.addListener(ValueChangedEvent.class, (event, widget) -> {
			updateToolTips();
			return WidgetEventResult.CONTINUE_PROCESSING;
		});
	}

	public void updateToolTips() {
		if(this.getValue() == RedstoneMode.IGNORE_POWER) {
			this.setTooltipLines(REDSTONE_IGNORE);
		} else if(this.getValue() == RedstoneMode.REJECT_POWER) {
			this.setTooltipLines(REDSTONE_REJECTED);
		} else if(this.getValue() == RedstoneMode.REQUIRE_POWER) {
			this.setTooltipLines(REDSTONE_REQUIRED);
		}
	}
}