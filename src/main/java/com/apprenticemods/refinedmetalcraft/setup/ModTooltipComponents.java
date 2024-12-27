package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.base.gui.tooltip.CombinedTooltipComponent;
import com.apprenticemods.refinedmetalcraft.base.gui.tooltip.ItemStackTooltipComponent;
import com.apprenticemods.refinedmetalcraft.base.gui.tooltip.SpriteTooltipComponent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;

import java.util.function.Function;

@EventBusSubscriber(modid = RefinedMetalCraft.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModTooltipComponents {

	@SubscribeEvent
	public static void onTooltipRegister(RegisterClientTooltipComponentFactoriesEvent event) {
		event.register(ItemStackTooltipComponent.class, Function.identity());
		event.register(CombinedTooltipComponent.class, Function.identity());
		event.register(SpriteTooltipComponent.class, Function.identity());
	}
}