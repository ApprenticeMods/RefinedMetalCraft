package com.apprenticemods.refinedmetalcraft.setup;

import com.apprenticemods.refinedmetalcraft.RefinedMetalCraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;


@EventBusSubscriber(modid = RefinedMetalCraft.MODID)
public class ForgeEvents {

    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        if(event.getDistance() <= 0.5d) {
            return;
        }

        if(event.getEntity().level().isClientSide) {
            return;
        }

        if(!(event.getEntity() instanceof ServerPlayer)) {
            return;
        }

        ServerPlayer player = (ServerPlayer) event.getEntity();
        if(player.getInventory().getArmor(0).getItem() != ModItems.SPRINGBOOTS_ITEM.get().asItem()) {
            return;
        }

        // Disable fall damage
        event.setDamageMultiplier(0);

        // Calculate new movement based on fallDistance
        Vec3 currentMovement = player.getDeltaMovement();
        double forceRequiredForEqualJumpHeight = 0.255d;
        Vec3 upwardsYeet = new Vec3(
                currentMovement.x,
                event.getDistance() * forceRequiredForEqualJumpHeight * Config.dampeningFactor,
                currentMovement.z
        );

        // Actually apply the changed movement vector
        player.setDeltaMovement(upwardsYeet);

        // We need to sync data to the client, hurtMarked works as our "markAsDirty".
        player.hurtMarked = true;

    }
}
