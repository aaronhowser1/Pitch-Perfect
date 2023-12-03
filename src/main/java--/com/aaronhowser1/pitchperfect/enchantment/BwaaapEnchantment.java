package com.aaronhowser1.pitchperfect.enchantment;

import com.aaronhowser1.pitchperfect.config.CommonConfigs;
import com.aaronhowser1.pitchperfect.config.ServerConfigs;
import com.aaronhowser1.pitchperfect.utils.ServerUtils;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class BwaaapEnchantment extends Enchantment {

    public BwaaapEnchantment(Enchantment.Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public int getMinCost(int pLevel) {
        return 5;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return 25;
    }

    public static List<LivingEntity> getTargets(LivingEntity user) {
        int range = ServerConfigs.BWAAAP_RANGE.get();

        return ServerUtils.getNearbyLivingEntities(user, range);
    }

    public static int getCooldown(LivingEntity user) {

        float cooldown = 0;

        for (LivingEntity target : getTargets(user)) {
            int range = ServerConfigs.BWAAAP_RANGE.get();

            double distanceToTarget = ServerUtils.distanceBetweenPoints(
                    ServerUtils.entityToVec3(user),
                    ServerUtils.entityToVec3(target)
            );

            if (distanceToTarget > range) continue;
            double targetPercentageFromRange = Math.abs(distanceToTarget/range - 1);

            cooldown += targetPercentageFromRange;
        }

        cooldown *= ServerConfigs.BWAAAP_COOLDOWN_MULT.get();

        return Mth.floor(cooldown);
    }

    public static void knockBack(LivingEntity user) {

        for (LivingEntity target : getTargets(user)) {
            int range = ServerConfigs.BWAAAP_RANGE.get();
            float strength = ServerConfigs.BWAAAP_STRENGTH.get();

            Vec3 targetMotion = target.getDeltaMovement();

            Vec3 userToTargetVector = ServerUtils.vecBetweenPoints(
                    ServerUtils.entityToVec3(user),
                    ServerUtils.entityToVec3(target)
            );

            double distanceToTarget = userToTargetVector.length();

            if (distanceToTarget > range) continue;

            double targetPercentageFromRange = Math.abs(distanceToTarget/range - 1);

            float yMult = user.isCrouching() ? 2F : 1F;

            target.setDeltaMovement(
                    targetMotion.add(
                            userToTargetVector.multiply(
                                    targetPercentageFromRange*strength,
                                    targetPercentageFromRange*strength*yMult,
                                    targetPercentageFromRange*strength
                            )
                    )
            );
        }
    }


}