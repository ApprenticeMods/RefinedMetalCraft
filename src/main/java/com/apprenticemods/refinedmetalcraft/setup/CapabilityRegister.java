package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStationEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = RefinedMetalCraft.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CapabilityRegister {

    @SubscribeEvent
    public static void onCapabilityRegister(RegisterCapabilitiesEvent event) {
        event.registerBlock(
                Capabilities.ItemHandler.BLOCK,
                (level, pos, state, blockEntity, side) -> {
                    if(blockEntity instanceof JewelingStationEntity jewelingStation) {
                        Direction facing = state.getValue(BlockStateProperties.FACING);

                        if(side == Direction.DOWN) {
                            return jewelingStation.outputInventoryHandler;
                        }

                        //  side     direction    inventory
                        //  NORTH    NORTH        NORTH
                        //  NORTH    EAST         ?
                        //  ...
                        //  WEST     SOUTH        ?
                        int rotateForNorth = facing.get2DDataValue() % 4;
                        Direction actualSide = side;
                        for(int i = 0; i < rotateForNorth; i++) {
                            actualSide = actualSide.getClockWise();
                        }

                        if(actualSide == Direction.NORTH) {
                            return jewelingStation.frontInventoryHandler;
                        }
                        if(actualSide == Direction.EAST) {
                            return jewelingStation.rightInventoryHandler;
                        }
                        if(actualSide == Direction.SOUTH) {
                            return jewelingStation.backInventoryHandler;
                        }
                        if(actualSide == Direction.WEST) {
                            return jewelingStation.leftInventoryHandler;
                        }
                    }
                    return null;
                },
                Registration.JEWELINGSTATION_BLOCK.get()
        );
    }

}
