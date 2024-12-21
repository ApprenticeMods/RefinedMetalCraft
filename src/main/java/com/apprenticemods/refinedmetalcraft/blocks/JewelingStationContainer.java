package com.apprenticemods.refinedmetalcraft.blocks;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.base.gui.WidgetBlockEntityContainer;
import com.apprenticemods.refinedmetalcraft.setup.ModContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class JewelingStationContainer extends WidgetBlockEntityContainer<JewelingStationEntity> {
	public static int WIDTH = 176;
	public static int HEIGHT = 205;
	public static int MARGIN = 8;

	public static final ResourceLocation SLOTGROUP_INPUT = ResourceLocation.fromNamespaceAndPath(RefinedMetalCraft.MODID, "jeweling_station_input");
	public static final ResourceLocation SLOTGROUP_OUTPUT = ResourceLocation.fromNamespaceAndPath(RefinedMetalCraft.MODID, "jeweling_station_output");
	public static final ResourceLocation SLOTGROUP_TOOLS = ResourceLocation.fromNamespaceAndPath(RefinedMetalCraft.MODID, "jeweling_station_tools");

	private JewelingStationEntity jewelingStation;

	public JewelingStationContainer(int id, Inventory inv, Level world, BlockPos pos, Player player) {
		super(ModContainers.JEWELING_STATION.get(), id, pos, inv, player);

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

			this.addSlotRange(SLOTGROUP_INPUT, jewelingStation.inputInventoryHandler, Direction.SOUTH.get2DDataValue(), centerSlotX, bottomSlotY, 1, 18);
			this.addSlotRange(SLOTGROUP_INPUT, jewelingStation.inputInventoryHandler, Direction.WEST.get2DDataValue(), leftSlotX, bottomSlotY, 1, 18);
			this.addSlotRange(SLOTGROUP_INPUT, jewelingStation.inputInventoryHandler, Direction.EAST.get2DDataValue(), rightSlotX, bottomSlotY, 1, 18);
			this.addSlotRange(SLOTGROUP_INPUT, jewelingStation.inputInventoryHandler, Direction.NORTH.get2DDataValue(), centerSlotX, topSlotY, 1, 18);

			this.addSlotRange(SLOTGROUP_OUTPUT, jewelingStation.outputInventoryHandler, 0, outputSlotX, topSlotY + 9, jewelingStation.outputInventoryHandler.getSlots(), 18);
		}



	}
}