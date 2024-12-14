package com.apprenticemods.refinedmetalcraft.compatibility.jade;

import com.apprenticemods.refinedmetalcraft.blocks.JewelingStation;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {
	public static final ResourceLocation JEWELINGSTATION = ResourceLocation.fromNamespaceAndPath("refinedmetalcraft", "jade/jewelingstation");

	@Override
	public void register(IWailaCommonRegistration registration) {

	}

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.registerBlockComponent(new JewelingStationProvider(), JewelingStation.class);
	}
}