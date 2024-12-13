package com.apprenticemods.refinedmetalcraft.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import net.minecraft.world.phys.Vec3;

public class SpringItem extends Item {

	private boolean fell = false;

	public SpringItem() {
		super(new Item.Properties().fireResistant().stacksTo(64).rarity(Rarity.COMMON));
	}
	// Tick-Rate: 20 ticks per second



	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
		super.inventoryTick(stack, level, entity, slotId, isSelected);
		if(level.isClientSide()) {
			return;
		}
		if(!(entity instanceof Player)) {
			return;
		}
		if(slotId != 40) {
			return;
		}
		Player player = (Player) entity;
		float savedFallDistance;
		if(player.fallDistance > 0){
			savedFallDistance = player.fallDistance;
		}

		//RefinedMetalCraft.LOGGER.info("SpringItem is in inventory slot {}", slotId);

		if(player.onGround()) {
			//player.setDeltaMovement(new Vec3(0, 1.9d, 0));
			savedFallDistance = 0;
		}
	}
}