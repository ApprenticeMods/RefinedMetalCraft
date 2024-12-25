package com.apprenticemods.refinedmetalcraft.networking;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import com.apprenticemods.refinedmetalcraft.blocks.JewelingStationEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record BlockTrigger(BlockPos pos) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<BlockTrigger> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(
			RefinedMetalCraft.MODID, "block_trigger"));

	public static final StreamCodec<ByteBuf, BlockTrigger> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC,
			BlockTrigger::pos,
			BlockTrigger::new
	);

	public static void handle(BlockTrigger blockTrigger, IPayloadContext context) {
		context.enqueueWork(() -> {
			Level level = context.player().level();
			if(level.getBlockEntity(blockTrigger.pos()) instanceof JewelingStationEntity jewelingStation) {
				jewelingStation.tryProgress();
			}
		});
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}