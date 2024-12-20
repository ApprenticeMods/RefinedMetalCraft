package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.base.gui.WidgetContainer;
import com.apprenticemods.refinedmetalcraft.setup.ModContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;

public class JewelingStationContainer extends WidgetContainer {
	public static int WIDTH = 176;
	public static int HEIGHT = 205;
	public static int MARGIN = 8;

	public static final ResourceLocation SLOTGROUP_INPUT = ResourceLocation.fromNamespaceAndPath(RefinedMetalCraft.MODID, "jeweling_station_input");
	public static final ResourceLocation SLOTGROUP_OUTPUT = ResourceLocation.fromNamespaceAndPath(RefinedMetalCraft.MODID, "jeweling_station_output");
	public static final ResourceLocation SLOTGROUP_TOOLS = ResourceLocation.fromNamespaceAndPath(RefinedMetalCraft.MODID, "jeweling_station_tools");

	private JewelingStationEntity jewelingStation;

	public JewelingStationContainer(int id, Inventory inv, Level world, BlockPos pos) {
		super(ModContainers.JEWELING_STATION.get(), id, inv);

		int availableWidth = WIDTH - (MARGIN * 2);
		int outputSlotX = MARGIN + (3 * availableWidth / 4) + 9;
		int centerSlotX = MARGIN + (availableWidth / 4) - 9;
		int leftSlotX = centerSlotX - 18;
		int rightSlotX = centerSlotX + 18;

		int topSlotY = MARGIN + 20;
		int bottomSlotY = topSlotY + 18;

		int toolSlotsY = bottomSlotY + 20 + 20;

		this.layoutPlayerInventorySlots(8, HEIGHT - 84);
		this.jewelingStation = (JewelingStationEntity) world.getBlockEntity(pos);
		if(jewelingStation != null) {
			this.addSlotRange(SLOTGROUP_TOOLS, jewelingStation.toolInventoryHandler, 0, MARGIN, toolSlotsY, jewelingStation.toolInventoryHandler.getSlots(), 18);

			this.addSlotRange(SLOTGROUP_INPUT, jewelingStation.frontInventoryHandler, 0, centerSlotX, bottomSlotY, 1, 18);
			this.addSlotRange(SLOTGROUP_INPUT, jewelingStation.leftInventoryHandler, 0, leftSlotX, bottomSlotY, 1, 18);
			this.addSlotRange(SLOTGROUP_INPUT, jewelingStation.rightInventoryHandler, 0, rightSlotX, bottomSlotY, 1, 18);
			this.addSlotRange(SLOTGROUP_INPUT, jewelingStation.backInventoryHandler, 0, centerSlotX, topSlotY, 1, 18);

			this.addSlotRange(SLOTGROUP_OUTPUT, jewelingStation.outputInventoryHandler, 0, outputSlotX, topSlotY + 9, jewelingStation.outputInventoryHandler.getSlots(), 18);
		}



	}
}