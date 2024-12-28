package com.apprenticemods.refinedmetalcraft.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.function.Function;

public class JewelingToolsItem extends Item {
	private JewelingToolsItem(Properties properties) {
		super(properties);
	}

	private static Properties standardProperties() {
		return new Properties()
				.fireResistant()
				.rarity(Rarity.COMMON)
				.durability(64);
	}

	public static JewelingToolsItem standard() {
		return new JewelingToolsItem(standardProperties());
	}

	public static JewelingToolsItem with(Function<Properties, Properties> properties) {
		return new JewelingToolsItem(properties.apply(standardProperties()));
	}
}